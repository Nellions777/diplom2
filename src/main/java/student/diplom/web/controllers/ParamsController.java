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
import student.diplom.web.entities.TypeTask;
import student.diplom.web.models.*;
import student.diplom.web.server.ManagerNode;
import student.diplom.web.server.Utils;
import student.diplom.web.services.ParamService;
import student.diplom.web.services.TypeTaskService;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 14.04.2016.
 */
@RestController
public class ParamsController {

    @Autowired
    OptionController optionController;

    @Autowired
    private ManagerNode managerNode;

    @Autowired
    private ParamService paramService;

    @Autowired
    private TypeTaskService typeTaskService;

    @RequestMapping(value = "/fillParams", method = RequestMethod.GET)
    private ModelAndView fillParams() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fillParams");
        return modelAndView;
    }

    @RequestMapping(value = "/defaultParams", method = RequestMethod.GET)
    public ResponseEntity<List<Param>> getDefaultParams(){

        List<Param> currentParams = paramService.findCurrentParams(true, optionController.getCurrentTypeTaskId());

        /*List<IterateParam> params = new LinkedList<>();

        for (Param param : currentParams) {
            params.add(new SingleParam(param,0.0));
        }*/

        //List<Object> res = new ArrayList();
        //res.add(typeTaskService.findNameTaskById(currentTypeTaskId));
        //res.add(currentParams);

        return new ResponseEntity<>(currentParams, HttpStatus.OK);
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public ResponseEntity<List<Pack>> startCalculate(@RequestBody ParametersSentToServer data) throws IOException {
        Map<Param, IterateParam> paramMap = new HashMap<>();
        List<Param> divParam = new LinkedList<>();
        for (SimpleParam param : data.getSimpleParams()) {
            paramMap.put(param.getParam(), param);
        }
        for (RangeParam param : data.getRangeParams()) {
            paramMap.put(param.getParam(), param);
        }
        for (Long paramId : data.getParamsIdForDivOnPackages()) {
            divParam.add(paramService.findParamById(paramId));
        }
        List<Pack> packages = Utils.divOnPackages(paramMap, divParam);
        TypeTask typeTask = typeTaskService.findTaskById(optionController.getCurrentTypeTaskId());
        for(Pack pack: packages) {
            pack.setTypeTask(typeTask);
        }
        managerNode.sendPackages(packages);

        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

}