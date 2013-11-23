package minijira.web;

import ejb.database.model.*;
import ejb.util.Log;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 23.11.13
 * Time: 16:09
 * Email: alexey.gorovoy.work@gmail.com
 */

@Named("emplBean")
@SessionScoped
public class EmplBean implements Serializable {

    @Inject
    DatabaseBean databaseBean;


    private Employee employee;
    private int rankId;
    private int devRankId;
    private int mainTechId;
    private int testTypeId;
    private int managerTypeId;
    private String email;
    private String password;
    private Date date_hired;
    private String userRole;
    Developer dev;
    Manager man;
    Tester tester;

    public String editEmployee(Employee employee) {

        this.employee = employee;

        dev = databaseBean.getDc().find(Developer.class, employee.getId());
        if (dev != null) {
            devRankId = dev.getRank().getId();
            mainTechId = dev.getMainTech().getId();
        }

        man = databaseBean.getDc().find(Manager.class, employee.getId());
        if (man != null) {
            managerTypeId = man.getType().getId();
        }

        tester = databaseBean.getDc().find(Tester.class, employee.getId());
        if (tester != null) {
            rankId = tester.getRank().getId();
            testTypeId = tester.getType().getId();
        }

        email = employee.getUser().getEmail();
        password = "";
        userRole = databaseBean.getDc().findUserRoleByEmail(email).getEmail();

        return "edit_employee";
    }

    public String saveEmployee(String employeeType) {

        employee.setEmail(email);
        //employee.setDate_hired(new Date());
        employee = databaseBean.getDc().merge(employee);

        User user = employee.getUser();
        user.setEmail(email);
        if (password != null && ! password.equals("")) {
            user.setPassword(password);
        }
        user = databaseBean.getDc().merge(user);
        employee.setUser(user);
        employee = databaseBean.getDc().merge(employee);

        UserRole ur = databaseBean.getDc().findUserRoleByEmail(email);
        ur.setRole(databaseBean.getDc().find(Role.class, userRole));
        ur.setEmail(email);
        databaseBean.getDc().merge(ur);

        Log.getLogger().info("addEmployee - type : " + employeeType);

        if (employeeType.equalsIgnoreCase("developer") ) {

            if ( dev == null) {
                if (man != null)
                    databaseBean.getDc().remove(man);

                if (tester != null)
                    databaseBean.getDc().remove(tester);

                dev = new Developer();
            }

            dev.setRank(databaseBean.getDc().find(Rank.class, devRankId));
            dev.setMainTech(databaseBean.getDc().find(Tech.class, mainTechId));
            dev.setEmployee(employee);
            databaseBean.getDc().merge(dev);
            Log.getLogger().info("developer added");

        }
        if (employeeType.equalsIgnoreCase("tester")) {

            if ( tester == null) {
                if (man != null)
                    databaseBean.getDc().remove(man);

                if (dev != null)
                    databaseBean.getDc().remove(dev);

                tester = new Tester();
            }
            tester.setRank(databaseBean.getDc().find(Rank.class, rankId));
            tester.setType(databaseBean.getDc().find(TestType.class, testTypeId));
            tester.setEmployee(employee);
            databaseBean.getDc().merge(tester);
            Log.getLogger().info("tester added");
        }
        if (employeeType.equalsIgnoreCase("manager")) {

            if ( man == null) {
                if (tester != null)
                    databaseBean.getDc().remove(tester);

                if (dev != null)
                    databaseBean.getDc().remove(dev);

                man = new Manager();
            }

            man.setType(databaseBean.getDc().find(ManagerType.class, managerTypeId));
            man.setEmployee(employee);
            databaseBean.getDc().merge(man);
            Log.getLogger().info("manager added");
        }

        return "employees";

    }

    public String deleteEmployee(Employee employee) {
        //databaseBean.getDc().deleteEmployee(employee.getId());
        return "employees";
    }

    public String reallyDeleteEmployee() {
        return "employees";
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public int getDevRankId() {
        return devRankId;
    }

    public void setDevRankId(int devRankId) {
        this.devRankId = devRankId;
    }

    public int getMainTechId() {
        return mainTechId;
    }

    public void setMainTechId(int mainTechId) {
        this.mainTechId = mainTechId;
    }

    public int getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(int testTypeId) {
        this.testTypeId = testTypeId;
    }

    public int getManagerTypeId() {
        return managerTypeId;
    }

    public void setManagerTypeId(int managerTypeId) {
        this.managerTypeId = managerTypeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_hired() {
        return date_hired;
    }

    public void setDate_hired(Date date_hired) {
        this.date_hired = date_hired;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
