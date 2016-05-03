package student.diplom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import student.diplom.web.entities.Param;
import student.diplom.web.models.*;
import student.diplom.web.server.ManagerNode;
import student.diplom.web.server.Utils;
import student.diplom.web.services.ParamService;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 14.04.2016.
 */
@Controller
public class ParamsController {

    @Autowired
    private ManagerNode managerNode;

    @Autowired
    private ParamService paramService;

    @RequestMapping(value = "/fillParams", method = RequestMethod.GET)
    private ModelAndView fillParams() {
        ModelAndView modelAndView = new ModelAndView();
        //List<Product> products = productService.getAll();
        //modelAndView.addObject("products", products);

        modelAndView.setViewName("fillParams");
        return modelAndView;
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String startCalculate(@RequestBody ParametersSentToServer data) throws IOException {
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
        managerNode.sendPackages(packages);
        return "params";
    }

}