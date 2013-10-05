package minijira.web;

import minijira.ejbapi.DatabaseController;
import minijira.ejbapi.dto.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    10:01
 * Email:   alexey.gorovoy.work@gmail.com
 */
@SuppressWarnings(value = "unchecked")
@ManagedBean
public class DatabaseBean {

    @EJB
    DatabaseController dc;

    public List<CommentDto> getComments() {
        return (List<CommentDto>)dc.getComment();
    }

    public List<CommentDto> getCommentsByProject(int project_id) {
        return (List<CommentDto>)dc.getCommentByProject(project_id);
    }

    // Stored procedures
    public List<CommentDto> getCommentsByProjectSP(int project_id) {
        return (List<CommentDto>)dc.findCommentByProjectSP(project_id);
    }

    public List<ProjectDto> findProjectsByEmployeeSP(int employee_id) {
        return (List<ProjectDto>)dc.findProjectByEmployeeSP(employee_id);
    }

    public List<ProjectDto> findProjectsByTechSP(int tech_id) {
        return (List<ProjectDto>)dc.findProjectByTechSP(tech_id);
    }
    ///

    public List<EmployeeDto> getEmployees () {
        return (List<EmployeeDto>)dc.get(EmployeeDto.class);
    }

    public List<TesterDto> getTesters () {
        return (List<TesterDto>)dc.get(TesterDto.class);
    }

    public List<ManagerDto> getManagers () {
        return (List<ManagerDto>)dc.get(ManagerDto.class);
    }

    public List<CustomerAgentDto> getCustomerAgents () {
        return (List<CustomerAgentDto>)dc.get(CustomerAgentDto.class);
    }

    public List<DeveloperDto> getDevelopers () {
        return (List<DeveloperDto>)dc.get(DeveloperDto.class);
    }

    public List<OfficeDto> getOffices () {
        return (List<OfficeDto>)dc.get(OfficeDto.class);
    }

    public List<PriorityDto> getPriorities() {
        return (List<PriorityDto>)dc.get(PriorityDto.class);
    }

    public List<WorkflowDto> getWorkflows() {
        return (List<WorkflowDto>)dc.get(WorkflowDto.class);
    }

    public List<RankDto> getRanks() {
        return (List<RankDto>)dc.get(RankDto.class);
    }

    public List<ProjectTypeDto> getProjectTypes() {
        return (List<ProjectTypeDto>)dc.get(ProjectTypeDto.class);
    }

    public List<TestTypeDto> getTestTypes() {
        return (List<TestTypeDto>)dc.get(TestTypeDto.class);
    }

    public List<ManagerTypeDto> getManagerTypes() {
        return (List<ManagerTypeDto>)dc.get(ManagerTypeDto.class);
    }

    public List<TechDto> getTechs() {
        return (List<TechDto>)dc.get(TechDto.class);
    }

    public List<CustomerDto> getCustomers() {
        return (List<CustomerDto>)dc.get(CustomerDto.class);
    }

    public List<ProjectDto> getProjects() {
        return (List<ProjectDto>)dc.get(ProjectDto.class);
    }

    public List<TaskDto> getTasks() {
        return (List<TaskDto>)dc.get(TaskDto.class);
    }
}
