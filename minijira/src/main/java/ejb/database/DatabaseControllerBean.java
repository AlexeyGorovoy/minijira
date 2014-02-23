package ejb.database;

import ejb.database.model.*;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    13:35
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Local//(value = DatabaseController.class)
@Stateless
@SuppressWarnings("unchecked")
public class DatabaseControllerBean implements DatabaseController, Serializable {

    @PersistenceContext
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
            Log.getLogger().info("PersistenceContext not worked! :(");
        }
    }

    public Query createNamedQuery(String queryName) {
        initEM();
        Log.getLogger().info("createNamedQuery - " + queryName + ", em = " + em);
        return em.createNamedQuery(queryName);
    }

    // Stored procedures calls

    /*
    @Override
    public List<Comment> findCommentByTaskSP(int task_id) {
        Log.getLogger().info("SP findCommentByTaskSP called, task_id: " + task_id);

        Query q = em.createNamedStoredProcedureQuery("Comment.findByTaskSP");
        q.setParameter("task_id", task_id);
        DatabaseGetter<Comment> dg = new DatabaseGetter<Comment>(q);
        return dg.get();
    }
    */

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

    @Override
    public Employee findEmployeeByEmail(String email) {
        Log.getLogger().info("findEmployeeByEmail called, email: " + email);

        Query q = em.createNamedQuery("Employee.findByEmail");
        q.setParameter("email", email);
        DatabaseGetter<Employee> dg = new DatabaseGetter<Employee>(q);
        return (Employee)dg.get().get(0);
    }

    @Override
    public List<Project> findProjectsByManagers(int id) {
        Log.getLogger().info("findProjectsByManagers called, employee id: " + id);

        Query q = em.createNamedStoredProcedureQuery("Project.findByManagersSP");
        q.setParameter("employee_id", id);
        DatabaseGetter<Project> dg = new DatabaseGetter<Project>(q);
        return dg.get();
    }

    @Override
    public UserRole findUserRoleByEmail(String email) {
        Log.getLogger().info("findUserRoleByEmail called, email: " + email);

        Query q = em.createNamedQuery("UserRole.findByEmail");
        q.setParameter("email", email);
        DatabaseGetter<UserRole> dg = new DatabaseGetter<UserRole>(q);
        return (UserRole)(dg.get().get(0));
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
    public List<User> getUser() {
        Log.getLogger().info("getUser called");
        DatabaseGetter<User> dg = new DatabaseGetter<User>(createNamedQuery("User.findAll"));
        return dg.get();
    }

    @Override
    public List<Role> getRole() {
        Log.getLogger().info("getRole");
        DatabaseGetter<Role> dg = new DatabaseGetter<Role>(createNamedQuery("Role.findAll"));
        return dg.get();
    }

    @Override
    public List<UserRole> getUserRole() {
        Log.getLogger().info("getUserRole");
        DatabaseGetter<UserRole> dg = new DatabaseGetter<UserRole>(createNamedQuery("UserRole.findAll"));
        return dg.get();
    }

    @Override
    public List<Priority> getPriority() {
        Log.getLogger().info("getPriority called");
        DatabaseGetter<Priority> dg = new DatabaseGetter<Priority>(createNamedQuery("Priority.findAll"));
        return dg.get();
    }

    @Override
    public List<Status> getStatus() {
        Log.getLogger().info("getStatus called");
        DatabaseGetter<Status> dg = new DatabaseGetter<Status>(createNamedQuery("Status.findAll"));
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
        if (clazz.equals(User.class)) {
            return getUser();
        }
        if (clazz.equals(Role.class)) {
            return getRole();
        }
        if (clazz.equals(Priority.class)){
            return getPriority();
        }
        if (clazz.equals(Status.class)){
            return getStatus();
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
    public int calculateTask(Project project, Status status) {

        Log.getLogger().info("calculateTask called, project = " + project.getTitle() + " status = " + status.getTitle());

        Query q = createNamedQuery("Task.findByProjectAndStatus");
        q.setParameter("project", project);
        q.setParameter("status", status);
        DatabaseGetter<Task> dg = new DatabaseGetter<Task>(q);

        return dg.get().size();
    }

    @Override
    public int calculateTask(Employee assignee, Status status) {

        Log.getLogger().info("calculateTask called, employee = " + assignee.getSurname() + " status = " + status.getTitle());

        Query q = createNamedQuery("Task.findByAssigneeAndStatus");
        q.setParameter("assignee", assignee);
        q.setParameter("status", status);
        DatabaseGetter<Task> dg = new DatabaseGetter<Task>(q);

        return dg.get().size();
    }

    @Override
    public List<Task> findTasksToBeDone(Employee assignee) {
        Log.getLogger().info("findTasksToBeDone called, employee = " + assignee);

        Query q = createNamedQuery("Task.findByAssigneeAndStatus");
        q.setParameter("assignee", assignee);
        q.setParameter("status", em.find(Status.class, 1));
        DatabaseGetter<Task> dg = new DatabaseGetter<Task>(q);

        return dg.get();
    }

    @Override
    public <T extends ModelEntity> boolean hasConnections(T modelEntity) {

        String className = modelEntity.getClass().getSimpleName();
        Log.getLogger().info("hasConnections called, for a class named " + className);
        Query q = createNamedQuery(className+".connection");
        q.setParameter("param", modelEntity);
        DatabaseGetter<T> dg = new DatabaseGetter<T>(q);

        return ! dg.get().isEmpty();
    }

    @Override
    public <T> T find(Class<T> tClass, Object id) {
        Log.getLogger().info("find tClass = " + tClass + " id = " + id + "em = " + em);
        return em.find(tClass, id);
    }

    @Override
    public ProjectType findProjectType(int id) {
        Log.getLogger().info("tClass = " + ProjectType.class + " id = " + id + "em = " + em);
        return em.getReference(ProjectType.class, id);
    }


    @Override
    public ManagerType findManagerType(int id) {
        Log.getLogger().info("tClass = " + ManagerType.class + " id = " + id + "em = " + em);
        return em.getReference(ManagerType.class, id);
    }

    @Override
    public <T> T merge(T tObject) {
        T newObject = em.merge(tObject);
        flush();
        return newObject;
    }

    @Override
    public <T> void persist(T tObject) {
        em.persist(tObject);
        flush();
    }

    @Override
    public <T> void remove(T tObject) {
        em.remove( em.merge(tObject));
        flush();
        if (tObject.getClass() == Employee.class || tObject.getClass() == Project.class) {
            em.getEntityManagerFactory().getCache().evictAll();
        }
    }

    @Override
    public void flush() {
        em.flush();
    }

    // ----------------- Old

    @Override
    public void test() {
        Log.getLogger().info("TEST!");
    }
}
