package minijira.web;

import ejb.database.model.Rank;
import ejb.database.model.Tech;
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
 * Time: 15:20
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named("devBean")
@SessionScoped
public class DevBean implements Serializable {

    @PostConstruct
    void init() {
        Log.getLogger().info("DevBean - init method");
    }

    @Inject
    DatabaseBean databaseBean;

    Tech tech;
    Rank rank;

    public String editTech(Tech tech) {
        this.tech = tech;
        return "edit_tech";
    }

    public String saveTech() {
        databaseBean.getDc().merge(tech);
        return "techs";
    }

    public String deleteTech(Tech tech) {
        databaseBean.getDc().remove(tech);
        return "techs";
    }

    public String editRank(Rank rank) {
        this.rank = rank;
        return "edit_rank";
    }

    public String saveRank() {
        databaseBean.getDc().merge(rank);
        return "ranks";
    }

    public String deleteRank(Rank rank) {
        databaseBean.getDc().remove(rank);
        return "ranks";
    }

    public Tech getTech() {
        return tech;
    }

    public void setTech(Tech tech) {
        this.tech = tech;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
