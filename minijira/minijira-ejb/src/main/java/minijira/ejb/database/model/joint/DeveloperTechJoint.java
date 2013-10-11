package minijira.ejb.database.model.joint;

import javax.persistence.*;

import minijira.ejb.database.model.*;
import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.TechDto;
import minijira.ejbapi.dto.joint.DeveloperTechDto;

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
    int tech_id;

    int experience;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="developer_id", referencedColumnName="employee_id")
    private Developer developer;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="dev_tech_id", referencedColumnName="dev_tech_id")
    private Tech tech;

    @Override
    public Dto getDto() {
        return new DeveloperTechDto((TechDto)tech.getDto(), experience);
    }
}
