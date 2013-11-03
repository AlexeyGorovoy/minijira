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

    List<Comment> getCommentByProject(int project_id);

    //Stored procedures calls
    List<Comment> findCommentByProjectSP(int project_id);
    List<Project> findProjectByTechSP(int tech_id);
    List<Project> findProjectByEmployeeSP(int employee_id);
    ///

    List<Employee> getEmployee();
    List<Developer> getDeveloper();
    List<Tester> getTester();
    List<Manager> getManager();
    List<CustomerAgent> getCustomerAgent();
    List<Office> getOffice();
    List<Priority> getPriority();
    List<Workflow> getWorkflow();
    List<Rank> getRank();
    List<ProjectType> getProjectType();
    List<TestType> getTestType();
    List<ManagerType> getManagerType();
    List<Tech> getTech();
    List<Customer> getCustomer();
    List<Comment> getComment();
    List<Project> getProject();
    List<Task> getTask();


    //
    Project find(int id);
    ProjectType findProjectType(int id);
    <T> T merge(T tObject);

    void flush();
    // --------------- Old
    void test();
}
