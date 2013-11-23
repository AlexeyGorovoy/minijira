package minijira.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
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
@FacesValidator("minijira.EmailValidator")
public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
            "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
            "(\\.[A-Za-z]{2,})$";

    private Pattern pattern;
    private Matcher matcher;
    private ResourceBundle bundle;

    public EmailValidator(){
        pattern = Pattern.compile(EMAIL_PATTERN);
        String bundlename = "minijira.locale.ResourceBundle";
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        bundle = ResourceBundle.getBundle(bundlename, locale);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        if(!matcher.matches()){

            FacesMessage msg =
                    new FacesMessage("E-mail validation failed.",
                            bundle.getString("invalidEmailFormat"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }
    }
}
