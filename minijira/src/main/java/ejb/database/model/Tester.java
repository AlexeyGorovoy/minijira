package ejb.database.model;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    28.09.13
 * Time:    16:54
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "tester")
@NamedQueries(
        @NamedQuery(name = "Tester.findAll", query = "select t from Tester t")
)
public class Tester implements ModelEntity{

    @Id
    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "rank_id", nullable = false)
    Rank rank;

    @ManyToOne
    @JoinColumn(name = "test_type_id", nullable = false)
    TestType type;

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

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }
}
