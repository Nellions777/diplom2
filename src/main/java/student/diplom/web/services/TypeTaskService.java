package student.diplom.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.diplom.web.dao.TypeTaskDao;
import student.diplom.web.entities.TypeTask;

import java.util.List;

/**
 * Created by Евгений on 11.04.2016.
 */

@Service
public class TypeTaskService {

    @Autowired
    TypeTaskDao dao;

    public TypeTaskService(){

    }

    public List<TypeTask> findAll(){
        return dao.findAll();
    }

    public TypeTask findTaskById(long id){
        return dao.findOne(id);
    }

    public String findNameTaskById(long id){
        return dao.findNameTaskById(id);
    }

    /*public void delete(TypeTask typeTask){
        dao.delete(typeTask);
    }*/

}
