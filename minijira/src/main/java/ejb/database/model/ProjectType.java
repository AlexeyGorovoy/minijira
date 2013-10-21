package ejb.database.model;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:48
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "project_type")
@NamedQueries(
        @NamedQuery(name = "ProjectType.findAll", query = "select pt from ProjectType pt")
)
public class ProjectType implements ModelEntity{
    @Id
    @Column(name = "project_type_id")
    int id;

    String title;
    String description;

    public ProjectType() {}

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
