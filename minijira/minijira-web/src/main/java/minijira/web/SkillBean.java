package minijira.web;

import minijira.ejbapi.dto.SkillDto;

import javax.annotation.PostConstruct;
import java.util.List;


public class SkillBean {

    private boolean okAdded;
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

    public boolean getOkAdded() {
        return okAdded;
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
        okAdded = true;
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
        okAdded = false;
		return "add_skill.jsf";
	}
}
