/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.ulsystems.app.rs.login;

import java.net.URI;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.server.mvc.Template;

/**
 * REST Web Service ログイン
 */
@Path("logout")
@RequestScoped
public class LogoutResource {
    
    @Inject
    private HttpServletRequest req;

    @GET
    public Response logout(){
        
        try {
            System.out.println(req.getUserPrincipal().getName());
            req.logout();
            req.getSession(false).invalidate();
            
            UriBuilder builder = UriBuilder.fromPath(req.getContextPath());
            builder.path("login");
            return Response.seeOther(builder.build()).build();
            
        } catch(ServletException e) {
            throw new WebApplicationException(e);
        }
    }

}
