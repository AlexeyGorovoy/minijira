package minijira.web;

import ejb.database.model.UserRole;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 24.10.13
 * Time: 16:53
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named("utils")
@SessionScoped
public class UtilsBean implements Serializable {

    private String googleString = "https://www.google.by/search?q=";

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    public String switchLocale(String lang) {
        locale = new Locale(lang);

        return FacesContext.getCurrentInstance().getViewRoot().getViewId() +
                "?faces-redirect=true";
    }

    public String getUrl() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

    public boolean getRandomBoolean() {
        Random rnd = new Random();
        return rnd.nextBoolean();
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getGoogleString() {
        return googleString;
    }

    public String googleSearchString(String query) {
        return googleString +query;
    }

}