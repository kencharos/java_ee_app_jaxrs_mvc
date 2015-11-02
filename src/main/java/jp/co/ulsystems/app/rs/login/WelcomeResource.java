package jp.co.ulsystems.app.rs.login;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Template;

/**
 *
 */
@Path("notifications")
@RequestScoped
public class WelcomeResource {

    @GET
    @Template(name = "/welcome")
    public Map<String, Object> notifications() {
        Map<String, Object> model = new HashMap<>();
        model.put("notifications", Arrays.asList("This is JAX-RS app", "note.."));
        return model;
    }
    
}
