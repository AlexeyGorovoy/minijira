package minijira.web;


import ejb.database.model.*;
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
    ProjectType projectType;
    Customer customer;

    private int projectTypeId;
    private int projectCustomerId;
    private int projectDeveloperId;
    private int projectTesterId;
    private int projectPmId;


    int employee_id;

    int tech_id;

    @Inject
    DatabaseBean databaseBean;

    //expetimental!
    int customerId;

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

    public String editProject(Project project){
        this.project = project;
        projectTypeId = project.getType().getId();
        projectCustomerId = project.getCustomer().getId();
        projectDeveloperId = project.getDev_leader().getEmployee().getId();
        projectPmId = project.getPm().getEmployee().getId();
        projectTesterId = project.getTest_leader().getEmployee().getId();

        return "edit_project";
    }

    public String saveProject() {

        project.setType(databaseBean.getDc().find(ProjectType.class, projectTypeId));
        project.setCustomer(databaseBean.getDc().find(Customer.class, projectCustomerId));
        project.setDev_leader(databaseBean.getDc().find(Developer.class, projectDeveloperId));
        project.setPm(databaseBean.getDc().find(Manager.class, projectPmId));
        project.setTest_leader(databaseBean.getDc().find(Tester.class, projectTesterId));

        databaseBean.getDc().merge(project);
        return "projects";
    }

    public String deleteProject(Project project) {
        databaseBean.getDc().remove(project);
        return "projects";
    }

    public String editProjectType(ProjectType projectType) {
        this.projectType = projectType;
        return "edit_project_type";
    }

    public String saveProjectType() {
        projectType = databaseBean.getDc().merge(projectType);
        return "project_types";
    }

    public String deleteProjectType(ProjectType projectType) {
        databaseBean.getDc().remove(projectType);
        return "project_types";
    }

    public String editCustomer(Customer customer) {
        this.customer = customer;
        return "edit_customer";
    }

    public String saveCustomer() {
        databaseBean.getDc().merge(customer);
        return "customers";
    }

    public String deleteCustomer(Customer customer) {
        databaseBean.getDc().remove(customer);
        return "customers";
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(int projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public int getProjectCustomerId() {
        return projectCustomerId;
    }

    public void setProjectCustomerId(int projectCustomerId) {
        this.projectCustomerId = projectCustomerId;
    }

    public int getProjectDeveloperId() {
        return projectDeveloperId;
    }

    public void setProjectDeveloperId(int projectDeveloperId) {
        this.projectDeveloperId = projectDeveloperId;
    }

    public int getProjectTesterId() {
        return projectTesterId;
    }

    public void setProjectTesterId(int projectTesterId) {
        this.projectTesterId = projectTesterId;
    }

    public int getProjectPmId() {
        return projectPmId;
    }

    public void setProjectPmId(int projectPmId) {
        this.projectPmId = projectPmId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        this.customer = databaseBean.getDc().find(Customer.class, customerId);
        Log.getLogger().info("setCustomerId = " + customerId);
    }
}
