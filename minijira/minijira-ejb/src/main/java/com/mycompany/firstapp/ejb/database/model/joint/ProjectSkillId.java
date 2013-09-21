package com.mycompany.firstapp.ejb.database.model.joint;

import java.io.Serializable;

/**
 * Created by  Alexey Gorovoy
 * Date:    18.09.13
 * Time:    12:09
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class ProjectSkillId implements Serializable {

    private int project_id;

    private int skill_id;

    @Override
    public int hashCode() {
        return project_id + skill_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ProjectSkillId) {
            ProjectSkillId anotherId = (ProjectSkillId)o;
            return (project_id == anotherId.project_id) && ( skill_id == anotherId.skill_id);
        }
        return false;
    }
}
