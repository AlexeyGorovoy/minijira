package minijira.web;

import ejb.util.Log;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 27.10.13
 * Time: 20:21
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named("auth")
@SessionScoped
public class AuthBean implements Serializable {

    String message;

    String username;

    String password;

    public String login() {
        try {
            message="";
            HttpServletRequest request = (HttpServletRequest)  FacesContext.getCurrentInstance().getExternalContext().getRequest();
            Log.getLogger().info("Loggin in with : " + username + " pwd: " + password);
            request.login(username, password);
            if(request.isUserInRole("USERS"))
                return "/projects.xhtml";
            else {
                message= "Either Login or Password is wrong";
                return "/index.xhtml";
            }
        } catch(Exception e) {
            Log.getLogger().info("Log in exception :( ");
            e.printStackTrace();
            message= "Either Login or Password is wrong";
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
