package student.diplom.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.diplom.web.dao.ParamDao;
import student.diplom.web.entities.Param;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Евгений on 20.04.2016.
 */

@Service
public class ParamService {

    @Autowired
    ParamDao dao;

    public ParamService(){

    }

    // get current params
    public List<Param> findCurrentParams(Boolean isInput, long taskId){
        return dao.findCurrentParams(isInput, taskId);
    }

    public Param findParamById(long id){
        return dao.findOne(id);
    }

    public List<Param> findAll(){
        return dao.findAll();
    }

    public List<Param> allParamByTypeTask(String typeTaskName) {
        List<Param> params = new LinkedList<>();
        for (Param param : findAll()) {
            /*if (typeTaskName.equals(param.getTask().getName())) {
                params.add(param);
            }*/
        }
        return params;
    }
}
