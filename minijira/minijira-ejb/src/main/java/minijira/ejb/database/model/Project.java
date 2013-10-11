package minijira.ejb.database.model;


import minijira.ejb.database.model.joint.ProjectEmployeeJoint;
import minijira.ejb.database.model.joint.ProjectTechJoint;
import minijira.ejbapi.dto.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
        @NamedStoredProcedureQuery(name = "Project.findByEmployeeSP",
                procedureName = "findProjectByEmployee",
                resultClasses = Project.class,
                parameters = {
                        @StoredProcedureParameter(name = "employee_id", type = Integer.class, mode = ParameterMode.IN)
                })
})
public class Project implements ModelEntity {
    @Id
    @Column(name = "project_id")
    private int id;

    private String title;
    private String description;

    @Temporal(value = TemporalType.DATE)
    private Date date_start;

    @Temporal(value = TemporalType.DATE)
    private Date date_end;

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
    @JoinColumn(name = "customer_agent_id")
    private CustomerAgent customer_agent;

    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "project_type_id")
    private ProjectType type;


    // Not a columns!

    @OneToMany (mappedBy = "project")
    List<ProjectEmployeeJoint> employeeJoint;

    @OneToMany (mappedBy = "project")
    List<ProjectTechJoint> techJoint;

    ///

    public Project () {}

    @Override
    public Dto getDto() {
        return new ProjectDto(id, title, description, date_start, date_end, (DeveloperDto) dev_leader.getDto(),
                    (TesterDto)test_leader.getDto(), (ManagerDto) pm.getDto(),
                    (CustomerAgentDto)customer_agent.getDto(),
                    (CustomerDto)customer.getDto(), (ProjectTypeDto)type.getDto());
    }
}
