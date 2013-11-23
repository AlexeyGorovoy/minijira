package ejb.database.model;


import javax.persistence.*;

@Entity
@Table(name = "comment")
@NamedQueries(
        @NamedQuery( name = "Comment.findAll", query = "select c from Comment c")
)
@NamedQuery( name = "Comment.findByProject", query = "select c from Comment c where c.task.id = :project_id")
@NamedStoredProcedureQuery( name = "Comment.findByTaskSP",
                            procedureName = "findCommentsByTask",
                            resultClasses = {Comment.class},
                            parameters = {
                                    @StoredProcedureParameter(name = "task_id", type = Integer.class, mode = ParameterMode.IN)
                            })
public class Comment implements ModelEntity{

    @Id
    @Column (name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String text;

    @ManyToOne
    @JoinColumn (name = "employee_id", nullable = false)
    Employee employee;

    @ManyToOne
    @JoinColumn (name = "task_id", nullable = false)
    Task task;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
