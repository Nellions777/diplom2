package student.diplom.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import student.diplom.web.models.Parameter;
import student.diplom.web.services.ServerNodeService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;


/**
 * Created by Евгений on 16.03.2016.
 */

@RestController
public class JsonController {

    @Autowired
    private ServerNodeService serverNodeService;

    private String currentTypeTask = "SUM";

    @RequestMapping(value = "/defaultParams", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getDefaultParams(){

        List<Parameter> list = new ArrayList();
        if(currentTypeTask.equals("INTEGRAL")) {
            // названия параметров должны быть разные
            list.add(new Parameter("a", 101));
            list.add(new Parameter("b", 0, 1, 1));
            list.add(new Parameter("c", 10, 40, 10));
            list.add(new Parameter("d", 1, 7, 3));
        }
        else if(currentTypeTask.equals("SUM")){
            list.add(new Parameter("e", 222));
            list.add(new Parameter("f", 2, 4, 2));
            list.add(new Parameter("g", 3, 9, 3));
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Object> res = new ArrayList();
        res.add(currentTypeTask);
        res.add(list);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public ResponseEntity<List<Double>> getResults(@RequestBody List<Parameter> listOfParameters){

        List<Double> listResults = serverNodeService.getListResults(listOfParameters);

        return new ResponseEntity<>(listResults, HttpStatus.OK);
    }

    @RequestMapping(value = "/typeTask", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getTypeTask(){

        List<String> list = new ArrayList();
        // названия параметров должны быть разные

        list.add(currentTypeTask);

        list.add("INTEGRAL");
        list.add("SUM");


        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/choseTypeTask", method = RequestMethod.PUT)
    public ResponseEntity<String> putChoseTypeTask(@RequestBody String typeTask){

        this.currentTypeTask = typeTask;

        return new ResponseEntity<>(typeTask, HttpStatus.OK);
    }
}
