package minijira.ejb.database.model;

import minijira.ejbapi.dto.*;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    28.09.13
 * Time:    16:54
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "customer_agent")
@NamedQueries(
        @NamedQuery(name = "CustomerAgent.findAll", query = "select ca from CustomerAgent ca")
)
public class CustomerAgent implements ModelEntity{

    @Id
    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @Override
    public Dto getDto() {
        return new CustomerAgentDto((EmployeeDto)employee.getDto(),
                (CustomerDto)customer.getDto());
    }
}
