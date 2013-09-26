package minijira.web;

import minijira.ejbapi.DatabaseControllerInterface;
import minijira.ejbapi.SampleInterface;
import minijira.ejbapi.dto.CustomerDto;
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
    private SampleInterface sampleInterface;

    @EJB
    private DatabaseControllerInterface dci;

    /*
    private SampleInterface getSampleInterface() {
        try {
            return InitialContext.doLookup(SampleInterface.JNDI_NAME);
        } catch (NamingException e) {
            //logger.error("Error while initializing service", e);
            return null;
        }
    }
    */
    
    public DatabaseControllerInterface getDatabaseInterface() {
		return dci;
    }
    
    public List<CustomerDto> getCustomers() {
        List<CustomerDto> customers = dci.getCustomers();
        return customers;
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
