package ejb.database.model;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    28.09.13
 * Time:    16:54
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "developer")
@NamedQueries(
        @NamedQuery(name = "Developer.findAll", query = "select d from Developer d")
)
public class Developer implements ModelEntity{

    @Id
    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "rank_id", nullable = false)
    Rank rank;

    @ManyToOne
    @JoinColumn(name = "main_tech_id", nullable = false)
    Tech mainTech;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Tech getMainTech() {
        return mainTech;
    }

    public void setMainTech(Tech mainTech) {
        this.mainTech = mainTech;
    }
}
