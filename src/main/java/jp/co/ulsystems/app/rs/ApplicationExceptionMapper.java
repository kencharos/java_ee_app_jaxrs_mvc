package jp.co.ulsystems.app.rs;


import javax.annotation.Priority;
import javax.inject.Inject;
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
 * アプリケーション例外から、
 * エラーメッセージをレスポンスに設定する。
 * Jersey MVC 標準の bean validation-mvc連携は model全体をエラーメッセージリストにしてしまうので、使い勝手が悪い。
 * そのため、形式を変更する。
 */
@Provider 
@Priority(Priorities.USER)
public class ApplicationExceptionMapper extends AbstractErrorTemplateMapper<ApplicationException> {

    @Context
    private ResourceContext rc;
    
    @Inject
    private ExtendedUriInfo info;
    
    @Override
    protected Response.Status getErrorStatus(ApplicationException throwable) {
        return Response.Status.BAD_REQUEST;
    }
    
    @Override
    protected Object getErrorModel(ApplicationException e) {
        Model model = new Model();
        
        
        ResourceMethod m = info.getMatchedResourceMethod();
        for (Parameter param : m.getInvocable().getParameters()) {
            if(param.getAnnotation(SaveForm.class) != null) {
                model.setForm(rc.getResource((Class)param.getType()));
            }
        }
        
        model.getErrors().put(e.getKey(), e.getMessage());
        
        
        return model;
    }

    
    
}

