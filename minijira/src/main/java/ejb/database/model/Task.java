package ejb.database.model;

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
@NamedQueries({
        @NamedQuery(name = "Task.findAll", query = "select t from Task t"),
        @NamedQuery(name = "Task.findByProjectAndStatus", query = "select t from Task t " +
                "                                                   where t.project = :project and t.status = :status"),
        @NamedQuery(name = "Task.findByAssigneeAndStatus", query = "select t from Task t " +
                "                                                   where t.assignee = :assignee and t.status = :status")
})
public class Task implements ModelEntity{

    @Id
    @Column (name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;
    String description;

    @Temporal(value = TemporalType.DATE)
    Date dueto;

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
    @JoinColumn(name = "status_id")
    Status status;

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

    public Date getDueto() {
        return dueto;
    }

    public void setDueto(Date dueto) {
        this.dueto = dueto;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }

    public Employee getReporter() {
        return reporter;
    }

    public void setReporter(Employee reporter) {
        this.reporter = reporter;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
