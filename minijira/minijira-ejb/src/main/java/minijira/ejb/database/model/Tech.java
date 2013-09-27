package minijira.ejb.database.model;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.TechDto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:48
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "dev_tech")
@NamedQueries(
        @NamedQuery(name = "Tech.findAll", query = "select t from Tech t")
)
public class Tech implements ModelEntity{
    @Id
    @Column(name = "dev_tech_id")
    int id;

    String title;
    String description;

    public Tech() {}

    public Tech(TechDto dto) {
        id = dto.getId();
        title = dto.getTitle();
        description = dto.getDescription();
    }

    @Override
    public Dto getDto() {
        return new TechDto(id, title, description);
    }
}
