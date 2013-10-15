package ejb.database.model;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    10.09.13
 * Time:    13:46
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "customer")
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
})
public class Customer implements ModelEntity{

    @Id
    @Column (name = "customer_id")
    private int id;

    private String title;
    private String description;
    private String address;
    private String info;

    public Customer() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
