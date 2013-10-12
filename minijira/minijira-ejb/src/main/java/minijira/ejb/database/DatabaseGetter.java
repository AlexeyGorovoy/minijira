package minijira.ejb.database;

import minijira.ejb.database.model.ModelEntity;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    12.09.13
 * Time:    14:35
 * Email:   alexey.gorovoy.work@gmail.com
 */
public class DatabaseGetter <E extends ModelEntity> {

    private Query query;

    public DatabaseGetter(Query query) {
        this.query = query;
    }

    public List get() {
        List<E> entities = query.getResultList();
        /*
        List<Dto> dtos = new LinkedList<Dto>();
        for (E e : entities) {
            dtos.add(e.getDto());
        }
          */
        return entities;
    }
}
