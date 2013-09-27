package minijira.ejb.database.model;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.TestTypeDto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:48
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "test_type")
@NamedQueries(
        @NamedQuery(name = "TestType.findAll", query = "select tt from TestType tt")
)
public class TestType implements ModelEntity{
    @Id
    @Column(name = "test_type_id")
    int id;

    String title;
    String description;

    public TestType() {}

    public TestType(TestTypeDto dto) {
        id = dto.getId();
        title = dto.getTitle();
        description = dto.getDescription();
    }

    @Override
    public Dto getDto() {
        return new TestTypeDto(id, title, description);
    }
}
