package minijira.ejbapi.dto;

/**
 * Created by  Alexey Gorovoy
 * Date:    28.09.13
 * Time:    18:37
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class ManagerDto implements Dto {
    EmployeeDto employee;
    ManagerTypeDto type;

    public ManagerDto(EmployeeDto employee, ManagerTypeDto type) {
        this.employee = employee;
        this.type = type;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public ManagerTypeDto getType() {
        return type;
    }

    public void setType(ManagerTypeDto type) {
        this.type = type;
    }
}
