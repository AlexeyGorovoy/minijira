package minijira.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 09.11.13
 * Time: 23:57
 * Email: alexey.gorovoy.work@gmail.com
 */
@FacesValidator("minijira.DateValidator")
public class DateValidator implements Validator {

    private ResourceBundle bundle;

    public DateValidator(){
        String bundlename = "minijira.locale.ResourceBundle";
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        bundle = ResourceBundle.getBundle(bundlename, locale);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        try {

            Date date = new Date( (Long)value );

            if (date.after(new Date())) {
                throw new IllegalStateException();
            }

        } catch (Exception ex) {

            FacesMessage msg =
                    new FacesMessage("Data validation failed.",
                            bundle.getString("invalidDate"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }
    }
}
