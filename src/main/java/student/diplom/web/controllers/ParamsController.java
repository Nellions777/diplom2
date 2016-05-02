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
    public String startCalculate(@RequestBody ParametersSentToServer data) throws IOException {

        System.out.println(data.getSimpleParams());
        System.out.println(data.getRangeParams());
        System.out.println(data.getParamsIdForDivOnPackages());

        //System.out.println(params[1]);

        // List<Pack> packages1 = managerNode.generatePackages();
        //List<SetValue> setValues = new LinkedList<>();
        //List<Param> params = paramService.allParamByTypeTask("integral");
        /*setValues.add(new SetValue(params.get(0), 3));
        setValues.add(new SetValue(params.get(1), 0, 10, 5));
        setValues.add(new SetValue(params.get(2), 10, 20, 5));
        setValues.add(new SetValue(params.get(3), 1));
        List<Pack> packages = Utils.divOnPackages2(params, 3);
        System.out.println(packages);*/

        /*
        List<List> packages3 = Utils.divOnPackages3(params, 3);
        System.out.println(packages3);
        for(List<SendSetValue> s:packages3){
            List<Double> res = ServiceSocket.workingWithPacket(s);
            System.out.println();
        }*/


        /*List<Pack> packages = new LinkedList<>();
        Pack pack1 = new Pack();
        pack1.addParam(new RangeParam(new Param("g"), 0.0, 10.0, 5.0));
        pack1.addParam(new SingleParam(new Param("f"), 7.0));
        pack1.addParam(new RangeParam(new Param("d"), 8.0, 12.0, 2.0));

        Pack pack2 = new Pack();
        pack2.addParam(new RangeParam(new Param("g"), 0.0, 10.0, 5.0));
        pack2.addParam(new SingleParam(new Param("f"), 8.0));
        pack2.addParam(new RangeParam(new Param("d"), 8.0, 12.0, 2.0));

        Pack pack3 = new Pack();
        pack3.addParam(new RangeParam(new Param("g"), 0.0, 10.0, 5.0));
        pack3.addParam(new SingleParam(new Param("f"), 9.0));
        pack3.addParam(new RangeParam(new Param("d"), 8.0, 12.0, 2.0));
        packages.add(pack1);
        packages.add(pack2);
        packages.add(pack3);
        managerNode.sendPackages(packages);*/
        return "defaultParams";
    }

}