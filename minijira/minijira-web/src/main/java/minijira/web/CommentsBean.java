package minijira.web;

import minijira.ejbapi.dto.CommentDto;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 03.10.13
 * Time: 16:34
 * Email: alexey.gorovoy.work@gmail.com
 */
@ManagedBean
public class CommentsBean {

    int project_id;

    List<CommentDto> comments;

    @ManagedProperty  (value = "#{databaseBean}")
    DatabaseBean databaseBean;

    @PostConstruct
    void init() {
        comments = new LinkedList<CommentDto>();
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public void refresh() {
        comments = databaseBean.getCommentsByProjectSP(project_id);
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public DatabaseBean getDatabaseBean() {
        return databaseBean;
    }

    public void setDatabaseBean(DatabaseBean databaseBean) {
        this.databaseBean = databaseBean;
    }
}
