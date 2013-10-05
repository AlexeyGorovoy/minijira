package minijira.ejb.database.model.joint;

import javax.persistence.*;

import minijira.ejb.database.model.Employee;
import minijira.ejb.database.model.Project;
import minijira.ejb.database.model.Tech;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 06.10.13
 * Time: 0:25
 * Email: alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "project_tech_joint")
@IdClass(ProjectTechId.class)
public class ProjectTechJoint {
    @Id
    int project_id;

    @Id
    int tech_id;

    boolean active;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="project_id", referencedColumnName="project_id")
    private Project project;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="dev_tech_id", referencedColumnName="dev_tech_id")
    private Tech tech;


}
