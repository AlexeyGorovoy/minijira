package minijira.web;

import ejb.database.model.Project;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class ProjectEditor {


    int projectTypeId;
      /*
    @ManagedProperty("#{param.projectId}")
    */
    int projectId;

    Project project;


    @ManagedProperty  ("#{databaseBean}")
    DatabaseBean databaseBean;

    public ProjectEditor () {
    }

    @PostConstruct
    void prepare() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params =
                fc.getExternalContext().getRequestParameterMap();
        projectId = Integer.parseInt(params.get("projectId"));
        edit(projectId);
    }

    public String edit (int id) {

        projectId = id;
        /*

        */
        //String strId = params.get("projectId");

        Log.getLogger().info("JSF : edit project with strId = " + projectId + "; with dc = " + databaseBean.getDc());
        //projectId = Integer.parseInt(strId);
        project = databaseBean.find(projectId);
        Log.getLogger().info("JSF : project = " + project);
        return "/secure/edit_project?faces-redirect=true";
    }

    public void save() {
        project.setType(databaseBean.getDc().findProjectType(projectTypeId));
        project = databaseBean.getDc().merge(project);
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

    public DatabaseBean getDatabaseBean() {
        return databaseBean;
    }

    public void setDatabaseBean(DatabaseBean databaseBean) {
        this.databaseBean = databaseBean;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
