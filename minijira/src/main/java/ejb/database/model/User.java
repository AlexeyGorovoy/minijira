package ejb.database.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 04.11.13
 * Time: 0:01
 * Email: alexey.gorovoy.work@gmail.com
 */
@Entity
@Table(name = "user")
@NamedQueries(
        @NamedQuery(name = "User.findAll", query = "select u from User u")
)
public class User implements ModelEntity {

    @Id
    @Column (name = "email")
    String email;

    @Column (name = "password")
    String password;

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
}
