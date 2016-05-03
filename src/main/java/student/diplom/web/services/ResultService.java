package student.diplom.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.diplom.web.dao.ResultDao;
import student.diplom.web.entities.Result;

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


}
