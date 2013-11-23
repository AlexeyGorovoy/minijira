package ejb.database.model;

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
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee e"),
        @NamedQuery(name = "Employee.findByEmail", query = "select e from Employee e where e.email = :email")
})
public class Employee implements ModelEntity {

    @Id
    @Column (name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String surname;

    @Temporal(TemporalType.DATE)
    Date date_hired;

    String phonenumber;

    @OneToOne //(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn (name = "email")
    User user;

    String skype;
    String email;

    public Employee() {
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

    public Date getDate_hired() {
        return date_hired;
    }

    public void setDate_hired(Date date_hired) {
        this.date_hired = date_hired;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
