package minijira.ejbapi.dto;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    13:39
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class CustomerDto implements Dto{

    int id;
    String title;
    String description;
    String address;
    String info;

    public CustomerDto(int id, String title, String description, String address, String info) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
