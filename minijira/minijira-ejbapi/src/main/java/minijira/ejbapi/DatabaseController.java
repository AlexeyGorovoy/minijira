package minijira.ejbapi;

import minijira.ejbapi.dto.*;

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

    List<? extends Dto> get(Class clazz);

    List<CommentDto> getCommentByProject(int project_id);

    //Stored procedures calls
    List<CommentDto> findCommentByProjectSP(int project_id);
    List<ProjectDto> findProjectByTechSP(int tech_id);
    List<ProjectDto> findProjectByEmployeeSP(int employee_id);
    ///

    List<EmployeeDto> getEmployee();
    List<DeveloperDto> getDeveloper();
    List<TesterDto> getTester();
    List<ManagerDto> getManager();
    List<CustomerAgentDto> getCustomerAgent();
    List<OfficeDto> getOffice();
    List<PriorityDto> getPriority();
    List<WorkflowDto> getWorkflow();
    List<RankDto> getRank();
    List<ProjectTypeDto> getProjectType();
    List<TestTypeDto> getTestType();
    List<ManagerTypeDto> getManagerType();
    List<TechDto> getTech();
    List<CustomerDto> getCustomer();
    List<CommentDto> getComment();
    List<ProjectDto> getProject();
    List<TaskDto> getTask();


    // --------------- Old
    void test();
}
