package minijira.ejbapi.dto;

/**
 * Created by  Alexey Gorovoy
 * Date:    28.09.13
 * Time:    18:37
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class TesterDto implements Dto {
    EmployeeDto employee;
    RankDto rank;
    TestTypeDto type;

    public TesterDto(EmployeeDto employee, RankDto rank, TestTypeDto type) {
        this.employee = employee;
        this.rank = rank;
        this.type = type;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public RankDto getRank() {
        return rank;
    }

    public void setRank(RankDto rank) {
        this.rank = rank;
    }

    public TestTypeDto getType() {
        return type;
    }

    public void setType(TestTypeDto type) {
        this.type = type;
    }
}
