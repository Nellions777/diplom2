package student.diplom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import student.diplom.web.entities.Result;
import student.diplom.web.entities.Value;
import student.diplom.web.models.Pack;
import student.diplom.web.services.ParamService;
import student.diplom.web.services.ResultService;
import student.diplom.web.services.ValueService;

import java.util.List;

/**
 * Created by Евгений on 05.05.2016.
 */

@RestController
public class ResultsController {

    @Autowired
    OptionController optionController;

    @Autowired
    ResultService resultService;

    @Autowired
    ParamService paramService;

    @Autowired
    ValueService valueService;

    @RequestMapping(value = "/showResults", method = RequestMethod.GET)
    private ModelAndView showResults() {
        ModelAndView modelAndView = new ModelAndView();
        Long taskId = optionController.getCurrentTypeTaskId();
        modelAndView.addObject("params",paramService.findParamsOnTaskId(taskId));
        modelAndView.addObject("results",resultService.getResultsOnTypeTask(taskId));
        modelAndView.setViewName("showResults");
        return modelAndView;
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    private ResponseEntity<List<Value>> getResults() {
        //Long taskId = optionController.getCurrentTypeTaskId();
        return new ResponseEntity<>(valueService.getValues(), HttpStatus.OK);
    }
}
