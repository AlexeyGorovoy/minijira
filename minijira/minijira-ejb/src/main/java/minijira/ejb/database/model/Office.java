package minijira.ejb.database.model;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.OfficeDto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    10:05
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "Office.findAll", query = "select o from Office o")
)
public class Office implements ModelEntity {

    @Id
    @Column(name = "office_id")
    int id;

    String title;
    String address;

    public Office() {
    }

    public Office(OfficeDto dto) {
        id = dto.getId();
        title = dto.getTitle();
        address = dto.getAddress();
    }


    @Override
    public Dto getDto() {
        return new OfficeDto(id, title, address);
    }
}
