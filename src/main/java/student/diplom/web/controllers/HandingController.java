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
public class HandingController {

    @Autowired
    ServerNodeService serverNodeService;

    @RequestMapping(value = "/defaultParams", method = RequestMethod.GET)
    public ResponseEntity<List<Parameter>> getDefaultParams(){

        List<Parameter> list = new ArrayList();
        // названия параметров должны быть разные
        list.add(new Parameter("a", 101));
        list.add(new Parameter("b", 0, 1, 1));
        list.add(new Parameter("c", 10, 40, 10));
        list.add(new Parameter("d", 1, 7, 3));

        //list.add(new Parameter("j",true));
        //list.add(new Parameter("i",false));

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public ResponseEntity<List<Double>> getResults(@RequestBody List<Parameter> listOfParameters){

        List<Double> listResults = serverNodeService.createServerSocket(listOfParameters);
        System.out.println(listResults);

        return new ResponseEntity<>(listResults, HttpStatus.OK);
    }


}
