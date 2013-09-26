package minijira.ejbapi.dto;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    13:39
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class CustomerDto implements Dto{

    private int id;

    public CustomerDto() {}

    public CustomerDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public CustomerDto(int id, String title, String description) {
        this(title, description);
        this.id = id;
    }

    private String title;
    private String description;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
