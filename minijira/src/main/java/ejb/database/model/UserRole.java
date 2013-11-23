package ejb.database.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 18.11.13
 * Time: 2:22
 * Email: alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "user_role")
@NamedQueries( {
        @NamedQuery(name = "UserRole.findAll", query = "select ur from UserRole ur"),
        @NamedQuery(name = "UserRole.findByEmail", query = "select ur from UserRole ur where ur.email = :email")
})
public class UserRole implements ModelEntity {

    @Id
    @Column (name = "user_id")
    String email;

    @OneToOne
    @JoinColumn (name = "role_id", referencedColumnName = "rolename")
    Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
