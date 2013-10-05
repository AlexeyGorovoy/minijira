package minijira.ejb.database;

import minijira.ejb.database.model.*;
import minijira.ejb.util.Log;
import minijira.ejbapi.DatabaseController;
import minijira.ejbapi.dto.*;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.queries.StoredProcedureCall;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    13:35
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Local(value = DatabaseController.class)
@Stateless
@SuppressWarnings("unchecked")
public class DatabaseControllerBean implements DatabaseController {


    @PostConstruct
    public void init() {
        try {
            Log.setup();
        } catch (IOException ex) {

        }
    }

    @PersistenceContext
    EntityManager em;

    public Query createNamedQuery(String queryName) {
        return em.createNamedQuery(queryName);
    }


    @Override
    public List<CommentDto> getCommentByProject(int project_id) {
        Log.getLogger().info("getCommentByProject called, project_id: " + project_id);
        Query q = createNamedQuery("Comment.findByProject");
        q.setParameter("project_id", project_id);
        DatabaseGetter<Comment> dg = new DatabaseGetter<Comment>(q);
        return (List<CommentDto>)dg.get();
    }
    // Stored procedures calls
    @Override
    public List<CommentDto> findCommentByProjectSP(int project_id) {
        Log.getLogger().info("SP findCommentByProjectSP called, project_id: " + project_id);

        Query q = em.createNamedStoredProcedureQuery("Comment.findByProjectSP");
        q.setParameter("project_id", project_id);
        DatabaseGetter<Comment> dg = new DatabaseGetter<Comment>(q);
        return (List<CommentDto>)dg.get();
    }

    @Override
    public List<ProjectDto> findProjectByTechSP(int tech_id) {
        Log.getLogger().info("SP findProjectByTechSP called, tech_id: " + tech_id);

        Query q = em.createNamedStoredProcedureQuery("Project.findByTechSP");
        q.setParameter("tech_id", tech_id);
        DatabaseGetter<Project> dg = new DatabaseGetter<Project>(q);
        return (List<ProjectDto>)dg.get();
    }

    @Override
    public List<ProjectDto> findProjectByEmployeeSP(int employee_id) {
        Log.getLogger().info("SP findProjectByEmployeeSP called, employee_id: " + employee_id);

        Query q = em.createNamedStoredProcedureQuery("Project.findByEmployeeSP");
        q.setParameter("employee_id", employee_id);
        DatabaseGetter<Project> dg = new DatabaseGetter<Project>(q);
        return (List<ProjectDto>)dg.get();
    }

    ///

    @Override
    public List<EmployeeDto> getEmployee() {
        Log.getLogger().info("getEmployee called");
        DatabaseGetter<Employee> dg = new DatabaseGetter<Employee>(createNamedQuery("Employee.findAll"));
        return (List<EmployeeDto>)dg.get();
    }

    @Override
    public List<DeveloperDto> getDeveloper() {
        Log.getLogger().info("getDeveloper called");
        DatabaseGetter<Developer> dg = new DatabaseGetter<Developer>(createNamedQuery("Developer.findAll"));
        return (List<DeveloperDto>)dg.get();
    }

    @Override
    public List<TesterDto> getTester() {
        Log.getLogger().info("getTester called");
        DatabaseGetter<Tester> dg = new DatabaseGetter<Tester>(createNamedQuery("Tester.findAll"));
        return (List<TesterDto>)dg.get();
    }

    @Override
    public List<ManagerDto> getManager() {
        Log.getLogger().info("getManager called");
        DatabaseGetter<Manager> dg = new DatabaseGetter<Manager>(createNamedQuery("Manager.findAll"));
        return (List<ManagerDto>)dg.get();
    }

    @Override
    public List<CustomerAgentDto> getCustomerAgent() {
        Log.getLogger().info("getCustomerAgent called");
        DatabaseGetter<CustomerAgent> dg = new DatabaseGetter<CustomerAgent>(createNamedQuery("CustomerAgent.findAll"));
        return (List<CustomerAgentDto>)dg.get();
    }

    @Override
    public List<OfficeDto> getOffice() {
        Log.getLogger().info("getOffice called");
        DatabaseGetter<Office> dg = new DatabaseGetter<Office>(createNamedQuery("Office.findAll"));
        return (List<OfficeDto>)dg.get();
    }

    @Override
    public List<PriorityDto> getPriority() {
        Log.getLogger().info("getPriority called");
        DatabaseGetter<Priority> dg = new DatabaseGetter<Priority>(createNamedQuery("Priority.findAll"));
        return (List<PriorityDto>)dg.get();
    }

    @Override
    public List<WorkflowDto> getWorkflow() {
        Log.getLogger().info("getWorkflow called");
        DatabaseGetter<Workflow> dg = new DatabaseGetter<Workflow>(createNamedQuery("Workflow.findAll"));
        return (List<WorkflowDto>)dg.get();
    }

    @Override
    public List<RankDto> getRank() {
        Log.getLogger().info("getRank called");
        DatabaseGetter<Rank> dg = new DatabaseGetter<Rank>(createNamedQuery("Rank.findAll"));
        return (List<RankDto>)dg.get();
    }

    @Override
    public List<ProjectTypeDto> getProjectType() {
        Log.getLogger().info("getProjectType called");
        DatabaseGetter<ProjectType> dg = new DatabaseGetter<ProjectType>(createNamedQuery("ProjectType.findAll"));
        return (List<ProjectTypeDto>)dg.get();
    }

    @Override
    public List<TestTypeDto> getTestType() {
        Log.getLogger().info("getTestType called");
        DatabaseGetter<TestType> dg = new DatabaseGetter<TestType>(createNamedQuery("TestType.findAll"));
        return (List<TestTypeDto>)dg.get();
    }

    @Override
    public List<ManagerTypeDto> getManagerType() {
        Log.getLogger().info("getManagerType called");
        DatabaseGetter<ManagerType> dg = new DatabaseGetter<ManagerType>(createNamedQuery("ManagerType.findAll"));
        return (List<ManagerTypeDto>)dg.get();
    }

    @Override
    public List<TechDto> getTech() {
        Log.getLogger().info("getTech called");
        DatabaseGetter<Tech> dg = new DatabaseGetter<Tech>(createNamedQuery("Tech.findAll"));
        return (List<TechDto>)dg.get();
    }

    @Override
    public List<CustomerDto> getCustomer() {
        Log.getLogger().info("getCustomer called");
        DatabaseGetter<Customer> dg = new DatabaseGetter<Customer>(createNamedQuery("Customer.findAll"));
        return (List<CustomerDto>)dg.get();
    }

    @Override
    public List<CommentDto> getComment() {
        Log.getLogger().info("getComment called");
        DatabaseGetter<Comment> dg = new DatabaseGetter<Comment>(createNamedQuery("Comment.findAll"));
        return (List<CommentDto>)dg.get();
    }

    @Override
    public List<ProjectDto> getProject() {
        Log.getLogger().info("getProject called");
        DatabaseGetter<Project> dg = new DatabaseGetter<Project>(createNamedQuery("Project.findAll"));
        return (List<ProjectDto>)dg.get();
    }

    @Override
    public List<TaskDto> getTask() {
        Log.getLogger().info("getTasks called");
        DatabaseGetter<Task> dg = new DatabaseGetter<Task>(createNamedQuery("Task.findAll"));
        return (List<TaskDto>)dg.get();
    }

    @Override
    public List<? extends Dto> get(Class clazz) {
        if (clazz.equals(EmployeeDto.class)) {
            return getEmployee();
        }
        if (clazz.equals(DeveloperDto.class)){
            return getDeveloper();
        }
        if (clazz.equals(ManagerDto.class)){
            return getManager();
        }
        if (clazz.equals(TesterDto.class)){
            return getTester();
        }
        if (clazz.equals(CustomerAgentDto.class)) {
            return getCustomerAgent();
        }
        if (clazz.equals(OfficeDto.class)) {
            return getOffice();
        }
        if (clazz.equals(PriorityDto.class)){
            return getPriority();
        }
        if (clazz.equals(WorkflowDto.class)){
            return getWorkflow();
        }
        if (clazz.equals(RankDto.class)){
            return getRank();
        }
        if (clazz.equals(ProjectTypeDto.class)){
            return getProjectType();
        }
        if (clazz.equals(TestTypeDto.class)){
            return getTestType();
        }
        if (clazz.equals(ManagerTypeDto.class)){
            return getManagerType();
        }
        if (clazz.equals(TechDto.class)){
            return getTech();
        }
        if (clazz.equals(CustomerDto.class)){
            return getCustomer();
        }
        if (clazz.equals(CommentDto.class)){
            return getComment();
        }
        if (clazz.equals(ProjectDto.class)){
            return getProject();
        }
        if (clazz.equals(TaskDto.class)){
            return getTask();
        }
        return null;
    }

    // ----------------- Old

    @Override
    public void test() {
        Log.getLogger().info("TEST!");
    }
}
