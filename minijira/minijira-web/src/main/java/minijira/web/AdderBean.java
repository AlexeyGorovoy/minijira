package minijira.web;

import minijira.ejbapi.DatabaseControllerInterface;
import minijira.ejbapi.dto.CustomerDto;
import minijira.ejbapi.dto.PersonDto;
import minijira.ejbapi.dto.ProjectDto;
import minijira.ejbapi.dto.SkillDto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 * Created by  Alexey Gorovoy
 * Date:    13.09.13
 * Time:    15:34
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class AdderBean {

    @EJB
    private DatabaseControllerInterface dci;

    private SkillDto skill;

    private PersonDto person;

    private ProjectDto project;

    private CustomerDto customer;

    private String email;

    @PostConstruct
    void init() {
        person = new PersonDto();
        skill = new SkillDto();
        project = new ProjectDto();
        customer = new CustomerDto();
    }

    public void test() {
        dci.test();
    }

    public void saveSkill() {
        dci.newSkill(skill);
    }

    public void updateSkill() {
        dci.updateSkill(skill);
    }

    public void saveCustomer() {
        dci.newCustomer(customer);
    }

    public void savePerson() {
        dci.newPerson(person);
    }

    public void addSkillToPerson() {
        dci.addSkillToPerson(skill, person);
    }

    public void saveProject() {
        project.setLeader(person);
        project.setCustomer(customer);
        dci.newProject(project);
    }

    public void addSkillToProject() {
        dci.addSkillToProject(skill, project);
    }

    public void  removeSkill(int id) {
        dci.removeSkill(id);
    }

    public void getByEmail() {
        person = dci.getPersonByEmail(email);
    }

    // getters and setters


    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public SkillDto getSkill() {
        return skill;
    }

    public void setSkill(SkillDto skill) {
        this.skill = skill;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
