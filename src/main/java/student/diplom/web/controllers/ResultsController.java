package student.diplom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import student.diplom.web.services.ParamService;
import student.diplom.web.services.ResultService;

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

    @RequestMapping(value = "/showResults", method = RequestMethod.GET)
    private ModelAndView showResults() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("params",paramService.findParamsOnTaskId(optionController.getCurrentTypeTaskId()));
        modelAndView.addObject("results",resultService.getResults());
        modelAndView.setViewName("showResults");
        return modelAndView;
    }
}
