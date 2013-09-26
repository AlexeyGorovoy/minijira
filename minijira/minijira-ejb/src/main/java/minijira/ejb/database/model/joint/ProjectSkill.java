package minijira.ejb.database.model.joint;

import minijira.ejb.database.model.Project;
import minijira.ejb.database.model.Skill;
import minijira.ejbapi.dto.ProjectDto;
import minijira.ejbapi.dto.SkillDto;

import javax.persistence.*;

/**
 * Created by  Alexey Gorovoy
 * Date:    13.09.13
 * Time:    14:06
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "used_skills")
@IdClass(ProjectSkillId.class)
public class ProjectSkill {

    @Id
    private int project_id;

    @Id
    private int skill_id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @PrimaryKeyJoinColumn(name="project_id", referencedColumnName="id")
    private Project project;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="skill_id", referencedColumnName="id")
    private Skill skill;

    @Transient
    @PersistenceContext
    EntityManager em;

    public ProjectSkill() {}

    public ProjectSkill(ProjectDto projectDto, SkillDto skillDto) {
        em = Persistence.createEntityManagerFactory("minijira-ejb").createEntityManager();
        project_id = projectDto.getId();
        project = em.find(Project.class, projectDto.getId());
        skill_id = skillDto.getId();
        skill = em.find(Skill.class, skillDto.getId());
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
