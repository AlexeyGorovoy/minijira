package minijira.ejbapi.dto.joint;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.EmployeeDto;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 06.10.13
 * Time: 19:59
 * Email: alexey.gorovoy.work@gmail.com
 */
public class ProjectEmployeeDto implements Dto {

    private EmployeeDto employee;

    private boolean active;

    public ProjectEmployeeDto(EmployeeDto employee, boolean active) {
        this.employee = employee;
        this.active = active;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
