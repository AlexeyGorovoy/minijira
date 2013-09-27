package minijira.ejbapi.dto;

/**
 * Created by  Alexey Gorovoy
 * Date:    27.09.13
 * Time:    10:04
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class OfficeDto implements Dto{

    int id;
    String title;
    String address;

    public OfficeDto(int id, String title, String address) {
        this.id = id;
        this.title = title;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
