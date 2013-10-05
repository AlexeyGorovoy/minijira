package minijira.ejb.database.model.joint;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 06.10.13
 * Time: 0:31
 * Email: alexey.gorovoy.work@gmail.com
 */
@Embeddable
public class ProjectTechId implements Serializable {

    @Id
    private int project_id;

    @Id
    private int tech_id;

    @Override
    public int hashCode() {
        return project_id + tech_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ProjectTechId) {
            ProjectTechId anotherId = (ProjectTechId)o;
            return (project_id == anotherId.project_id) && ( tech_id == anotherId.tech_id);
        }
        return false;
    }
}
