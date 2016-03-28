package student.diplom.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Евгений on 24.03.2016.
 */
@Controller
public class PageController {

    @RequestMapping(value="/fillParams", method= RequestMethod.GET)
    private ModelAndView fillParams(){
        ModelAndView modelAndView = new ModelAndView();
        //List<Product> products = productService.getAll();
        //modelAndView.addObject("products", products);

        modelAndView.setViewName("fillParams");
        return modelAndView;
    }

    @RequestMapping(value="/options", method= RequestMethod.GET)
    private ModelAndView options(){
        ModelAndView modelAndView = new ModelAndView();
        //List<Product> products = productService.getAll();
        //modelAndView.addObject("products", products);
        modelAndView.setViewName("options");
        return modelAndView;
    }

    @RequestMapping(value="/statistic", method= RequestMethod.GET)
    private ModelAndView statistic(){
        ModelAndView modelAndView = new ModelAndView();
        //List<Product> products = productService.getAll();
        //modelAndView.addObject("products", products);
        modelAndView.setViewName("statistic");
        return modelAndView;
    }
}
