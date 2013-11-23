package minijira.web;

import ejb.database.model.*;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 09.11.13
 * Time: 13:35
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named ("taskBean")
@SessionScoped
public class TaskBean implements Serializable {

    Priority priority;
    Status status;

    private Task task;
    private int projectId;
    private int assigneeId;
    private int reporterId;
    private int priorityId;
    private int statusId;

    @Inject
    DatabaseBean databaseBean;

    @PostConstruct
    public void init() {
        Log.getLogger().info("PostConstruct in taskBean called");
        task = new Task();
        priority = new Priority();
        status = new Status();
    }

    public String editPriority(Priority priority) {
        this.priority = priority;
        return "edit_priority";
    }

    public String savePriority() {
        databaseBean.getDc().merge(priority);
        return "priorities";
    }

    public String deletePriority(Priority priority) {
        databaseBean.getDc().remove(priority);
        return "priorities";
    }

    public String editStatus(Status status) {
        this.status = status;
        return "edit_status";
    }

    public String saveStatus() {
        databaseBean.getDc().merge(status);
        return "statuses";
    }

    public String deleteStatus(Status status) {
        databaseBean.getDc().remove(status);
        return "statuses";
    }

    public String editTask(Task task) {

        this.task = task;

        assigneeId = task.getAssignee().getId();
        reporterId = task.getReporter().getId();
        projectId = task.getProject().getId();
        priorityId = task.getPriority().getId();
        statusId = task.getStatus().getId();

        return "edit_task";
    }

    public String deleteTask(Task task) {

        databaseBean.getDc().remove(task);

        return "tasks";
    }

    public String saveTask() {

        task.setAssignee(databaseBean.getDc().find(Employee.class, assigneeId));
        task.setReporter(databaseBean.getDc().find(Employee.class, reporterId));
        task.setProject(databaseBean.getDc().find(Project.class, projectId));
        task.setPriority(databaseBean.getDc().find(Priority.class, priorityId));
        task.setStatus(databaseBean.getDc().find(Status.class, statusId));

        databaseBean.getDc().merge(task);

        return "tasks";
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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

    public DatabaseBean getDatabaseBean() {
        return databaseBean;
    }

    public void setDatabaseBean(DatabaseBean databaseBean) {
        this.databaseBean = databaseBean;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
