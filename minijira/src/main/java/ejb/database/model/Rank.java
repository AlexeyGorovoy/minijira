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
        @NamedQuery(name = "Rank.findAll", query = "select r from Rank r"),
        @NamedQuery(name = "Rank.connection", query = "select e from Employee e " +
                "where e in (select t.employee from Tester t where t.rank = :param)" +
                "or e in (select d.employee from Developer d where d.rank = :param)")
})
public class Rank implements ModelEntity{

    @Id
    @Column(name = "rank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    public Rank () {}

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
}
