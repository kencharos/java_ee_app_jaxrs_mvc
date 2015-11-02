package jp.co.ulsystems.app.rs;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.server.mvc.spi.AbstractErrorTemplateMapper;
/**
 * Bean Validationの例外に対するマッピング。
 * 
 * エラーメッセージをレスポンスに設定する。
 */
@Provider
@Priority(Priorities.USER)
public class BeanValidationExceptionMapper extends AbstractErrorTemplateMapper<ConstraintViolationException> {

    @Context
    private HttpServletRequest req;
    
    
    @Override
    protected Response.Status getErrorStatus(ConstraintViolationException throwable) {
        return Response.Status.BAD_REQUEST;
    }
    
    @Override
    protected Object getErrorModel(ConstraintViolationException ex) {
        Model model = new Model();
        for(ConstraintViolation<?> vo : ex.getConstraintViolations()) {
            model.getErrors().put(last(vo.getPropertyPath().toString()), vo.getMessage());
        }

        Enumeration<String> en = req.getParameterNames();
        Map<String, String> map = new HashMap<>();
        while(en.hasMoreElements()) {
            String key = en.nextElement();
            map.put(key, req.getParameter(key));
        }
        
        model.setForm(map);
        
        System.out.println(model.getErrors());
        System.out.println(model.getForm());
        
        return model;
    }
    
    private String last(String str) {
        System.out.println(str);
        String[] arr = str.split("[.]");
        return arr[arr.length-1];
    }
    
   
}

