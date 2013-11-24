package ejb.database.model;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:48
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "tech")
@NamedQueries({
        @NamedQuery(name = "Tech.findAll", query = "select t from Tech t"),
        @NamedQuery(name = "Tech.connection", query = "select d from Developer d where d.mainTech = :param")
})
public class Tech implements ModelEntity{
    @Id
    @Column(name = "tech_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;
    String description;

    public Tech() {}

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
