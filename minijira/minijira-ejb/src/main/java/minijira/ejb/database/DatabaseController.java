package minijira.ejb.database;

import minijira.ejb.database.model.*;
import minijira.ejb.database.model.joint.PersonSkill;
import minijira.ejb.database.model.joint.ProjectSkill;
import minijira.ejb.util.Log;
import minijira.ejbapi.DatabaseControllerInterface;
import minijira.ejbapi.dto.*;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    13:35
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Local(value = DatabaseControllerInterface.class)
@Stateless
public class DatabaseController implements DatabaseControllerInterface{


    @PostConstruct
    public void init() {
        try {
            Log.setup();
        } catch (IOException ex) {

        }
    }

    @PersistenceContext
    EntityManager em;

    public Query createNamedQuery(String queryName) {
        return em.createNamedQuery(queryName);
    }

    @Override
    public List<EmployeeDto> getEmployee() {
        Log.getLogger().info("getEmployee called");
        DatabaseGetter<Employee> dg = new DatabaseGetter<Employee>(createNamedQuery("Employee.findAll"));
        return (List<EmployeeDto>)dg.get();
    }

    @Override
    public List<CustomerDto> getCustomers() {
        Log.getLogger().info("getCustomers called");
        DatabaseGetter<Customer> dg = new DatabaseGetter<Customer>(createNamedQuery("Customer.findAll"));
        return (List<CustomerDto>)dg.get();
    }

    @Override
    public List<PersonDto> getPeople() {
        Log.getLogger().info("getPeople called");
        DatabaseGetter<Person> dg = new DatabaseGetter<Person>(createNamedQuery("Person.findAll"));
        return (List<PersonDto>)dg.get();
    }

    @Override
    public List<SkillDto> getSkills() {
        Log.getLogger().info("getSkills called");
        DatabaseGetter<Skill> dg = new DatabaseGetter<Skill>(createNamedQuery("Skill.findAll"));
        return (List<SkillDto>)dg.get();
    }

    @Override
    public List<ProjectDto> getProjects() {
        Log.getLogger().info("getProjects called");
        DatabaseGetter<Project> dg = new DatabaseGetter<Project>(createNamedQuery("Project.findAll"));
        return (List<ProjectDto>)dg.get();
    }

    @Override
    public void newSkill(SkillDto skillDto) {
        Log.getLogger().info("newSkill called");
        em.persist(new Skill(skillDto));
    }


    @Override
    public void newCustomer(CustomerDto customerDto) {
        Log.getLogger().info("newCustomer called");
        em.persist(new Customer(customerDto));
    }

    @Override
    public void newPerson(PersonDto personDto) {
        Log.getLogger().info("newPerson called");
        em.persist(new Person(personDto));
    }

    @Override
    public void addSkillToPerson(SkillDto skillDto, PersonDto personDto) {
        Log.getLogger().info("addSkillToPerson called");
        em.persist(new PersonSkill(personDto, skillDto));
    }

    @Override
    public void newProject(ProjectDto projectDto) {
        Log.getLogger().info("newProject called");
        em.persist(new Project(projectDto));
    }

    @Override
    public void test() {
        Log.getLogger().info("TEST!");
    }

    @Override
    public void addSkillToProject(SkillDto skillDto, ProjectDto projectDto) {
        Log.getLogger().info("addSkillToProject called");
        em.persist(new ProjectSkill(projectDto, skillDto));
    }

    @Override
    public PersonDto getPersonByEmail(String email) {
        Query q = createNamedQuery("Person.findByEmail");
        q.setParameter("email", email);
        List<Person> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return (PersonDto)list.get(0).getDto();
        }
    }

    @Override
    public void updateSkill(SkillDto skillDto) {
        Log.getLogger().info("updateSkill called");
        em.merge(new Skill(skillDto));
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Log.getLogger().info("updateCustomer called");
        em.merge(new Customer(customerDto));
    }

    @Override
    public void updateProject(ProjectDto projectDto) {
        Log.getLogger().info("updateCustomer called");
        em.merge(new Project(projectDto));
    }

    @Override
    public void updatePerson(PersonDto personDto) {
        Log.getLogger().info("updateCustomer called");
        em.merge(new Person(personDto));
    }

    @Override
    public void removeSkill(int  id) {
        Log.getLogger().info("removeSkill called");
        em.remove(em.find(Skill.class, id));
    }

    @Override
    public void removeCustomer(int id) {
        Log.getLogger().info("removeCustomer called");
        em.remove(em.find(Customer.class, id));
    }

    @Override
    public void removeProject(int id) {
        Log.getLogger().info("removeProject called");
        em.remove(em.find(Project.class, id));
    }

    @Override
    public void removePerson(int id) {
        Log.getLogger().info("removePerson called");
        em.remove(em.find(Person.class, id));
    }

    @Override
    public void removeSkillFromPerson(int skill_id, int person_id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeSkillFromProject(int skill_id, int project_id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<? extends Dto> get(Class clazz) {
        if (clazz.equals(EmployeeDto.class)) {
            return getEmployee();
        }
        return null;
    }
}
