package ejb.database.model;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:48
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "test_type")
@NamedQueries({
        @NamedQuery(name = "TestType.findAll", query = "select tt from TestType tt"),
        @NamedQuery(name = "TestType.connection", query = "select t from Tester t where t.type = :param")
})
public class TestType implements ModelEntity{
    @Id
    @Column(name = "test_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;
    String description;

    public TestType() {}

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
