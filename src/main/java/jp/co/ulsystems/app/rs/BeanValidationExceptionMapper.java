package jp.co.ulsystems.app.rs;


import javax.annotation.Priority;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.model.ResourceMethod;
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
    private ResourceContext rc;
    
    @Inject
    private ExtendedUriInfo info;
    
    
    @Override
    protected Response.Status getErrorStatus(ConstraintViolationException throwable) {
        return Response.Status.BAD_REQUEST;
    }
    
    @Override
    protected Object getErrorModel(ConstraintViolationException ex) {
       
        
        Model model = new Model();
        ResourceMethod m = info.getMatchedResourceMethod();
        for (Parameter param : m.getInvocable().getParameters()) {
            if(param.getAnnotation(SaveForm.class) != null) {
                model.setForm(rc.getResource((Class)param.getType()));
            }
        }
        
        for(ConstraintViolation<?> vo : ex.getConstraintViolations()) {
            model.getErrors().put(last(vo.getPropertyPath().toString()), vo.getMessage());
        }
        
        return model;
    }
    
    private String last(String str) {
        System.out.println(str);
        String[] arr = str.split("[.]");
        return arr[arr.length-1];
    }
    
   
}

