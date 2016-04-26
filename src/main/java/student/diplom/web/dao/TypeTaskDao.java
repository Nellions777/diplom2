package student.diplom.web.dao;

import org.springframework.stereotype.Repository;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.TypeTask;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Евгений on 11.04.2016.
 */

@Repository
public class TypeTaskDao extends JpaCRUD<TypeTask> {

    public TypeTaskDao(){
        super();
        setClazz(TypeTask.class);
    }

    public String findNameTaskById(long id){
        return findOne(id).getName();
    }
}
