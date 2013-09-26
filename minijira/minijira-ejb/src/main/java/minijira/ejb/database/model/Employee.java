package minijira.ejb.database.model;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.EmployeeDto;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by  Alexey Gorovoy
 * Date:    26.09.13
 * Time:    15:55
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "employee")
@NamedQueries(
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee e")
)
public class Employee implements ModelEntity {

    @Id
    @Column (name = "employee_id")
    int id;

    String name;
    String surname;

    @Temporal(TemporalType.DATE)
    Date date_hired;

    String phonenumber;

    @Column (unique = true)
    String email;
    String skype;
    String password;

    @Override
    public Dto getDto() {
        return new EmployeeDto(id, name, surname, phonenumber, email, skype, password, date_hired);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_hired() {
        return date_hired;
    }

    public void setDate_hired(Date date_hired) {
        this.date_hired = date_hired;
    }
}
