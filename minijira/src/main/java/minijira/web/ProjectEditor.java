package minijira.web;

import com.sun.faces.context.FacesContextImpl;
import ejb.database.DatabaseController;
import ejb.database.DatabaseControllerBean;
import ejb.database.model.Project;
import ejb.database.model.ProjectType;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 21.10.13
 * Time: 1:25
 * Email: alexey.gorovoy.work@gmail.com
 */
@ManagedBean
public class ProjectEditor {


    int projectTypeId;

    Project project;


    DatabaseController dc;

    @PostConstruct
    void init() {
        dc = new DatabaseControllerBean();
    }

    public String edit (int id) {
        Log.getLogger().info("JSF : edit project with id" + id);
        project = dc.find(id);
        return "/secure/edit_project";
    }

    public void save() {
        project.setType(dc.findProjectType(projectTypeId));
        dc.merge(project);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(int projectTypeId) {
        this.projectTypeId = projectTypeId;
    }
}
