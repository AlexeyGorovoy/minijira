package com.mycompany.firstapp.ejbapi.dto;

import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    14:11
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class ProjectDto  implements Dto {

    private int id;
    private String title;
    private String description;
    private PersonDto leader;
    private CustomerDto customer;
    private List<SkillDto> skills;

    public ProjectDto() {}

    public ProjectDto(String title, String description, PersonDto leader, CustomerDto customer) {
        this.title = title;
        this.description = description;
        this.leader = leader;
        this.customer = customer;
    }


    public ProjectDto(int id, String title, String description, PersonDto leader, CustomerDto customer) {
        this(title, description, leader, customer);
        this.id = id;
    }

    public ProjectDto(int id, String title, String description, PersonDto leader, CustomerDto customer, List<SkillDto> skills) {
        this(id, title, description, leader, customer);
        this.skills = skills;
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

    public PersonDto getLeader() {
        return leader;
    }

    public void setLeader(PersonDto leader) {
        this.leader = leader;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SkillDto> getSkills() {
        return skills;
    }
}
