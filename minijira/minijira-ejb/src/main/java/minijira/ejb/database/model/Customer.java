package minijira.ejb.database.model;

import minijira.ejbapi.dto.CustomerDto;
import minijira.ejbapi.dto.Dto;

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

    public Customer(CustomerDto dto) {
        id = dto.getId();
        title = dto.getTitle();
        description = dto.getDescription();
        address = dto.getAddress();
        info = dto.getInfo();
    }

    @Override
    public Dto getDto() {
        return new CustomerDto(id, title, description, address, info);
    }
}
