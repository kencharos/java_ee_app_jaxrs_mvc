package jp.co.ulsystems.app.rs.auth;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * 認証チェックフィルター
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest req;
    
    @Override
    public void filter(ContainerRequestContext context) {
        // ログインリクエスト以外で継続セッションが取得できない場合はエラーとする。
        if(!context.getUriInfo().getPath().endsWith("login") && req.getSession(false) == null) {
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("not login").build());
        }
    }
}
