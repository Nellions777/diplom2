package student.diplom.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Евгений on 24.03.2016.
 */
@Controller
public class OptionController {

    @RequestMapping(value = "/option", method = RequestMethod.GET)
    private ModelAndView options() {
        ModelAndView modelAndView = new ModelAndView();
        //List<Product> products = productService.getAll();
        //modelAndView.addObject("products", products);
        modelAndView.setViewName("option");
        return modelAndView;
    }
}
