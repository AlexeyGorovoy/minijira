package ejb.database.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 04.11.13
 * Time: 0:05
 * Email: alexey.gorovoy.work@gmail.com
 */
@Entity
@Table (name = "role")
@NamedQueries({
        @NamedQuery( name = "Role.findAll", query = "select r from Role r")
})
public class Role implements ModelEntity {

   @Id
   String rolename;

   public String getRolename() {
       return rolename;
   }

   public void setRolename(String rolename) {
       this.rolename = rolename;
   }
}
