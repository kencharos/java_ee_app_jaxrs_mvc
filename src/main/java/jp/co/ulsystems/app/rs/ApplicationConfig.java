package jp.co.ulsystems.app.rs;

import javax.ws.rs.ApplicationPath;
import jp.co.ulsystems.app.rs.auth.AuthenticationFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.beanvalidation.MvcBeanValidationFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

/**
 *
 */
@ApplicationPath("/")
public class ApplicationConfig extends ResourceConfig{
    
    public ApplicationConfig() {
        this.packages(true, ApplicationConfig.class.getPackage().getName())
            .register(JspMvcFeature.class)
            .register(MvcBeanValidationFeature.class)
            .register(AuthenticationFilter.class);
    }

}
