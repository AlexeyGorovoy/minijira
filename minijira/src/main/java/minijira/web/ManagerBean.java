package minijira.web;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 09.11.13
 * Time: 0:15
 * Email: alexey.gorovoy.work@gmail.com
 */

import ejb.database.model.ManagerType;
import ejb.database.model.Project;
import ejb.database.model.ProjectType;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("managerBean")
@SessionScoped
public class ManagerBean implements Serializable {

    ManagerType managerType;

    @Inject
    DatabaseBean databaseBean;

    @PostConstruct
    void init() {
        Log.getLogger().info("ManagerBean.init() called");
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    public DatabaseBean getDatabaseBean() {
        return databaseBean;
    }

    public void setDatabaseBean(DatabaseBean databaseBean) {
        this.databaseBean = databaseBean;
    }

    public String editManagerType(ManagerType managerType) {
        this.managerType = managerType;
        return "edit_manager_type";
    }

    public String saveManagerType() {
        managerType = databaseBean.getDc().merge(managerType);
        return "manager_types";
    }

    public String deleteManagerType(ManagerType managerType) {
        databaseBean.getDc().remove(managerType);
        return "manager_types";
    }
}
