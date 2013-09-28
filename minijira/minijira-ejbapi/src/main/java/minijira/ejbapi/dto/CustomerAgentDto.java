package minijira.ejbapi.dto;

/**
 * Created by  Alexey Gorovoy
 * Date:    28.09.13
 * Time:    18:47
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class CustomerAgentDto implements Dto {
    EmployeeDto employee;
    CustomerDto customer;

    public CustomerAgentDto(EmployeeDto employee, CustomerDto customer) {
        this.employee = employee;
        this.customer = customer;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}
