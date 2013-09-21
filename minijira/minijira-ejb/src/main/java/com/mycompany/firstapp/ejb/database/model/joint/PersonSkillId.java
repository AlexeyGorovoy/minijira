package com.mycompany.firstapp.ejb.database.model.joint;

import java.io.Serializable;

/**
 * Created by  Alexey Gorovoy
 * Date:    18.09.13
 * Time:    12:09
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class PersonSkillId implements Serializable {

    private int person_id;

    private int skill_id;

    @Override
    public int hashCode() {
        return person_id + skill_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PersonSkillId) {
            PersonSkillId anotherId = (PersonSkillId)o;
            return (person_id == anotherId.person_id) && ( skill_id == anotherId.skill_id);
        }
        return false;
    }
}
