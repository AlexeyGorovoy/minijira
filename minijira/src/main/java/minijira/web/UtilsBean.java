package minijira.web;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

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
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    public String switchLocale(String lang) {
        locale = new Locale(lang);

        return FacesContext.getCurrentInstance().getViewRoot().getViewId() +
                "?faces-redirect=true";
    }

    public String getUrl() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}