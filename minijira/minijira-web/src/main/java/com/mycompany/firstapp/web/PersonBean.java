package com.mycompany.firstapp.web;

import com.mycompany.firstapp.ejbapi.dto.PersonDto;
import com.mycompany.firstapp.ejbapi.dto.SkillDto;
import java.util.List;
import javax.annotation.PostConstruct;


public class PersonBean {

	private int editInd;
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
	}
	
	public void setSample(SampleBean s) {
		sample = s;
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
		return "add_person.jsf";
	}
}
 
