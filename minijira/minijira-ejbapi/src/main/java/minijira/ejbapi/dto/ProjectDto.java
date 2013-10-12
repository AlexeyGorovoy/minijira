package minijira.ejbapi.dto;

import minijira.ejbapi.dto.joint.ProjectEmployeeDto;

import java.util.Date;
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
    private Date date_start;
    private Date date_end;
    private DeveloperDto dev_leader;
    private TesterDto test_leader;
    private ManagerDto pm;
    private CustomerAgentDto customer_agent;
    private CustomerDto customer;
    private ProjectTypeDto type;
    private List<TechDto> techs;
    private List<ProjectEmployeeDto> employees;

    public ProjectDto() {}

    public ProjectDto(int id, String title, String description, Date date_start, Date date_end, DeveloperDto dev_leader, TesterDto test_leader,
                      ManagerDto pm, CustomerAgentDto customer_agent, CustomerDto customer, ProjectTypeDto type) {
        this.id = id;
        this.title = title;
        this.date_start = date_start;
        this.date_end = date_end;
        this.description = description;
        this.dev_leader = dev_leader;
        this.test_leader = test_leader;
        this.pm = pm;
        this.customer_agent = customer_agent;
        this.customer = customer;
        this.type = type;
        /*
        List<TechDto> techs, List<ProjectEmployeeDto> employees

        this.techs = techs;
        this.employees = employees;
        */
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public DeveloperDto getDev_leader() {
        return dev_leader;
    }

    public void setDev_leader(DeveloperDto dev_leader) {
        this.dev_leader = dev_leader;
    }

    public TesterDto getTest_leader() {
        return test_leader;
    }

    public void setTest_leader(TesterDto test_leader) {
        this.test_leader = test_leader;
    }

    public ManagerDto getPm() {
        return pm;
    }

    public void setPm(ManagerDto pm) {
        this.pm = pm;
    }

    public CustomerAgentDto getCustomer_agent() {
        return customer_agent;
    }

    public void setCustomer_agent(CustomerAgentDto customer_agent) {
        this.customer_agent = customer_agent;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public ProjectTypeDto getType() {
        return type;
    }

    public void setType(ProjectTypeDto type) {
        this.type = type;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public List<TechDto> getTechs() {
        return techs;
    }

    public void setTechs(List<TechDto> techs) {
        this.techs = techs;
    }

    public List<ProjectEmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<ProjectEmployeeDto> employees) {
        this.employees = employees;
    }
}
