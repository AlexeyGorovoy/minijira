package minijira.ejb.database.model;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.RankDto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:33
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@NamedQueries(
        @NamedQuery( name = "Rank.findAll", query = "select r from Rank r")
)
public class Rank implements ModelEntity{

    @Id
    @Column(name = "rank_id")
    int id;

    String title;

    public Rank () {}

    public Rank (RankDto dto) {
        id = dto.getId();
        title = dto.getTitle();
    }

    @Override
    public Dto getDto() {
        return new RankDto(id, title);
    }
}
