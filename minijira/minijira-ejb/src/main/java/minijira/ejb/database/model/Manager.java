package minijira.ejb.database.model;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    28.09.13
 * Time:    18:31
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "manager")
@NamedQueries(
        @NamedQuery(name = "Manager.findAll", query = "select m from Manager m")
)
public class Manager implements ModelEntity{

    @Id
    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "manager_type_id", nullable = false)
    ManagerType type;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }
}
