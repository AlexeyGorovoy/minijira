package minijira.web;

import ejb.database.model.*;
import ejb.database.DatabaseController;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    10:01
 * Email:   alexey.gorovoy.work@gmail.com
 */
@SuppressWarnings(value = "unchecked")
@Named("databaseBean")
@SessionScoped
public class DatabaseBean implements Serializable {

    @Inject
    DatabaseController dc;

    public DatabaseBean() {
        //dc = new DatabaseControllerBean();
    }

    public List<Comment> getComments() {
        return dc.getComment();
    }

    // Stored procedures
    public List<Comment> getCommentsByTaskSP(int task_id) {
        return null; //dc.findCommentByTaskSP(task_id);
    }

    public List<Project> findProjectsByEmployeeSP(int employee_id) {
        return dc.findProjectByEmployeeSP(employee_id);
    }

    public List<Project> findProjectsByTechSP(int tech_id) {
        return dc.findProjectByTechSP(tech_id);
    }
    ///

    public List<Employee> getEmployees () {
        return (List<Employee>)dc.get(Employee.class);
    }

    public List<Tester> getTesters () {
        return (List<Tester>)dc.get(Tester.class);
    }

    public List<Manager> getManagers () {
        return (List<Manager>)dc.get(Manager.class);
    }

    public List<User> getUsers () {
        return (List<User>)dc.get(User.class);
    }

    public List<Developer> getDevelopers () {
        return (List<Developer>)dc.get(Developer.class);
    }

    public List<Role> getRoles () {
        return (List<Role>)dc.get(Role.class);
    }

    public List<Priority> getPriorities() {
        return (List<Priority>)dc.get(Priority.class);
    }

    public List<Status> getStatuses() {
        return (List<Status>)dc.get(Status.class);
    }

    public List<Rank> getRanks() {
        return (List<Rank>)dc.get(Rank.class);
    }

    public List<ProjectType> getProjectTypes() {
        return (List<ProjectType>)dc.get(ProjectType.class);
    }

    public List<TestType> getTestTypes() {
        return (List<TestType>)dc.get(TestType.class);
    }

    public List<ManagerType> getManagerTypes() {
        return (List<ManagerType>)dc.get(ManagerType.class);
    }

    public List<Tech> getTechs() {
        return (List<Tech>)dc.get(Tech.class);
    }

    public List<Customer> getCustomers() {
        return (List<Customer>)dc.get(Customer.class);
    }

    public List<Project> getProjects() {
        return (List<Project>)dc.get(Project.class);
    }

    public List<Task> getTasks() {
        return (List<Task>)dc.get(Task.class);
    }

    public DatabaseController getDc() {
        return dc;
    }

    public void setDc(DatabaseController dc) {
        this.dc = dc;
    }
}
