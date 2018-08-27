package com.staycon.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${message.global.exception}")
    private String glonalExceptionMessage;

    @Value("${message.data.exception}")
    private String dataExceptionMessage;

    @ExceptionHandler (value=Exception.class)
    public ModelAndView defaultExceptionHandler (HttpServletRequest request, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("message", glonalExceptionMessage);
        modelAndView.getModel().put("url", request.getRequestURL());
        modelAndView.getModel().put("uri", request.getRequestURI());
        modelAndView.getModel().put("exception", exception);
        modelAndView.setViewName("det.exception");
        return modelAndView;
    }

    @ExceptionHandler (value=DataIntegrityViolationException.class)
    public ModelAndView dataExceptionHandler (HttpServletRequest request, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("message", dataExceptionMessage);
        modelAndView.getModel().put("url", request.getRequestURL());
        modelAndView.getModel().put("uri", request.getRequestURI());
        modelAndView.getModel().put("exception", exception);
        modelAndView.setViewName("det.exception");
        return modelAndView;
    }

    @ExceptionHandler (value=MultipartException.class)
    @ResponseBody
    public String largeFileUploadHandler (Exception ex) {
        ex.printStackTrace();
        return "Large file cannot be uploaded!";
    }
}