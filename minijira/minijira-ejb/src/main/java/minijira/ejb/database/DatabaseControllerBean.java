package minijira.ejb.database;

import minijira.ejb.database.model.*;
import minijira.ejb.database.model.joint.PersonSkill;
import minijira.ejb.database.model.joint.ProjectSkill;
import minijira.ejb.util.Log;
import minijira.ejbapi.DatabaseController;
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
@Local(value = DatabaseController.class)
@Stateless
public class DatabaseControllerBean implements DatabaseController {


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
    public List<DeveloperDto> getDeveloper() {
        Log.getLogger().info("getDeveloper called");
        DatabaseGetter<Developer> dg = new DatabaseGetter<Developer>(createNamedQuery("Developer.findAll"));
        return (List<DeveloperDto>)dg.get();
    }

    @Override
    public List<TesterDto> getTester() {
        Log.getLogger().info("getTester called");
        DatabaseGetter<Tester> dg = new DatabaseGetter<Tester>(createNamedQuery("Tester.findAll"));
        return (List<TesterDto>)dg.get();
    }

    @Override
    public List<ManagerDto> getManager() {
        Log.getLogger().info("getManager called");
        DatabaseGetter<Manager> dg = new DatabaseGetter<Manager>(createNamedQuery("Manager.findAll"));
        return (List<ManagerDto>)dg.get();
    }

    @Override
    public List<CustomerAgentDto> getCustomerAgent() {
        Log.getLogger().info("getCustomerAgent called");
        DatabaseGetter<CustomerAgent> dg = new DatabaseGetter<CustomerAgent>(createNamedQuery("CustomerAgent.findAll"));
        return (List<CustomerAgentDto>)dg.get();
    }

    @Override
    public List<OfficeDto> getOffice() {
        Log.getLogger().info("getOffice called");
        DatabaseGetter<Office> dg = new DatabaseGetter<Office>(createNamedQuery("Office.findAll"));
        return (List<OfficeDto>)dg.get();
    }

    @Override
    public List<PriorityDto> getPriority() {
        Log.getLogger().info("getPriority called");
        DatabaseGetter<Priority> dg = new DatabaseGetter<Priority>(createNamedQuery("Priority.findAll"));
        return (List<PriorityDto>)dg.get();
    }

    @Override
    public List<WorkflowDto> getWorkflow() {
        Log.getLogger().info("getWorkflow called");
        DatabaseGetter<Workflow> dg = new DatabaseGetter<Workflow>(createNamedQuery("Workflow.findAll"));
        return (List<WorkflowDto>)dg.get();
    }

    @Override
    public List<RankDto> getRank() {
        Log.getLogger().info("getRank called");
        DatabaseGetter<Rank> dg = new DatabaseGetter<Rank>(createNamedQuery("Rank.findAll"));
        return (List<RankDto>)dg.get();
    }

    @Override
    public List<ProjectTypeDto> getProjectType() {
        Log.getLogger().info("getProjectType called");
        DatabaseGetter<ProjectType> dg = new DatabaseGetter<ProjectType>(createNamedQuery("ProjectType.findAll"));
        return (List<ProjectTypeDto>)dg.get();
    }

    @Override
    public List<TestTypeDto> getTestType() {
        Log.getLogger().info("getTestType called");
        DatabaseGetter<TestType> dg = new DatabaseGetter<TestType>(createNamedQuery("TestType.findAll"));
        return (List<TestTypeDto>)dg.get();
    }

    @Override
    public List<ManagerTypeDto> getManagerType() {
        Log.getLogger().info("getManagerType called");
        DatabaseGetter<ManagerType> dg = new DatabaseGetter<ManagerType>(createNamedQuery("ManagerType.findAll"));
        return (List<ManagerTypeDto>)dg.get();
    }

    @Override
    public List<TechDto> getTech() {
        Log.getLogger().info("getTech called");
        DatabaseGetter<Tech> dg = new DatabaseGetter<Tech>(createNamedQuery("Tech.findAll"));
        return (List<TechDto>)dg.get();
    }

    @Override
    public List<CustomerDto> getCustomer() {
        Log.getLogger().info("getCustomer called");
        DatabaseGetter<Customer> dg = new DatabaseGetter<Customer>(createNamedQuery("Customer.findAll"));
        return (List<CustomerDto>)dg.get();
    }


    @Override
    public List<? extends Dto> get(Class clazz) {
        if (clazz.equals(EmployeeDto.class)) {
            return getEmployee();
        }
        if (clazz.equals(DeveloperDto.class)){
            return getDeveloper();
        }
        if (clazz.equals(ManagerDto.class)){
            return getManager();
        }
        if (clazz.equals(TesterDto.class)){
            return getTester();
        }
        if (clazz.equals(CustomerAgentDto.class)) {
            return getCustomerAgent();
        }
        if (clazz.equals(OfficeDto.class)) {
            return getOffice();
        }
        if (clazz.equals(PriorityDto.class)){
            return getPriority();
        }
        if (clazz.equals(WorkflowDto.class)){
            return getWorkflow();
        }
        if (clazz.equals(RankDto.class)){
            return getRank();
        }
        if (clazz.equals(ProjectTypeDto.class)){
            return getProjectType();
        }
        if (clazz.equals(TestTypeDto.class)){
            return getTestType();
        }
        if (clazz.equals(ManagerTypeDto.class)){
            return getManagerType();
        }
        if (clazz.equals(TechDto.class)){
            return getTech();
        }
        if (clazz.equals(CustomerDto.class)){
            return getCustomer();
        }
        return null;
    }

    // ----------------- Old


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


}
