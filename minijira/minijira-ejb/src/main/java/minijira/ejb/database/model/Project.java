package minijira.ejb.database.model;

import minijira.ejb.database.model.joint.ProjectSkill;
import minijira.ejbapi.dto.*;

import javax.ejb.EJB;
import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import java.util.logging.Logger;

/**
 * Created by  Alexey Gorovoy
 * Date:    10.09.13
 * Time:    16:39
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "projects")
@NamedQueries({
        @NamedQuery(name = "Project.findAll", query = "select p from Project p")
})
public class Project implements ModelEntity {
    @Id
    private int id;

    private String title;
    private String description;

    @ManyToOne
    private Person leader;

    @ManyToOne
    private Customer customer;

    @OneToMany (mappedBy = "project")
    private Collection<ProjectSkill> skills;

    public Project () {}

    @EJB
    @Transient
    @PersistenceContext
    EntityManager em;

    @Transient
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Project (ProjectDto projectDto) {
        this.id = projectDto.getId();
        this.title = projectDto.getTitle();
        this.description = projectDto.getDescription();
        this.leader =  new Person (projectDto.getLeader());
        this.customer = new Customer (projectDto.getCustomer());
        logger.severe("Project created: " + id + ", " + title + ", "
                    + description + ", " + leader.getName());
    }

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

    public Person getLeader() {
        return leader;
    }

    public void setLeader(Person leader_id) {
        this.leader = leader_id;
    }

    public Collection<ProjectSkill> getSkills() {
        return skills;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addProjectSkill(ProjectSkill projectSkill){
        if (skills == null) {
            skills = new LinkedList<ProjectSkill>();
        }
        skills.add(projectSkill);
    }

    @Override
    public Dto getDto() {

        List<SkillDto> skillDtos = new LinkedList<SkillDto>();
        for (ProjectSkill ps : skills ) {
            skillDtos.add((SkillDto) ps.getSkill().getDto());
        }

        return new ProjectDto(id, title, description,
                            (PersonDto)leader.getDto(),
                            (CustomerDto)customer.getDto(), skillDtos);
    }
}
