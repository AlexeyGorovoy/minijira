package minijira.ejbapi.dto;

import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    14:08
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class PersonDto  implements Dto{

    private int id;

    public PersonDto(String name, String surname, String mail, String skype, String site, String password) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.skype = skype;
        this.site = site;
        this.password = password;
    }

    public PersonDto() {}

    public PersonDto(int id, String name, String surname, String mail, String skype, String site, String password) {
        this(name, surname, mail, skype, site, password);
        this.id = id;
    }

    public PersonDto(int id, String name, String surname, String mail, String skype, String site, String password, List<SkillDto> skills) {
        this(id, name, surname, mail, skype, site, password);
        this.skills = skills;
    }

    private String name;
    private String surname;
    private String mail;
    private String skype;
    private String site;
    private String password;

    private List<SkillDto> skills;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SkillDto> getSkills() {
        return skills;
    }
}
