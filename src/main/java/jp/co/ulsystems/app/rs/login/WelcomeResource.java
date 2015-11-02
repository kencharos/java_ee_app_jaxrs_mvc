package jp.co.ulsystems.app.rs.login;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import jp.co.ulsystems.app.rs.Model;
import org.glassfish.jersey.server.mvc.Template;

/**
 *
 */
@Path("notifications")
@RequestScoped
public class WelcomeResource {

    @GET
    @Template(name = "/welcome")
    public Model notifications() {
        Model model = new Model();
        model.put("notifications", Arrays.asList("This is JAX-RS app", "note.."));
        return model;
    }
    
}
