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
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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
    

    @GET
    @Template(name = "/login")
    public Map<String, String> init() {
        System.out.println("login init call,");
        return new HashMap<>();
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
    @Valid
    public Response login(@Valid @BeanParam LoginRequest loginReq) {
      
        try {
            req.getSession(true);
            System.out.println("login:" + loginReq.getId());
            req.login(loginReq.getId(), loginReq.getPassword());
            req.getSession().setAttribute("user", req.getUserPrincipal().getName());
            
            UriBuilder builder = UriBuilder.fromPath(req.getContextPath());
            builder.path("notifications");
            return Response.seeOther(builder.build()).build();
        } catch(ServletException e) {
            
            throw new WebApplicationException(e);
        }
    }
    

}
