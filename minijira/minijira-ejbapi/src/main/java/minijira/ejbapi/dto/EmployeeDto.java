package minijira.ejbapi.dto;

import java.util.Date;

/**
 * Created by  Alexey Gorovoy
 * Date:    26.09.13
 * Time:    16:00
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class EmployeeDto implements Dto {

    protected int id;
    protected String name;
    protected String surname;
    protected String phonenumber;
    protected String email;
    protected String skype;
    protected String password;
    protected Date date_hired;

    public EmployeeDto(int id, String name, String surname, String phonenumber, String email, String skype, String password, Date date_hired) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.skype = skype;
        this.password = password;
        this.date_hired = date_hired;
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
