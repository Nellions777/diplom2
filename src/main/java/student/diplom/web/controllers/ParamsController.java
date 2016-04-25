package student.diplom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import student.diplom.web.entities.Param;
import student.diplom.web.models.Pack;
import student.diplom.web.models.SetValue;
import student.diplom.web.server.ManagerNode;
import student.diplom.web.server.Utils;
import student.diplom.web.services.ParamService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
    public String startCalculate() throws IOException {
        // List<Pack> packages1 = managerNode.generatePackages();
        List<SetValue> setValues = new LinkedList<>();
        List<Param> params = paramService.allParamByTypeTask("integral");
        setValues.add(new SetValue(params.get(0), 3));
        setValues.add(new SetValue(params.get(1), 0, 10, 5));
        setValues.add(new SetValue(params.get(2), 10, 20, 5));
        setValues.add(new SetValue(params.get(3), 1));
        List<Pack> packages = Utils.divOnPackages2(setValues, 3);
        managerNode.sendPackages(packages);
        return "params";
    }

}