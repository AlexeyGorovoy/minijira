package ejb.database.model.joint;

import javax.persistence.*;

import ejb.database.model.Employee;
import ejb.database.model.Project;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 06.10.13
 * Time: 0:25
 * Email: alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "project_employee")
@IdClass(ProjectEmployeeId.class)
public class ProjectEmployeeJoint {
    @Id
    int project_id;

    @Id
    int employee_id;

    boolean active;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="project_id", referencedColumnName="project_id")
    private Project project;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="employee_id", referencedColumnName="employee_id")
    private Employee employee;


}
