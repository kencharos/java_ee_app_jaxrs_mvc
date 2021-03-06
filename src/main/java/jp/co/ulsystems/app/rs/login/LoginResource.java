/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.ulsystems.app.rs.login;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import jp.co.ulsystems.app.rs.ApplicationException;
import jp.co.ulsystems.app.rs.Model;
import jp.co.ulsystems.app.rs.SaveForm;
import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;

/**
 * REST Web Service ログイン
 */
@Path("login")
@RequestScoped
public class LoginResource {

    
    @Inject
    private HttpServletRequest req;
    
    @Inject
    private User user;
    

    @GET
    @Template(name = "/login")
    public Model init(){
        return new Model();
    }
    
    /**
     * ログイン処理
     * 
     * @param  loginReq  IDとパスワード。バリデーション済み
     * @return ログインユーザー名と次の遷移先URL
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ErrorTemplate(name = "/login")
    public Response login(@Valid @BeanParam @SaveForm LoginRequest loginReq, @FormParam("button") String button) {
      
        System.out.println("button=" + button);
        
        try {
            req.getSession(true);
            req.login(loginReq.getId(), loginReq.getPassword());
            user.setName(req.getUserPrincipal().getName());
            
            UriBuilder builder = UriBuilder.fromPath(req.getContextPath());
            builder.path("notifications");
            return Response.seeOther(builder.build()).build();
        } catch(ServletException e) {
            
            throw new ApplicationException("id", "invalid id or password.");
        }
    }
    

}
