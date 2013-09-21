package com.mycompany.firstapp.ejbapi.dto;

import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    14:12
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class SkillDto  implements Dto {

    private int id;
    private String title;
    private String description;
    private List<PersonDto> people;
    private List<ProjectDto> projects;

    public SkillDto() {}

    public SkillDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public SkillDto(int id, String title, String description) {
        this(title, description);
        this.id = id;
    }

    public SkillDto(int id, String title, String description, List<PersonDto> people, List<ProjectDto> projects) {
        this(id, title, description);
        this.people = people;
        this.projects = projects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PersonDto> getPeople() {
        return people;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }
}
