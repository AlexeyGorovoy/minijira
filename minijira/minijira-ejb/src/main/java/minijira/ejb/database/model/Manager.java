package minijira.ejb.database.model;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.EmployeeDto;
import minijira.ejbapi.dto.ManagerDto;
import minijira.ejbapi.dto.ManagerTypeDto;

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

    @Override
    public Dto getDto() {
        return new ManagerDto((EmployeeDto)employee.getDto(), (ManagerTypeDto)type.getDto());
    }

}
