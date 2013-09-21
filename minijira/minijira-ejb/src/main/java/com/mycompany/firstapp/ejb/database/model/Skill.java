package com.mycompany.firstapp.ejb.database.model;

import com.mycompany.firstapp.ejb.database.model.joint.PersonSkill;
import com.mycompany.firstapp.ejb.database.model.joint.ProjectSkill;
import com.mycompany.firstapp.ejbapi.dto.Dto;
import com.mycompany.firstapp.ejbapi.dto.SkillDto;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by  Alexey Gorovoy
 * Date:    10.09.13
 * Time:    16:39
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "skills")
@NamedQueries({
        @NamedQuery(name = "Skill.findAll", query = "select s from Skill s")
})
public class Skill implements ModelEntity {
    @Id
    private int id;

    private String title;
    private String description;

    @Transient
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public Skill() {

    }

    public Skill(SkillDto skillDto) {
        this.id = skillDto.getId();
        this.title = skillDto.getTitle();
        this.description = skillDto.getDescription();

        logger.severe("Skill constructor called");
    }

    @OneToMany (mappedBy = "skill", cascade = CascadeType.REMOVE)
    private Collection<ProjectSkill> projects;

    @OneToMany (mappedBy = "skill", cascade = CascadeType.REMOVE)
    private Collection<PersonSkill> people;

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

    public void addPersonSkill(PersonSkill personSkill) {
        if (people == null) {
            people = new LinkedList<PersonSkill>();
        }

        people.add(personSkill);
    }

    public void addProjectSkill(ProjectSkill projectSkill) {
        if (projects == null) {
            projects = new LinkedList<ProjectSkill>();
        }

        projects.add(projectSkill);
    }

    @Override
    public Dto getDto() {

        return new SkillDto(id, title, description);
    }
}
