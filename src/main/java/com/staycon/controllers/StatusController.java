package com.staycon.controllers;

import javax.validation.Valid;

import com.staycon.facade.StatusFacade;
import com.staycon.models.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatusController {

    @Autowired
    private StatusFacade statusFacade;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ModelAndView getStatus(ModelAndView modelAndView, @ModelAttribute StatusModel statusModel) {
        modelAndView.setViewName("det.addStatus");
        StatusModel lastAddedStatus = getStatusFacade().getLastAdded();
        modelAndView.getModel().put("lastAddedStatus", lastAddedStatus);
        return modelAndView;
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public ModelAndView postStatus(ModelAndView modelAndView, @Valid StatusModel statusModel, BindingResult bindingResult) {
        modelAndView.setViewName("det.addStatus");
        if (!bindingResult.hasErrors()) {
            getStatusFacade().save(statusModel);
            modelAndView.getModel().put("statusModel", new StatusModel());
            modelAndView.setViewName("redirect:/displayStatus");
        }
        StatusModel lastAddedStatus = statusFacade.getLastAdded();
        modelAndView.getModel().put("lastAddedStatus", lastAddedStatus);
        return modelAndView;
    }

    @RequestMapping(value = "/displayStatus", method = RequestMethod.GET)
    public ModelAndView displayStatus(ModelAndView modelAndView, @RequestParam(name = "p", defaultValue = "1") int pageNumber) {
        Page page = getStatusFacade().getPage(pageNumber);
        modelAndView.getModel().put("page", page);
        modelAndView.setViewName("det.displayStatus");
        return modelAndView;
    }

    @RequestMapping(value = "/deletestatus", method = RequestMethod.GET)
    public ModelAndView deleteStatus(ModelAndView modelAndView, @RequestParam(value = "id") Long id) {
        modelAndView.setViewName("redirect:/displayStatus");
        getStatusFacade().deleteStatus(id);
        return modelAndView;
    }

    @RequestMapping(value = "/editstatus", method = RequestMethod.GET)
    public ModelAndView editStatus(ModelAndView modelAndView, @RequestParam(value = "id") Long id) {
        modelAndView.setViewName("det.editStatus");
        StatusModel statusModel = getStatusFacade().getStatusById(id);
        modelAndView.getModel().put("statusModel", statusModel);
        return modelAndView;
    }

    @RequestMapping(value = "/editstatus", method = RequestMethod.POST)
    public ModelAndView editStatus(ModelAndView modelAndView, StatusModel statusModel, BindingResult bindingResult) {
        modelAndView.setViewName("det.editStatus");
        if (!bindingResult.hasErrors()) {
            getStatusFacade().save(statusModel);
            modelAndView.setViewName("redirect:/displayStatus");
        }
        return modelAndView;
    }

    public StatusFacade getStatusFacade() {
        return statusFacade;
    }

    public void setStatusFacade(StatusFacade statusFacade) {
        this.statusFacade = statusFacade;
    }
}
