package minijira.ejbapi.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 01.10.13
 * Time: 17:30
 * Email: alexey.gorovoy.work@gmail.com
 */
public class CommentDto implements Dto {

    int id;
    String text;
    EmployeeDto employee;
    ProjectDto project;

    public CommentDto(int id, String text, EmployeeDto employee, ProjectDto project) {
        this.id = id;
        this.text = text;
        this.employee = employee;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }
}
