package student.diplom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import student.diplom.web.entities.Param;
import student.diplom.web.entities.Result;
import student.diplom.web.entities.Value;
import student.diplom.web.models.Pack;
import student.diplom.web.models.ParametersSentToServer;
import student.diplom.web.services.ParamService;
import student.diplom.web.services.ResultService;
import student.diplom.web.services.ValueService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<Param> params = paramService.findParamsOnTaskId(taskId);
        Map<Param,List<Double>> paramsWithValues = new HashMap<>();
        for(Param p : params) {
            if(p.getIsInput()) paramsWithValues.put(p,valueService.getValueOnParam(p));
            else paramsWithValues.put(p,null);
        }
        modelAndView.addObject("paramsWithValues",paramsWithValues);
        modelAndView.setViewName("showResults");
        return modelAndView;
    }

    @RequestMapping(value = "/outValues", method = RequestMethod.POST)
    private ResponseEntity<List<Double>> getOutValues(@RequestBody Map<String,Double> params) {
        List<Double> outValues = new ArrayList<>();
        Long taskId = optionController.getCurrentTypeTaskId();
        List<Result> results = resultService.getResultsOnTypeTask(taskId);
        for (Result r : results){
            Boolean flag = true;
            Double temp = 0.0;
            for (Value v : r.getValues()){
                if(params.containsKey(v.getParam().getName())){
                    if(v.getParam().getIsInput()){
                        Double value1 = params.get(v.getParam().getName());
                        Double value2 = v.getValue();
                        if (!value1.equals(value2)){
                            flag = false;
                            break;
                        }
                    }else{
                        temp = v.getValue();
                    }
                }
            }
            if (flag) outValues.add(temp);
        }
        return new ResponseEntity<>(outValues, HttpStatus.OK);
    }
}
