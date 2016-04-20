package student.diplom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import student.diplom.web.models.Pack;
import student.diplom.web.models.Parameter;
import student.diplom.web.server.ManagerNode;
import student.diplom.web.server.Utils;

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

    @RequestMapping(value = "/fillParams", method = RequestMethod.GET)
    private ModelAndView fillParams() {
        ModelAndView modelAndView = new ModelAndView();
        //List<Product> products = productService.getAll();
        //modelAndView.addObject("products", products);

        modelAndView.setViewName("fillParams");
        return modelAndView;
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public String startCalculate() throws IOException {
        // List<Pack> packages1 = managerNode.generatePackages();
        List<Parameter> params = new LinkedList<>();
        params.add(new Parameter("a", 3));
        params.add(new Parameter("b", 10));
        params.add(new Parameter("c", 10, 20, 5));
        params.add(new Parameter("s", 1));
        List<Pack> packages = Utils.divOnPackages2(params, 3);
        managerNode.sendPackages(packages);
        return "params";
    }


}