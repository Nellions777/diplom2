package student.diplom.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import student.diplom.web.server.ManagerNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 26.04.2016.
 */

@RestController
public class RestDynamicStatisticController {

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    private ModelAndView statistic() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("statistic");
        return modelAndView;
    }

    @RequestMapping(value = "/statisticData", method = RequestMethod.GET)
    private ResponseEntity<Object> statisticData() {

        List<Object> dataStatistic = new ArrayList();
        dataStatistic.add(ManagerNode.isStarted);
        dataStatistic.add(ManagerNode.getCountClients());

        return new ResponseEntity<>(dataStatistic, HttpStatus.OK);
    }

}
