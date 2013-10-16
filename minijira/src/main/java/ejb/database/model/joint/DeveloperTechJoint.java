package ejb.database.model.joint;

import javax.persistence.*;

import ejb.database.model.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 06.10.13
 * Time: 0:25
 * Email: alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "dev_tech_joint")
@IdClass(DeveloperTechId.class)
public class DeveloperTechJoint implements ModelEntity {
    @Id
    int developer_id;

    @Id
    int dev_tech_id;

    int experience;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="developer_id", referencedColumnName="employee_id")
    private Developer developer;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="dev_tech_id", referencedColumnName="dev_tech_id")
    private Tech tech;
}
