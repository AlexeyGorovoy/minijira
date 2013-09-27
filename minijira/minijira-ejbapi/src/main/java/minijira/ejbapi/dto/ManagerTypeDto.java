package minijira.ejbapi.dto;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:44
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class ManagerTypeDto implements Dto {
    int id;
    String title;
    String description;

    public ManagerTypeDto(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
}
