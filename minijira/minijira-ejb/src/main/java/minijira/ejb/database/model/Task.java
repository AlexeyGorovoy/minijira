package minijira.ejb.database.model;

import minijira.ejbapi.dto.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 05.10.13
 * Time: 17:25
 * Email: alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "task")
@NamedQueries(
        @NamedQuery(name = "Task.findAll", query = "select t from Task t")
)
public class Task implements ModelEntity{

    @Id
    @Column (name = "task_id")
    int id;

    String title;
    String description;

    @Temporal(value = TemporalType.DATE)
    Date dueto;

    boolean closed;

    @ManyToOne
    @JoinColumn (name = "project_id")
    Project project;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    Employee assignee;


    @ManyToOne
    @JoinColumn(name = "reporter_id")
    Employee reporter;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    Priority priority;

    @ManyToOne
    @JoinColumn(name = "workflow_id")
    Workflow workflow;

    @Override
    public Dto getDto() {
        return new TaskDto(id, title, description, dueto, closed, (ProjectDto)project.getDto(),
                (EmployeeDto)assignee.getDto(), (EmployeeDto)reporter.getDto(),
                (PriorityDto)priority.getDto(), (WorkflowDto)workflow.getDto());
    }
}
