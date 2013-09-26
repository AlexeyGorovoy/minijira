package minijira.ejb.database.model.joint;


import minijira.ejb.database.model.Person;
import minijira.ejb.database.model.Skill;
import minijira.ejbapi.dto.PersonDto;
import minijira.ejbapi.dto.SkillDto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    13.09.13
 * Time:    14:16
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "people_skills")
public class PersonSkill {


    @Id
    private int person_id;

    @Id
    private int skill_id;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="person_id", referencedColumnName="id")
    Person person;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="skill_id", referencedColumnName="id")
    private Skill skill;

    public PersonSkill() {}

    @Transient
    EntityManager em;

    public PersonSkill(PersonDto personDto, SkillDto skillDto) {
        em = Persistence.createEntityManagerFactory("minijira-ejb").createEntityManager();
        this.person = em.find(Person.class, personDto.getId());
        this.skill = em.find(Skill.class, skillDto.getId());
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
