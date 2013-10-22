package ejb.database;

import ejb.database.model.*;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
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

    EntityManager em;

    @PostConstruct
    public void init() {
        try {
            Log.setup();
            Log.getLogger().info("DatabaseController.init() called");
        } catch (IOException ex) {

        }

    }

    private void initEM() {
        if (em == null) {
            EntityManagerFactory entityMangerFactory = Persistence.createEntityManagerFactory("minijira");
            em = entityMangerFactory.createEntityManager();
        }
    }

    public Query createNamedQuery(String queryName) {
        initEM();
        Log.getLogger().info("createNamedQuery - " + queryName + ", em = " + em);
        return em.createNamedQuery(queryName);
    }


    @Override
    public List<Comment> getCommentByProject(int project_id) {
        Log.getLogger().info("getCommentByProject called, project_id: " + project_id);
        Query q = createNamedQuery("Comment.findByProject");
        q.setParameter("project_id", project_id);
        DatabaseGetter<Comment> dg = new DatabaseGetter<Comment>(q);
        return dg.get();
    }
    // Stored procedures calls
    @Override
    public List<Comment> findCommentByProjectSP(int project_id) {
        Log.getLogger().info("SP findCommentByProjectSP called, project_id: " + project_id);

        Query q = em.createNamedStoredProcedureQuery("Comment.findByProjectSP");
        q.setParameter("project_id", project_id);
        DatabaseGetter<Comment> dg = new DatabaseGetter<Comment>(q);
        return dg.get();
    }

    @Override
    public List<Project> findProjectByTechSP(int tech_id) {
        Log.getLogger().info("SP findProjectByTechSP called, tech_id: " + tech_id);

        Query q = em.createNamedStoredProcedureQuery("Project.findByTechSP");
        q.setParameter("tech_id", tech_id);
        DatabaseGetter<Project> dg = new DatabaseGetter<Project>(q);
        return dg.get();
    }

    @Override
    public List<Project> findProjectByEmployeeSP(int employee_id) {
        Log.getLogger().info("SP findProjectByEmployeeSP called, employee_id: " + employee_id);

        Query q = em.createNamedStoredProcedureQuery("Project.findByEmployeeSP");
        q.setParameter("employee_id", employee_id);
        DatabaseGetter<Project> dg = new DatabaseGetter<Project>(q);
        return dg.get();
    }

    ///

    @Override
    public List<Employee> getEmployee() {
        Log.getLogger().info("getEmployee called");
        DatabaseGetter<Employee> dg = new DatabaseGetter<Employee>(createNamedQuery("Employee.findAll"));
        return dg.get();
    }

    @Override
    public List<Developer> getDeveloper() {
        Log.getLogger().info("getDeveloper called");
        DatabaseGetter<Developer> dg = new DatabaseGetter<Developer>(createNamedQuery("Developer.findAll"));
        return dg.get();
    }

    @Override
    public List<Tester> getTester() {
        Log.getLogger().info("getTester called");
        DatabaseGetter<Tester> dg = new DatabaseGetter<Tester>(createNamedQuery("Tester.findAll"));
        return dg.get();
    }

    @Override
    public List<Manager> getManager() {
        Log.getLogger().info("getManager called");
        DatabaseGetter<Manager> dg = new DatabaseGetter<Manager>(createNamedQuery("Manager.findAll"));
        return dg.get();
    }

    @Override
    public List<CustomerAgent> getCustomerAgent() {
        Log.getLogger().info("getCustomerAgent called");
        DatabaseGetter<CustomerAgent> dg = new DatabaseGetter<CustomerAgent>(createNamedQuery("CustomerAgent.findAll"));
        return dg.get();
    }

    @Override
    public List<Office> getOffice() {
        Log.getLogger().info("getOffice called");
        DatabaseGetter<Office> dg = new DatabaseGetter<Office>(createNamedQuery("Office.findAll"));
        return dg.get();
    }

    @Override
    public List<Priority> getPriority() {
        Log.getLogger().info("getPriority called");
        DatabaseGetter<Priority> dg = new DatabaseGetter<Priority>(createNamedQuery("Priority.findAll"));
        return dg.get();
    }

    @Override
    public List<Workflow> getWorkflow() {
        Log.getLogger().info("getWorkflow called");
        DatabaseGetter<Workflow> dg = new DatabaseGetter<Workflow>(createNamedQuery("Workflow.findAll"));
        return dg.get();
    }

    @Override
    public List<Rank> getRank() {
        Log.getLogger().info("getRank called");
        DatabaseGetter<Rank> dg = new DatabaseGetter<Rank>(createNamedQuery("Rank.findAll"));
        return dg.get();
    }

    @Override
    public List<ProjectType> getProjectType() {
        Log.getLogger().info("getProjectType called");
        DatabaseGetter<ProjectType> dg = new DatabaseGetter<ProjectType>(createNamedQuery("ProjectType.findAll"));
        return dg.get();
    }

    @Override
    public List<TestType> getTestType() {
        Log.getLogger().info("getTestType called");
        DatabaseGetter<TestType> dg = new DatabaseGetter<TestType>(createNamedQuery("TestType.findAll"));
        return dg.get();
    }

    @Override
    public List<ManagerType> getManagerType() {
        Log.getLogger().info("getManagerType called");
        DatabaseGetter<ManagerType> dg = new DatabaseGetter<ManagerType>(createNamedQuery("ManagerType.findAll"));
        return dg.get();
    }

    @Override
    public List<Tech> getTech() {
        Log.getLogger().info("getTech called");
        DatabaseGetter<Tech> dg = new DatabaseGetter<Tech>(createNamedQuery("Tech.findAll"));
        return dg.get();
    }

    @Override
    public List<Customer> getCustomer() {
        Log.getLogger().info("getCustomer called");
        DatabaseGetter<Customer> dg = new DatabaseGetter<Customer>(createNamedQuery("Customer.findAll"));
        return dg.get();
    }

    @Override
    public List<Comment> getComment() {
        Log.getLogger().info("getComment called");
        DatabaseGetter<Comment> dg = new DatabaseGetter<Comment>(createNamedQuery("Comment.findAll"));
        return dg.get();
    }

    @Override
    public List<Project> getProject() {
        Log.getLogger().info("getProject called");
        //DatabaseGetter<Project> dg = new DatabaseGetter<Project>(createNamedQuery("Project.findAll"));

        Query q = createNamedQuery("Project.findAll");
        return q.getResultList();
    }

    @Override
    public List<Task> getTask() {
        Log.getLogger().info("getTasks called");
        DatabaseGetter<Task> dg = new DatabaseGetter<Task>(createNamedQuery("Task.findAll"));
        return dg.get();
    }

    @Override
    public List<? extends ModelEntity> get(Class clazz) {
        if (clazz.equals(Employee.class)) {
            return getEmployee();
        }
        if (clazz.equals(Developer.class)){
            return getDeveloper();
        }
        if (clazz.equals(Manager.class)){
            return getManager();
        }
        if (clazz.equals(Tester.class)){
            return getTester();
        }
        if (clazz.equals(CustomerAgent.class)) {
            return getCustomerAgent();
        }
        if (clazz.equals(Office.class)) {
            return getOffice();
        }
        if (clazz.equals(Priority.class)){
            return getPriority();
        }
        if (clazz.equals(Workflow.class)){
            return getWorkflow();
        }
        if (clazz.equals(Rank.class)){
            return getRank();
        }
        if (clazz.equals(ProjectType.class)){
            return getProjectType();
        }
        if (clazz.equals(TestType.class)){
            return getTestType();
        }
        if (clazz.equals(ManagerType.class)){
            return getManagerType();
        }
        if (clazz.equals(Tech.class)){
            return getTech();
        }
        if (clazz.equals(Customer.class)){
            return getCustomer();
        }
        if (clazz.equals(Comment.class)){
            return getComment();
        }
        if (clazz.equals(Project.class)){
            return getProject();
        }
        if (clazz.equals(Task.class)){
            return getTask();
        }
        return null;
    }

    // more finders...

    @Override
    public Project find(int id) {
        Log.getLogger().info("tClass = " + Project.class + " id = " + id + "em = " + em);
        return em.getReference(Project.class, id);
    }

    @Override
    public ProjectType findProjectType(int id) {
        Log.getLogger().info("tClass = " + ProjectType.class + " id = " + id + "em = " + em);
        return em.getReference(ProjectType.class, id);
    }

    @Override
    public <T> T merge(T tObject) {
        return em.merge(tObject);
    }

    // ----------------- Old

    @Override
    public void test() {
        Log.getLogger().info("TEST!");
    }
}
