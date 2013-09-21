package com.mycompany.firstapp.web;

import com.mycompany.firstapp.ejbapi.DatabaseControllerInterface;
import com.mycompany.firstapp.ejbapi.dto.CustomerDto;
import com.mycompany.firstapp.ejbapi.dto.PersonDto;
import com.mycompany.firstapp.ejbapi.dto.ProjectDto;
import com.mycompany.firstapp.ejbapi.dto.SkillDto;
import com.mycompany.firstapp.ejbapi.ws.WeatherInterface;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    13.09.13
 * Time:    15:34
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class AdderBean {

    @EJB
    private DatabaseControllerInterface dci;

    @EJB
    private WeatherInterface wi;

    private SkillDto skill;

    private PersonDto person;

    private ProjectDto project;

    private CustomerDto customer;

    private String email;

    private String city;
    private String country;
    private String weatherResponse;

    @PostConstruct
    void init() {
        person = new PersonDto();
        skill = new SkillDto();
        project = new ProjectDto();
        customer = new CustomerDto();
        city = "Minsk";
        country = "Belarus";
    }

    public void getWeather() {
        weatherResponse = wi.getWeather(city, country);
    }

    public List<String> getCitiesByCountry() {
        return wi.getCitiesByCountry(country);
    }

    public void test() {
        dci.test();
    }

    public void saveSkill() {
        dci.newSkill(skill);
    }

    public void updateSkill() {
        dci.updateSkill(skill);
    }

    public void saveCustomer() {
        dci.newCustomer(customer);
    }

    public void savePerson() {
        dci.newPerson(person);
    }

    public void addSkillToPerson() {
        dci.addSkillToPerson(skill, person);
    }

    public void saveProject() {
        project.setLeader(person);
        project.setCustomer(customer);
        dci.newProject(project);
    }

    public void addSkillToProject() {
        dci.addSkillToProject(skill, project);
    }

    public void  removeSkill(int id) {
        dci.removeSkill(id);
    }

    public void getByEmail() {
        person = dci.getPersonByEmail(email);
    }

    // getters and setters


    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public SkillDto getSkill() {
        return skill;
    }

    public void setSkill(SkillDto skill) {
        this.skill = skill;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(String weatherResponse) {
        this.weatherResponse = weatherResponse;
    }
}
