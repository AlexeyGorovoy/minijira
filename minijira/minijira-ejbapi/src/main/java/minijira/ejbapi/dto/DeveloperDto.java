package minijira.ejbapi.dto;

import minijira.ejbapi.dto.joint.DeveloperTechDto;

import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    28.09.13
 * Time:    16:55
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class DeveloperDto implements Dto{

    EmployeeDto employee;
    RankDto rank;
    TechDto mainTech;
    List<DeveloperTechDto> techs;

    public DeveloperDto(EmployeeDto employee, RankDto rank,
                        TechDto mainTech, List<DeveloperTechDto> techs) {
        this.employee = employee;
        this.rank = rank;
        this.mainTech = mainTech;
        this.techs = techs;
    }

    public RankDto getRank() {
        return rank;
    }

    public void setRank(RankDto rank) {
        this.rank = rank;
    }

    public TechDto getMainTech() {
        return mainTech;
    }

    public void setMainTech(TechDto mainTech) {
        this.mainTech = mainTech;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }
}
