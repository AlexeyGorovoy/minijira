package minijira.ejbapi.dto;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 05.10.13
 * Time: 17:34
 * Email: alexey.gorovoy.work@gmail.com
 */
public class TaskDto implements Dto {
    int id;
    String title;
    String description;
    Date dueto;
    boolean closed;
    ProjectDto project;
    EmployeeDto assignee;
    EmployeeDto reporter;
    PriorityDto priority;
    WorkflowDto workflow;

    public TaskDto(int id, String title, String description, Date dueto, boolean closed,
                   ProjectDto project, EmployeeDto assignee, EmployeeDto reporter,
                   PriorityDto priority, WorkflowDto workflow) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueto = dueto;
        this.closed = closed;
        this.project = project;
        this.assignee = assignee;
        this.reporter = reporter;
        this.priority = priority;
        this.workflow = workflow;
    }

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

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public EmployeeDto getAssignee() {
        return assignee;
    }

    public void setAssignee(EmployeeDto assignee) {
        this.assignee = assignee;
    }

    public EmployeeDto getReporter() {
        return reporter;
    }

    public void setReporter(EmployeeDto reporter) {
        this.reporter = reporter;
    }

    public PriorityDto getPriority() {
        return priority;
    }

    public void setPriority(PriorityDto priority) {
        this.priority = priority;
    }

    public WorkflowDto getWorkflow() {
        return workflow;
    }

    public void setWorkflow(WorkflowDto workflow) {
        this.workflow = workflow;
    }
}
