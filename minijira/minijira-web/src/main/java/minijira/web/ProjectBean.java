package minijira.web;

import minijira.ejbapi.dto.CustomerDto;
import minijira.ejbapi.dto.PersonDto;
import minijira.ejbapi.dto.ProjectDto;
import minijira.ejbapi.dto.SkillDto;

import javax.annotation.PostConstruct;
import java.util.List;


public class ProjectBean {

	private int cur;
	private int editInd;
	private boolean okAdded;
	private SkillDto skill;
	private List<ProjectDto> list;
	private ProjectDto tmpProject;
	private PersonDto tmpPerson;
	private CustomerDto tmpCustomer;

	private SampleBean sample;

	public ProjectBean() {
	}
    
	@PostConstruct
	public void init() {	
		cur = 0;
		editInd = 0;
		skill = new SkillDto();
		tmpPerson = new PersonDto();
		tmpCustomer = new CustomerDto(0, "", "", "", "");
		list = sample.getProjects();
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
	
	public void setSkill(SkillDto s) {
		skill = s;
	}
	
	public SkillDto getSkill() {
		return skill;
	}
	
	public ProjectDto getCurProject() {
		return list.get(cur);
	}
	
	public ProjectDto getEditedProject() {
		return tmpProject;
	}
	
	public void setTmpProject(ProjectDto p) {
		tmpProject = p;
	}
	
	public ProjectDto getTmpProject() {
		return tmpProject;
	}
	
	public void setTmpPerson(PersonDto p) {
		tmpPerson = p;
	}
	
	public PersonDto getTmpPerson() {
		return tmpPerson;
	}
	
	public void setTmpCustomer(CustomerDto c) {
		tmpCustomer = c;
	}
	
	public CustomerDto getTmpCustomer() {
		return tmpCustomer;
	}
	
	public void remove(int id) {
		sample.getDatabaseInterface().removeProject(id);
		list = sample.getProjects();
	}
	
	public void removeSkillFromProject(int id) {
		sample.getDatabaseInterface().removeSkillFromProject(id, tmpProject.getId());
	}
	
	public void addSkillToProject() {
		sample.getDatabaseInterface().addSkillToProject(skill, tmpProject);
	}
	
	
	public void saveChanges() {
		sample.getDatabaseInterface().updateProject(tmpProject);
		list = sample.getProjects();
	}
	
	public void saveProject() {
		tmpProject.setLeader(tmpPerson);
		tmpProject.setCustomer(tmpCustomer);
		sample.getDatabaseInterface().newProject(tmpProject);
		list = sample.getProjects();
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
		for (ProjectDto proj : list)
			if (proj.getId() == id) {
				cur = i; 
				break;
			} else ++i;
		return "project.jsf";
	}
	
	public String getUrlEdit(int id) {
		int i = 0;
		for (ProjectDto proj : list)
			if (proj.getId() == id) {
				tmpProject = list.get(i);
				break;
			} else ++i;
		return "edit_project.jsf";
	}
	
	public String getUrlAdd() {
		tmpProject = new ProjectDto();
		okAdded = false;
		return "add_project.jsf";
	}
	
	
	public int getCountProjects() {
		return list.size();
	}
}
