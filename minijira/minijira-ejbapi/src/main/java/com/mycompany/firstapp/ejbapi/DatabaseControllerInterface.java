package com.mycompany.firstapp.ejbapi;

import com.mycompany.firstapp.ejbapi.dto.CustomerDto;
import com.mycompany.firstapp.ejbapi.dto.PersonDto;
import com.mycompany.firstapp.ejbapi.dto.ProjectDto;
import com.mycompany.firstapp.ejbapi.dto.SkillDto;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    13:38
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Local
public interface DatabaseControllerInterface {
    List<CustomerDto> getCustomers();
    List<PersonDto> getPeople();
    List<SkillDto> getSkills();
    List<ProjectDto> getProjects();

    void newSkill(SkillDto skillDto);
    void newCustomer(CustomerDto customerDto);
    void newProject(ProjectDto projectDto);
    void newPerson(PersonDto personDto);

    void updateSkill(SkillDto skillDto);
    void updateCustomer(CustomerDto customerDto);
    void updateProject(ProjectDto projectDto);
    void updatePerson(PersonDto personDto);

    void removeSkill(int id);
    void removeCustomer(int id);
    void removeProject(int id);
    void removePerson(int id);

    void addSkillToPerson(SkillDto skillDto, PersonDto personDto);
    void addSkillToProject(SkillDto skillDto, ProjectDto projectDto);

    void removeSkillFromPerson(int skill_id, int person_id);
    void removeSkillFromProject(int skill_id, int project_id);

    PersonDto getPersonByEmail(String email);

    void test();
}
