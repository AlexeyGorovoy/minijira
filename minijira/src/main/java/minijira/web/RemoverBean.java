package minijira.web;

import ejb.database.model.ManagerType;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 04.11.13
 * Time: 2:46
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named("remover")
@SessionScoped
public class RemoverBean implements Serializable {

    @Inject
    DatabaseBean databaseBean;

    private int manager_type_id;

    public void removeManagerType() {
        ManagerType managerType = databaseBean.getDc().findManagerType(manager_type_id);
        databaseBean.getDc().remove(managerType);
    }

    public int getManager_type_id() {
        return manager_type_id;
    }

    public void setManager_type_id(int manager_type_id) {
        this.manager_type_id = manager_type_id;
    }
}
