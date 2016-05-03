package NodeClient;

import org.springframework.beans.factory.annotation.Autowired;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.Result;
import student.diplom.web.entities.TypeTask;
import student.diplom.web.entities.Value;
import student.diplom.web.server.SetParamWrongException;
import student.diplom.web.services.ResultService;
import student.diplom.web.services.TypeTaskService;
import student.diplom.web.services.ValueService;

import java.util.*;

/**
 * Created by Андрей on 29.04.2016.
 */
public abstract class AbstractCalculate {
    private Map<Param, ListIterator<Double>> paramMap;

    @Autowired
    private ResultService resultService;

    @Autowired
    private ValueService valueService;

    @Autowired
    private TypeTaskService typeTaskService;

    public void init(Map<Param, ListIterator<Double>> paramMap) throws SetParamWrongException {
        this.paramMap = paramMap;
        List<Param> keyList = new LinkedList<Param>(paramMap.keySet());
        ListIterator<Param> keyListIterator = keyList.listIterator();
        Map<Param, Double> mySetValue = new HashMap<>();
        calculate(keyListIterator, mySetValue);
    }

    private void calculate(ListIterator<Param> keyListIterator, Map<Param, Double> setValue) throws SetParamWrongException {
        if (keyListIterator.hasNext()) {
            Param currentParam = keyListIterator.next();
            while (paramMap.get(currentParam).hasNext()) {
                setValue.put(currentParam, paramMap.get(currentParam).next());
                calculate(keyListIterator, setValue);
            }
            // reset value for currentParam
            while (paramMap.get(currentParam).hasPrevious()) {
                paramMap.get(currentParam).previous();
            }
            keyListIterator.previous();
        } else {
            long startTime = System.currentTimeMillis();
            long resultId = calculate(setValue);
            long totalTime = System.currentTimeMillis() - startTime;
            // TODO: getting typeTaskId or TypeTask.
            // TypeTaskID = "2" is id "Sum" Task
            //TypeTask typeTask = typeTaskService.findTaskById(2);
            //saveResult(resultId, typeTask, totalTime, setValue);
            System.out.println(setValue);
        }
    }

    protected abstract long calculate(Map<Param, Double> setValue) throws SetParamWrongException;

    private void saveResult(long resultId, TypeTask typeTask, long totalTime, Map<Param, Double> setValue) {

        Result result = new Result();
        result.setId(resultId);
        result.setTask(typeTask);
        result.setTimeTask(totalTime);
        resultService.save(result);

        for (Param param : setValue.keySet()) {
            Value value = new Value();
            value.setResult(result);
            value.setParam(param);
            value.setValue(setValue.get(param));
            valueService.save(value);
        }
    }

}
