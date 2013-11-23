package minijira.web;

import ejb.database.model.Comment;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 03.10.13
 * Time: 16:34
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named("commentsBean")
@SessionScoped
public class CommentsBean implements Serializable {

    int task_id;

    List<Comment> comments;

    @Inject
    DatabaseBean databaseBean;

    @PostConstruct
    void init() {
        comments = new LinkedList<Comment>();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void refresh() {
        comments = databaseBean.getCommentsByTaskSP(task_id);
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public DatabaseBean getDatabaseBean() {
        return databaseBean;
    }

    public void setDatabaseBean(DatabaseBean databaseBean) {
        this.databaseBean = databaseBean;
    }
}
