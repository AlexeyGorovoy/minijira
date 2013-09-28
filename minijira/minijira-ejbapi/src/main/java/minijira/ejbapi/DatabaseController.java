package minijira.ejbapi;

import minijira.ejbapi.dto.*;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    13:38
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Local
public interface DatabaseController {

    List<? extends Dto> get(Class clazz);

    List<EmployeeDto> getEmployee();
    List<DeveloperDto> getDeveloper();
    List<TesterDto> getTester();
    List<ManagerDto> getManager();
    List<CustomerAgentDto> getCustomerAgent();
    List<OfficeDto> getOffice();
    List<PriorityDto> getPriority();
    List<WorkflowDto> getWorkflow();
    List<RankDto> getRank();
    List<ProjectTypeDto> getProjectType();
    List<TestTypeDto> getTestType();
    List<ManagerTypeDto> getManagerType();
    List<TechDto> getTech();
    List<CustomerDto> getCustomer();


    // --------------- Old

    List<PersonDto> getPeople();
    List<SkillDto> getSkills();
    List<ProjectDto> getProjects();

    void newSkill(SkillDto skillDto);
    void newProject(ProjectDto projectDto);
    void newPerson(PersonDto personDto);

    void updateSkill(SkillDto skillDto);
    void updateProject(ProjectDto projectDto);
    void updatePerson(PersonDto personDto);

    void removeSkill(int id);
    void removeProject(int id);
    void removePerson(int id);

    void addSkillToPerson(SkillDto skillDto, PersonDto personDto);
    void addSkillToProject(SkillDto skillDto, ProjectDto projectDto);

    void removeSkillFromPerson(int skill_id, int person_id);
    void removeSkillFromProject(int skill_id, int project_id);

    PersonDto getPersonByEmail(String email);

    void test();
}
