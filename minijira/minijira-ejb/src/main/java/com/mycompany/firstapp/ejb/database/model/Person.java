package com.mycompany.firstapp.ejb.database.model;

import com.mycompany.firstapp.ejb.database.model.joint.PersonSkill;
import com.mycompany.firstapp.ejbapi.dto.Dto;
import com.mycompany.firstapp.ejbapi.dto.PersonDto;
import com.mycompany.firstapp.ejbapi.dto.SkillDto;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    10.09.13
 * Time:    16:31
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "people")
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "select p from Person p"),
        @NamedQuery(name = "Person.findByEmail", query = "select p from Person p where p.mail = :email")
})
public class Person implements ModelEntity {
    @Id
    private int id;

    private String name;
    private String surname;
    private String mail;
    private String skype;
    private String site;
    private String password;

    @OneToMany (mappedBy = "person")
    private Collection<PersonSkill> skills;

    public Person() {}

    public Person (PersonDto personDto) {
        this.id = personDto.getId();
        this.name = personDto.getName();
        this.surname = personDto.getSurname();
        this.mail = personDto.getMail();
        this.skype = personDto.getSkype();
        this.site = personDto.getSite();
        this.password = personDto.getPassword();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addPersonSkill(PersonSkill personSkill) {
        if (skills == null) {
            skills = new LinkedList<PersonSkill>();
        }

        skills.add(personSkill);
    }

    public Collection<PersonSkill> getSkills() {
        return skills;
    }

    @Override
    public Dto getDto() {

        List<SkillDto> skillDtos = new LinkedList<SkillDto>();
        for (PersonSkill ps : skills ) {
            skillDtos.add((SkillDto)ps.getSkill().getDto());
        }
        return new PersonDto(id, name, surname, mail, skype, site, password, skillDtos);
    }
}
