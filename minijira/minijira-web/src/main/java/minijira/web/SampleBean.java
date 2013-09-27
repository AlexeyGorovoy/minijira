package minijira.web;

import minijira.ejbapi.DatabaseController;
import minijira.ejbapi.dto.PersonDto;
import minijira.ejbapi.dto.ProjectDto;
import minijira.ejbapi.dto.SkillDto;

import javax.ejb.EJB;
import java.util.List;


public class SampleBean {

	String test = "";

    public void setTest(String t) {
		test = t;
	}
	
	public String getTest() {
		return test;
	}
	
	
	public String linkTest(String t) {
		test = t;
		return "skills.jsf";
	}
	

    public SampleBean() {
        //sampleInterface = getSampleInterface();
    }


    @EJB
    private DatabaseController dci;
    
    public DatabaseController getDatabaseInterface() {
		return dci;
    }
    
    public List<SkillDto> getSkills() {
        List<SkillDto> skills = dci.getSkills();
        return skills;
    }
    
    public List<PersonDto> getPeople() {
        List<PersonDto> persons = dci.getPeople();
        return persons;
    }
    
    public List<ProjectDto> getProjects() {
        List<ProjectDto> projects = dci.getProjects();
        return projects;
    }
}
