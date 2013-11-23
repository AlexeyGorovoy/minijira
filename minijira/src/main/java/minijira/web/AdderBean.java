package minijira.web;

import ejb.database.model.*;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 04.11.13
 * Time: 1:57
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named ("adder")
@RequestScoped
public class AdderBean implements Serializable {

    @Inject
    DatabaseBean databaseBean;

    @Inject
    AuthBean authBean;

    private ManagerType managerType;
    private ProjectType projectType;
    private Status status;
    private Priority priority;
    private Tech tech;
    private Rank rank;
    private TestType testType;
    private Customer customer;
    private Project project;

    private int projectTypeId;
    private int projectCustomerId;
    private int projectDeveloperId;
    private int projectTesterId;
    private int projectPmId;

    private Employee employee;
    private int rankId;
    private int devRankId;
    private int mainTechId;
    private int testTypeId;
    private int managerTypeId;
    private String email;
    private String password;
    private Date date_hired;
    private String userRole;

    private Task task;
    private int projectId;
    private int assigneeId;
    private int reporterId;
    private int priorityId;
    private int statusId;


    @PostConstruct
    void init() {
        Log.getLogger().info("AdderBean.init() called");
        managerType = new ManagerType();
        projectType = new ProjectType();
        status = new Status();
        priority = new Priority();
        tech = new Tech();
        testType = new TestType();
        rank = new Rank();
        customer = new Customer();

        project = new Project();

        employee = new Employee();

        task = new Task();
        reporterId = authBean.getEmployee().getId();
    }


    public String addManagerType() {
        databaseBean.getDc().merge(managerType);
        return "/pages/test/manager_types.jsf";
    }

    public String addProjectType() {
        databaseBean.getDc().merge(projectType);
        return "project_types";
    }

    public String addStatus() {
        databaseBean.getDc().merge(status);
        return "statuses";
    }

    public String addPriority() {
        databaseBean.getDc().merge(priority);
        return "priorities";
    }

    public String addTech() {
        databaseBean.getDc().merge(tech);
        return "techs";
    }

    public String addRank() {
        databaseBean.getDc().merge(rank);
        return "ranks";
    }

    public String addTestType() {
        databaseBean.getDc().merge(testType);
        return "test_types";
    }

    public String opa() {
        if (customer.getTitle().equals("")) {
            return "";
        } else {
            return "add " + customer.getTitle();
        }

    }

    public String addTask() {

        task.setAssignee(databaseBean.getDc().find(Employee.class, assigneeId));
        task.setReporter(databaseBean.getDc().find(Employee.class, reporterId));
        task.setProject(databaseBean.getDc().find(Project.class, projectId));
        task.setPriority(databaseBean.getDc().find(Priority.class, priorityId));
        task.setStatus(databaseBean.getDc().find(Status.class, statusId));

        task.setDueto(new Date());

        databaseBean.getDc().merge(task);
        return "tasks";
    }

    public String addCustomer() {
        if (customer.getTitle() == null || customer.getTitle().equals("")) {
            return null;
        }
        customer = databaseBean.getDc().merge(customer);
        return "customers";
    }

    public String addProject() {

        project.setType(databaseBean.getDc().find(ProjectType.class, projectTypeId));
        project.setCustomer(databaseBean.getDc().find(Customer.class, projectCustomerId));
        project.setDev_leader(databaseBean.getDc().find(Developer.class, projectDeveloperId));
        project.setPm(databaseBean.getDc().find(Manager.class, projectPmId));
        project.setTest_leader(databaseBean.getDc().find(Tester.class, projectTesterId));

        databaseBean.getDc().merge(project);
        return "projects";
    }

    public String addEmployee(String employeeType) {

        employee.setEmail(email);
        employee.setDate_hired(new Date());
        employee = databaseBean.getDc().merge(employee);

        User user= new User();
        user.setEmail(email);
        user.setPassword(password);
        user = databaseBean.getDc().merge(user);
        employee.setUser(user);
        employee = databaseBean.getDc().merge(employee);

        UserRole ur = new UserRole();
        ur.setRole(databaseBean.getDc().find(Role.class, userRole));
        ur.setEmail(email);
        databaseBean.getDc().merge(ur);

        Log.getLogger().info("addEmployee - type : " + employeeType);

        if (employeeType.equalsIgnoreCase("developer") ) {
            Developer dev = new Developer();
            dev.setRank(databaseBean.getDc().find(Rank.class, devRankId));
            dev.setMainTech(databaseBean.getDc().find(Tech.class, mainTechId));
            dev.setEmployee(employee);
            databaseBean.getDc().merge(dev);
            Log.getLogger().info("developer added");

        }
        if (employeeType.equalsIgnoreCase("tester")) {
            Tester test = new Tester();
            test.setRank(databaseBean.getDc().find(Rank.class, rankId));
            test.setType(databaseBean.getDc().find(TestType.class, testTypeId));
            test.setEmployee(employee);
            databaseBean.getDc().merge(test);
            Log.getLogger().info("tester added");
        }
        if (employeeType.equalsIgnoreCase("manager")) {
            Manager manager = new Manager();
            manager.setType(databaseBean.getDc().find(ManagerType.class, managerTypeId));
            manager.setEmployee(employee);
            databaseBean.getDc().merge(manager);
            Log.getLogger().info("manager added");
        }

        return "employees";
    }


    public String revokeAdmin(User user){
        UserRole userRole = databaseBean.getDc().findUserRoleByEmail(user.getEmail());
        userRole.setRole(databaseBean.getDc().find(Role.class, "USER"));
        databaseBean.getDc().merge(userRole);
        return "users";
    }

    public String grantAdmin(User user) {
        UserRole userRole = databaseBean.getDc().findUserRoleByEmail(user.getEmail());
        userRole.setRole(databaseBean.getDc().find(Role.class, "ADMIN"));
        databaseBean.getDc().merge(userRole);
        return "users";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Tech getTech() {
        return tech;
    }

    public void setTech(Tech tech) {
        this.tech = tech;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public int getMainTechId() {
        return mainTechId;
    }

    public void setMainTechId(int mainTechId) {
        this.mainTechId = mainTechId;
    }

    public int getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(int testTypeId) {
        this.testTypeId = testTypeId;
    }

    public int getManagerTypeId() {
        return managerTypeId;
    }

    public void setManagerTypeId(int managerTypeId) {
        this.managerTypeId = managerTypeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDevRankId() {
        return devRankId;
    }

    public void setDevRankId(int devRankId) {
        this.devRankId = devRankId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
