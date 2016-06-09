package student.diplom.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.diplom.web.dao.ValueDao;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.Value;

import java.util.*;

@Service
public class ValueService{

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

    public List<Double> getValueOnParam(Param p){
        List<Value> repeatValues = valueDao.valuesOnParam(p);
        List<Double> results;

        Set<Double> uniqueValues = new HashSet<>();
        for(Value v : repeatValues){
            uniqueValues.add(v.getValue());
        }
        results = new ArrayList<>(uniqueValues);
        Collections.sort(results);

        return results;
    }
}
