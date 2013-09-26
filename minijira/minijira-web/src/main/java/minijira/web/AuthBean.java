package minijira.web;

import minijira.ejbapi.dto.PersonDto;

/**
 * Created with IntelliJ IDEA.
 * User: over
 * Date: 22.09.13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class AuthBean {

    private PersonDto tmpPerson;
    private String email,password;
    private boolean logged;
    private boolean admin;
    private SampleBean sample;
    public AuthBean() {
        logged = false;
        admin = false;
    }

    public void setSample(SampleBean s) {
        sample = s;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public PersonDto getTmpPerson() {
        return tmpPerson;
    }

    public void setTmpPerson(PersonDto tmpPerson) {
        this.tmpPerson = tmpPerson;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void signin() {
        tmpPerson = sample.getDatabaseInterface().getPersonByEmail(email);
        if (tmpPerson != null)
            if (tmpPerson.getPassword().equals(password)) logged = true;
        if (logged == true) {
            if (tmpPerson.getName().equals("Alexander") && tmpPerson.getSurname().equals("Savitski"))  admin = true;
            if (tmpPerson.getName().equals("Alexey") && tmpPerson.getSurname().equals("Gorovoy"))  admin = true;
        }
    }

    public void logout() {
        logged = false;
        admin = false;
    }


}
