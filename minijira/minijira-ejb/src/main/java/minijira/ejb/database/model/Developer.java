package minijira.ejb.database.model;

import minijira.ejbapi.dto.*;

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

    @Override
    public Dto getDto() {
        return new DeveloperDto((EmployeeDto)employee.getDto(),
                                (RankDto)rank.getDto(),
                                (TechDto)mainTech.getDto());
    }
}
