package minijira.web;

import ejb.database.model.TestType;
import ejb.util.Log;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 09.11.13
 * Time: 15:28
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named ("testerBean")
@SessionScoped
public class TesterBean implements Serializable {

    @PostConstruct
    void init() {
        Log.getLogger().info("TesterBean - init()");
    }

    @Inject
    DatabaseBean databaseBean;

    TestType testType;

    public String editTestType(TestType testType) {
        this.testType = testType;
        return "edit_test_type";
    }

    public String saveTestType() {
        databaseBean.getDc().merge(testType);
        return "test_types";
    }

    public String deleteTestType(TestType testType) {
        databaseBean.getDc().remove(testType);
        return "test_types";
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }
}
