package minijira.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 10.11.13
 * Time: 13:23
 * Email: alexey.gorovoy.work@gmail.com
 */
@FacesValidator("minijira.NotNullValidator")
public class NotNullValidator implements Validator {

    ResourceBundle bundle;

    public NotNullValidator() {
        String bundlename = "minijira.locale.ResourceBundle";
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        bundle = ResourceBundle.getBundle(bundlename, locale);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        String str = value.toString();
        if (str == null || str.equals("")) {
            FacesMessage msg =
                    new FacesMessage("Not null validation failed.",
                            bundle.getString("notNullValidation"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
