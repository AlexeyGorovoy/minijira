package com.mycompany.firstapp.web;

import com.mycompany.firstapp.ejbapi.dto.SkillDto;
import java.util.List;
import javax.annotation.PostConstruct;


public class SkillBean {

	private SkillDto tmpSkill;
	private List<SkillDto> list;
	private SampleBean sample;

	public SkillBean() {
	}
    
	@PostConstruct
	public void init() {
		tmpSkill = new SkillDto();
		list = sample.getSkills();
	}
	
	public void setSample(SampleBean s) {
		sample = s;
	}
	
	
	public void setTmpSkill(SkillDto s) {
		tmpSkill = s;
	}
	
	public SkillDto getTmpSkill() {
		return tmpSkill;
	}

	
	public void remove(int id) {
		sample.getDatabaseInterface().removeSkill(id);
		list = sample.getSkills();
	}
	
	
	
	public void saveChanges() {
		sample.getDatabaseInterface().updateSkill(tmpSkill);
		list = sample.getSkills();
	}
	
	public void saveSkill() {
		tmpSkill.setId(0);
		sample.getDatabaseInterface().newSkill(tmpSkill);
		list = sample.getSkills();
	}

	
	public String getUrlEdit(int id) {
		int i = 0;
		tmpSkill.setId(id);
		for (SkillDto sk : list)
			if (sk.getId() == id) {
				tmpSkill = list.get(i);
				break;
			} else ++i;
		return "edit_skill.jsf";
	}
	
	public String getUrlAdd() {
		tmpSkill = new SkillDto();
		return "add_skill.jsf";
	}
}
