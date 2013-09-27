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

    public List<EmployeeDto> getEmployees () {
        return (List<EmployeeDto>)dc.get(EmployeeDto.class);
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
}
