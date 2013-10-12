package minijira.ejb.database.model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
