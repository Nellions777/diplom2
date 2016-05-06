package student.diplom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import student.diplom.web.entities.TypeTask;
import student.diplom.web.services.TypeTaskService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 24.03.2016.
 */
@RestController
public class OptionController {
    @Autowired
    private TypeTaskService typeTaskService;

    private long currentTypeTaskId = -1;

    @RequestMapping(value = "/option", method = RequestMethod.GET)
    private ModelAndView options() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("option");
        return modelAndView;
    }

    @RequestMapping(value = "/typeTask", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getTypeTask() {

        if(currentTypeTaskId == -1) {
            currentTypeTaskId = getCurrentTypeTaskId();
        }

        List<TypeTask> tasks = typeTaskService.findAll();
        List<Object> res = new ArrayList();
        res.add(currentTypeTaskId);
        res.add(tasks);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/choseTypeTask", method = RequestMethod.PUT)
    public ResponseEntity<Long> putChoseTypeTask(@RequestBody Long typeTask){

        this.currentTypeTaskId = typeTask;

        return new ResponseEntity<>(typeTask, HttpStatus.OK);
    }

    public long getCurrentTypeTaskId() {
        if(currentTypeTaskId == -1) return typeTaskService.findAll().get(0).getId();
        else return currentTypeTaskId;
    }
}
