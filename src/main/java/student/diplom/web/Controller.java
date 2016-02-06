package student.diplom.web;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Евгений on 20.09.2015.
 */



@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value="/params", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("a","7");
        modelAndView.addObject("b","8");
        modelAndView.addObject("c","3");
        modelAndView.addObject("d","4");
        modelAndView.addObject("e","9");
        modelAndView.addObject("u","9");
        modelAndView.addObject("p","9");
        modelAndView.setViewName("params");
        return modelAndView;
    }

}