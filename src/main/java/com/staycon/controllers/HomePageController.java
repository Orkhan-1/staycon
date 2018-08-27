package com.staycon.controllers;

import com.staycon.facade.StatusFacade;
import com.staycon.models.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    @Autowired
    private StatusFacade statusFacade;

    @RequestMapping("/")
    ModelAndView home(ModelAndView modelAndView, @RequestParam(value = "name", required = false, defaultValue = "User") String name) {
        modelAndView.setViewName("det.homepage");
        StatusModel statusModel = getStatusFacade().getLastAdded();
        modelAndView.getModel().put("name", name);
        modelAndView.getModel().put("statusModel", statusModel);
        return modelAndView;
    }

    @RequestMapping("/about")
    public String about() {
        return "det.about";
    }

    public StatusFacade getStatusFacade() {
        return statusFacade;
    }

    public void setStatusFacade(StatusFacade statusFacade) {
        this.statusFacade = statusFacade;
    }

}