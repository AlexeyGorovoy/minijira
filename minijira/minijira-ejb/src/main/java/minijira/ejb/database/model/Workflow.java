package minijira.ejb.database.model;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.WorkflowDto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:33
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@NamedQueries(
        @NamedQuery( name = "Workflow.findAll", query = "select w from Workflow w")
)
public class Workflow implements ModelEntity{

    @Id
    @Column(name = "workflow_id")
    int id;

    String title;

    public Workflow () {}

    public Workflow (WorkflowDto dto) {
        id = dto.getId();
        title = dto.getTitle();
    }

    @Override
    public Dto getDto() {
        return new WorkflowDto(id, title);
    }
}
