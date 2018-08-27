package com.staycon.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorHandlingController {

    @Value("${message.access.denied}")
    private String accessForbiddenMessage;

    @GetMapping("/accessForbidden")
    public ModelAndView accessForbidden (ModelAndView modelAndView) {
        modelAndView.setViewName("det.message");
        modelAndView.getModel().put("message", accessForbiddenMessage);
        return modelAndView;
    }
}
