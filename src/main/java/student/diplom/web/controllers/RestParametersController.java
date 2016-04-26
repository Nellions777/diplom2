package student.diplom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.TypeTask;
import student.diplom.web.models.SetValue;
import student.diplom.web.services.ParamService;
import student.diplom.web.services.TypeTaskService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class RestParametersController {

    @Autowired
    private TypeTaskService typeTaskService;
    @Autowired
    private ParamService paramService;

    private long currentTypeTaskId = -1;

    @RequestMapping(value = "/defaultParams", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getDefaultParams(){

        if(currentTypeTaskId == -1) {
            currentTypeTaskId = typeTaskService.findAll().get(0).getId();
        }

        List<Param> currentParams = paramService.findCurrentParams(true, currentTypeTaskId);
        List<SetValue> setValues = new LinkedList<>();
        for (Param param : currentParams) {
            setValues.add(new SetValue(param));
        }

        List<Object> res = new ArrayList();
        res.add(typeTaskService.findNameTaskById(currentTypeTaskId));
        res.add(setValues);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/typeTask", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getTypeTask() {

        if(currentTypeTaskId == -1) {
            currentTypeTaskId = typeTaskService.findAll().get(0).getId();
        }

        List<TypeTask> tasks = typeTaskService.findAll();
        List<Object> res = new ArrayList();
        res.add(currentTypeTaskId);
        res.add(tasks);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/choseTypeTask", method = RequestMethod.PUT)
    public ResponseEntity<Long> putChoseTypeTask(@RequestBody Long typeTask){

        this.currentTypeTaskId = typeTask;

        return new ResponseEntity<>(typeTask, HttpStatus.OK);
    }
    /*@RequestMapping(value = "/results", method = RequestMethod.POST)
    public ResponseEntity<List<Double>> getResults(@RequestBody List<Parameter> listOfParameters){

        List<Double> listResults = serverNodeService.getListResults(listOfParameters);

        return new ResponseEntity<>(listResults, HttpStatus.OK);
    }*/
}
