package ejb.database;

import ejb.database.model.*;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    13:38
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Local
public interface DatabaseController {

    List <? extends ModelEntity> get(Class clazz);

    //Stored procedures calls
    Employee findEmployeeByEmail(String email);
    List<Project> findProjectByTechSP(int tech_id);
    List<Project> findProjectByEmployeeSP(int employee_id);
    ///

    List<Employee> getEmployee();
    List<Developer> getDeveloper();
    List<Tester> getTester();
    List<Manager> getManager();
    List<Priority> getPriority();
    List<Status> getStatus();
    List<Rank> getRank();
    List<ProjectType> getProjectType();
    List<TestType> getTestType();
    List<ManagerType> getManagerType();
    List<Tech> getTech();
    List<Customer> getCustomer();
    List<Comment> getComment();
    List<Project> getProject();
    List<Task> getTask();
    List<User> getUser();
    List<Role> getRole();
    List<UserRole> getUserRole();


    //
    ProjectType findProjectType(int id);
    ManagerType findManagerType(int id);
    UserRole findUserRoleByEmail(String email);
    List<Project> findProjectsByManagers(int id);


    <T> T find(Class<T> tClass, Object id);
    <T> T merge(T tObject);
    <T> void persist(T tObject);
    <T> void remove(T tObject);
    void flush();

    // --------------- Old
    void test();
}
