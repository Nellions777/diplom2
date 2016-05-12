package student.diplom.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.diplom.web.dao.ValueDao;
import student.diplom.web.entities.Value;

import java.util.List;

@Service
public class ValueService {

    @Autowired
    private ValueDao valueDao;

    public ValueService() {

    }

    public void save(Value value) {
        valueDao.create(value);
    }

    public List<Value> getValues(){
        return valueDao.findAll();
    }


}
