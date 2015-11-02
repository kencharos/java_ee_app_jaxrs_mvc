package jp.co.ulsystems.app.rs.login;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;

/**
 * ログインリクエスト
 */
public class LoginRequest {

    @NotNull
    @Size(min = 3)
    @FormParam("id")
    private String id;
    
    @NotNull
    @Size(min = 3)
    @FormParam("password")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
