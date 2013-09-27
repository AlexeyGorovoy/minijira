package minijira.ejbapi.dto;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    11:28
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class WorkflowDto implements Dto {
    int id;
    String title;

    public WorkflowDto(int id, String title) {
        this.id = id;
        this.title = title;
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
}
