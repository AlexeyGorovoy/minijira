package minijira.web;


import ejb.database.model.Project;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 06.10.13
 * Time: 1:15
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named("projectsBean")
@SessionScoped
public class ProjectsBean implements Serializable {

    List<Project> projects;

    Project project;

    int employee_id;

    int tech_id;


    @Inject
    DatabaseBean databaseBean;

    @PostConstruct
    void init() {
        Log.getLogger().info("ProjectsBean.init() called");
        Log.getLogger().info("databaseBean = " + databaseBean);
        projects = databaseBean.getProjects();
        Log.getLogger().info("ProjectsBean.init() called");
    }

    String slovo = "";

    public String getSlovo() {
        return slovo;
    }

    public String hello() {
        return "Hello, " + slovo + "!";
    }

    public void setSlovo(String slovo) {
        this.slovo = slovo;
    }

    public void findProjectsByEmployee() {
        projects = databaseBean.findProjectsByEmployeeSP(employee_id);
    }

    public void findProjectsByTech() {
        projects = databaseBean.findProjectsByTechSP(tech_id);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getTech_id() {
        return tech_id;
    }

    public void setTech_id(int tech_id) {
        this.tech_id = tech_id;
    }

    public DatabaseBean getDatabaseBean() {
        return databaseBean;
    }

    public void setDatabaseBean(DatabaseBean databaseBean) {
        this.databaseBean = databaseBean;
    }

    public String edit (int id) {
        Log.getLogger().info("JSF : edit project with strId = " + id);
        project = projects.get(id-1);
        Log.getLogger().info("JSF : project = " + project + " title=" + project.getTitle() + " id=" + project.getId());
        return "edit_project";
    }

    public String save() {
        Log.getLogger().info("Project saved: " + project + " title=" + project.getTitle() + " id=" + project.getId());
        project.setType(databaseBean.getDc().findProjectType(project.getType().getId()));
        project = databaseBean.getDc().merge(project);
        Log.getLogger().info("Project saved: " + project + " title=" + project.getTitle() + " id=" + project.getId());
        return "projects";
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
