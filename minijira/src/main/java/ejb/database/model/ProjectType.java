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

}
