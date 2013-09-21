package com.mycompany.firstapp.ejb.database.model;

import com.mycompany.firstapp.ejbapi.dto.CustomerDto;
import com.mycompany.firstapp.ejbapi.dto.Dto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    10.09.13
 * Time:    13:46
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "customers")
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
})
public class Customer implements ModelEntity{

    @Id
    private int id;

    private String title;
    private  String description;

    public Customer() {}

    public Customer(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.title = customerDto.getTitle();
        this.description = customerDto.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Dto getDto() {
        return new CustomerDto(id, title, description);
    }
}
