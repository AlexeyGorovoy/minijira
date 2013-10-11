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
public class DeveloperTechId implements Serializable {

    @Id
    private int developer_id;

    @Id
    private int tech_id;

    @Override
    public int hashCode() {
        return developer_id + tech_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeveloperTechId) {
            DeveloperTechId anotherId = (DeveloperTechId)o;
            return (developer_id == anotherId.developer_id) && ( tech_id == anotherId.tech_id);
        }
        return false;
    }
}
