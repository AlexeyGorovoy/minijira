package ejb.database.model;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:33
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Status.findAll", query = "select s from Status s"),
        @NamedQuery(name = "Status.connection", query = "select t from Task t where t.status = :param")
})
public class Status implements ModelEntity{

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;
    String description;

    public Status() {}

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
