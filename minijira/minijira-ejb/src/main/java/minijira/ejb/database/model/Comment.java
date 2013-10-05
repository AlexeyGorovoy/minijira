package minijira.ejb.database.model;


import minijira.ejbapi.dto.CommentDto;
import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.EmployeeDto;
import minijira.ejbapi.dto.ProjectDto;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@NamedQueries(
        @NamedQuery( name = "Comment.findAll", query = "select c from Comment c")
)
@NamedQuery( name = "Comment.findByProject", query = "select c from Comment c where c.project.id = :project_id")
@NamedStoredProcedureQuery( name = "Comment.findByProjectSP",
                            procedureName = "findCommentsByProject",
                            resultClasses = {Comment.class},
                            parameters = {
                                    @StoredProcedureParameter(name = "project_id", type = Integer.class, mode = ParameterMode.IN)
                            })
public class Comment implements ModelEntity{

    @Id
    @Column (name = "comment_id")
    int id;

    String text;

    @ManyToOne
    @JoinColumn (name = "employee_id", nullable = false)
    Employee employee;

    @ManyToOne
    @JoinColumn (name = "project_id", nullable = false)
    Project project;

    @Override
    public Dto getDto() {
        return new CommentDto(id, text, (EmployeeDto)employee.getDto(), (ProjectDto)project.getDto());
    }
}
