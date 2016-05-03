package student.diplom.web.dao;

import org.springframework.stereotype.Repository;
import student.diplom.web.entities.Value;

/**
 * Created by Евгений on 20.04.2016.
 */

@Repository
public class ValueDao extends JpaCRUD<Value> {

    public ValueDao() {
        super();
        setClazz(Value.class);
    }
}
