package minijira.ejbapi.dto.joint;

import minijira.ejbapi.dto.Dto;
import minijira.ejbapi.dto.TechDto;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 06.10.13
 * Time: 19:59
 * Email: alexey.gorovoy.work@gmail.com
 */
public class DeveloperTechDto implements Dto {
    private TechDto tech;
    private int experience;

    public DeveloperTechDto(TechDto tech, int experience) {
        this.tech = tech;
        this.experience = experience;
    }

    public TechDto getTech() {
        return tech;
    }

    public void setTech(TechDto tech) {
        this.tech = tech;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
