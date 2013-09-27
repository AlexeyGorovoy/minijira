package minijira.ejb.database.model;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.PriorityDto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:00
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "Priority.findAll", query = "select p from Priority p")
)
@Table(name = "priority")
public class Priority implements ModelEntity {

    @Id
    @Column(name = "priority_id")
    int id;

    String title;

    public Priority() {
    }

    public Priority(PriorityDto dto) {
        id = dto.getId();
        title = dto.getTitle();
    }

    @Override
    public Dto getDto() {
        return new PriorityDto(id, title);
    }

}
