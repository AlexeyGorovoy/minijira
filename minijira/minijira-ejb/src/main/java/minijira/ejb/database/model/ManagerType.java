package minijira.ejb.database.model;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.ManagerTypeDto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:48
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "manager_type")
@NamedQueries(
        @NamedQuery(name = "ManagerType.findAll", query = "select mt from ManagerType mt")
)
public class ManagerType implements ModelEntity{
    @Id
    @Column(name = "manager_type_id")
    int id;

    String title;
    String description;

    public ManagerType() {}

    public ManagerType(ManagerTypeDto dto) {
        id = dto.getId();
        title = dto.getTitle();
        description = dto.getDescription();
    }

    @Override
    public Dto getDto() {
        return new ManagerTypeDto(id, title, description);
    }
}
