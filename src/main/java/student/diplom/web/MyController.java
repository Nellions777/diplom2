package student.diplom.web;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 20.09.2015.
 */



@org.springframework.stereotype.Controller
public class MyController {

    @RequestMapping(value="/params", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("params");
        return modelAndView;
    }

}