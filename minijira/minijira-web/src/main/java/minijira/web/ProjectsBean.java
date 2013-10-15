package minijira.web;


import ejb.database.model.Project;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 06.10.13
 * Time: 1:15
 * Email: alexey.gorovoy.work@gmail.com
 */
@ManagedBean
public class ProjectsBean {
    List<Project> projects;

    int employee_id;

    int tech_id;

    @ManagedProperty (value = "#{databaseBean}")
    DatabaseBean databaseBean;

    @PostConstruct
    void init() {
        Log.getLogger().info("ProjectsBean.init() called");
        Log.getLogger().info("databaseBean = " + databaseBean);
        projects = databaseBean.getProjects();
        Log.getLogger().info("ProjectsBean.init() called");
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
}
