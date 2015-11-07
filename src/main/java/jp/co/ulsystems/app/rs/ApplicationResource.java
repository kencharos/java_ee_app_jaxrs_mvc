package jp.co.ulsystems.app.rs;

import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * 
 */
@ApplicationScoped
@Path("/")
public class ApplicationResource {

    @GET
    public Response home() {
        return Response.seeOther(URI.create("login")).build();
    }
    
    
}
