package student.diplom.web.services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.diplom.web.dao.ResultDao;
import student.diplom.web.entities.Result;

import java.util.List;

/**
 * Created by Евгений on 20.04.2016.
 */

@Service
public class ResultService {

    @Autowired
    private ResultDao resultDao;

    public ResultService() {

    }

    public void save(Result result) {
        resultDao.create(result);
    }

    public List<Result> getResults(){
        return resultDao.findAll();
    }

    public List<Result> getResultsOnTypeTask(Long id){
        return resultDao.getResultsOnTypeTask(id);
    }
}
