package student.diplom.web.dao;

import org.springframework.stereotype.Repository;
import student.diplom.web.entities.TypeTask;

/**
 * Created by Евгений on 11.04.2016.
 */

@Repository
public class TypeTaskDao extends JpaCRUD<TypeTask> {

    public TypeTaskDao(){
        super();
        setClazz(TypeTask.class);
    }
}
