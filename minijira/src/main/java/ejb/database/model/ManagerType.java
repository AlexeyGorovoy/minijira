package ejb.database.model;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:48
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "manager_type")
@NamedQueries(
        @NamedQuery(name = "ManagerType.findAll", query = "select mt from ManagerType mt")
)
public class ManagerType implements ModelEntity{
    @Id
    @Column(name = "manager_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;
    String description;

    public ManagerType() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
