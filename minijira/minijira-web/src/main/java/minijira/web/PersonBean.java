package minijira.web;

import minijira.ejbapi.dto.PersonDto;
import minijira.ejbapi.dto.SkillDto;
import java.util.List;
import javax.annotation.PostConstruct;


public class PersonBean {

	private int cur;
    private boolean okAdded;
	private SkillDto skill;
	private List<PersonDto> list;
	private PersonDto tmpPerson;

	private SampleBean sample;

	public PersonBean() {
	}
    
	@PostConstruct
	public void init() {	
		skill = new SkillDto();
		tmpPerson = new PersonDto();
		list = sample.getPeople();
        okAdded = false;
	}
	
	public void setSample(SampleBean s) {
		sample = s;
	}
	
	public int getCur() {
		return cur + 1;
	}
	
	public void setCur(int c) {
		cur = c;
	}

    public boolean getOkAdded() {
        return okAdded;
    }
	
	public PersonDto getCurPerson() {
		return list.get(cur);
	}
	
	
	public void setSkill(SkillDto s) {
		skill = s;
	}
	
	public SkillDto getSkill() {
		return skill;
	}
	
	
	public void setTmpPerson(PersonDto p) {
		tmpPerson = p;
	}
	
	public PersonDto getTmpPerson() {
		return tmpPerson;
	}
	

	public void remove(int id) {
		sample.getDatabaseInterface().removePerson(id);
		list = sample.getPeople();
	}
	
	public void removeSkillFromPerson(int id) {
		sample.getDatabaseInterface().removeSkillFromPerson(id, tmpPerson.getId());
	}
	
	public void addSkillToPerson() {
		sample.getDatabaseInterface().addSkillToPerson(skill, tmpPerson);
	}
	
	
	public void saveChanges() {
		sample.getDatabaseInterface().updatePerson(tmpPerson);
		list = sample.getPeople();
	}
	
	public void savePerson() {
		sample.getDatabaseInterface().newPerson(tmpPerson);
        okAdded = true;
	}
	
	public void next() {
		if (cur < list.size()-1) ++cur;
	}
    
	public void prev() {
		if (cur > 0) --cur;
	}

	public String getUrl(int id) {
		//list = sample.getProjects();
		int i = 0;
		for (PersonDto per : list)
			if (per.getId() == id) {
				cur = i; 
				break;
			} else ++i;
		return "person.jsf";
	}
	
	public String getUrlEdit(int id) {
		int i = 0;
		for (PersonDto per : list)
			if (per.getId() == id) {
				tmpPerson = list.get(i);
				break;
			} else ++i;
		return "edit_person.jsf";
	}
	
	public String getUrlAdd() {
		tmpPerson = new PersonDto();
        okAdded = false;
		return "add_person.jsf";
	}
	
	public int getCountPeople() {
		return list.size();
	}
}
 
