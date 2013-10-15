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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
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

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }
}
