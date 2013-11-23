package ejb.database.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by  Alexey Gorovoy
 * Date:    10.09.13
 * Time:    16:39
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "project")
@NamedQueries({
        @NamedQuery(name = "Project.findAll", query = "select p from Project p")
})
@NamedStoredProcedureQueries( {
        @NamedStoredProcedureQuery(name = "Project.findByTechSP",
                procedureName = "findProjectByTech",
                resultClasses = Project.class,
                parameters = {
                        @StoredProcedureParameter(name = "tech_id", type = Integer.class, mode = ParameterMode.IN)
                }),
        @NamedStoredProcedureQuery(name = "Project.findByManagersSP",
                procedureName = "findProjectByManagers",
                resultClasses = Project.class,
                parameters = {
                        @StoredProcedureParameter(name = "employee_id", type = Integer.class, mode = ParameterMode.IN)
                })
})
public class Project implements ModelEntity {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "dev_lead_id")
    private Developer dev_leader;


    @ManyToOne
    @JoinColumn(name = "test_lead_id")
    private Tester test_leader;


    @ManyToOne
    @JoinColumn(name = "pm_id")
    private Manager pm;

    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "project_type_id")
    private ProjectType type;

    /*
    // Not a columns!

    @OneToMany (mappedBy = "project")
    List<ProjectEmployeeJoint> employeeJoint;

    @OneToMany (mappedBy = "project")
    List<ProjectTechJoint> techJoint;
    */
    ///

    public Project () {}

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

    public Developer getDev_leader() {
        return dev_leader;
    }

    public void setDev_leader(Developer dev_leader) {
        this.dev_leader = dev_leader;
    }

    public Tester getTest_leader() {
        return test_leader;
    }

    public void setTest_leader(Tester test_leader) {
        this.test_leader = test_leader;
    }

    public Manager getPm() {
        return pm;
    }

    public void setPm(Manager pm) {
        this.pm = pm;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }
}
