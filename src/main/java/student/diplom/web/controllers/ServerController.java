package student.diplom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import student.diplom.web.server.ManagerNode;

/**
 * Created by Andrew on 13.04.2016.
 */
@Controller
public class ServerController {

    @Autowired
    ManagerNode managerNode;

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    private ModelAndView statistic() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("started", ManagerNode.isStarted);
        modelAndView.addObject("clientCount", ManagerNode.getCountClients());
        modelAndView.setViewName("statistic");
        return modelAndView;
    }

    @RequestMapping(value = "/startServer", method = RequestMethod.GET)
    private ModelAndView startServer() {
        ModelAndView modelAndView = new ModelAndView();
        if (!ManagerNode.isStarted) {
            ManagerNode.isStarted = true;
            managerNode.createServerSocket(777);
            managerNode.startCollect();
            System.out.println("Server was started");
        }

        modelAndView.addObject("started", ManagerNode.isStarted);
        modelAndView.addObject("clientCount", ManagerNode.getCountClients());
        modelAndView.setViewName("statistic");
        return modelAndView;
    }

    @RequestMapping(value = "/stopServer", method = RequestMethod.GET)
    private ModelAndView stopServer() {
        ModelAndView modelAndView = new ModelAndView();
        managerNode.stopCollect();
        modelAndView.addObject("started", ManagerNode.isStarted);
        modelAndView.addObject("clientCount", ManagerNode.getCountClients());
        modelAndView.setViewName("statistic");
        return modelAndView;
    }


}
