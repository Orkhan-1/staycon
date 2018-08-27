package com.staycon.controllers;

import com.staycon.facade.PrincipalFacade;
import com.staycon.models.PrincipalModel;
import com.staycon.models.VerificationTokenModel;
import com.staycon.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class RegistrationController {

    @Autowired
    private PrincipalFacade principalFacade;

    @Autowired
    private EmailService emailService;

    @RequestMapping("/login")
    public String login() {
        return "det.login";
    }

    @Value("${message.login.confirmed}")
    private String registrationConfirmedMessage;

    @Value("${message.invalid.user}")
    private String invalidUser;

    @Value("${message.token.expired}")
    private String expiredToken;

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {

        PrincipalModel principal = new PrincipalModel();
        modelAndView.getModel().put("principal", principal);
        modelAndView.setViewName("det.register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute(value = "principal") @Valid PrincipalModel model, BindingResult result) {

        modelAndView.setViewName("det.register");

        if (!result.hasErrors()) {
            getPrincipalFacade().register(model);
            modelAndView.setViewName("redirect:/verify");
            String token = getPrincipalFacade().createEmailVerificationToken(model);
            getEmailService().sendVerificationEmail(model.getEmail(), token);
        }
        return modelAndView;
    }

    @RequestMapping("/verify")
    public String verify() {
        return "det.verify";
    }

    @GetMapping("/confirmRegistration")
    public ModelAndView registrationConfirmed(ModelAndView modelAndView, @RequestParam("t") String token) {

        VerificationTokenModel tokenModel = getPrincipalFacade().getVerificationToken(token);

        if (tokenModel==null) {
            modelAndView.setViewName("redirect:/invalidUser");
            return  modelAndView;
        }

        Date expiredDate = tokenModel.getExpiredDate();

        if (expiredDate.before(new Date())) {
            modelAndView.setViewName("redirect:/expiredToken");
            getPrincipalFacade().deleteToken (tokenModel);
            return  modelAndView;
        }

        PrincipalModel principalModel = tokenModel.getPrincipalModel();

        if (principalModel==null) {
            modelAndView.setViewName("redirect:/invalidUser");
            getPrincipalFacade().deleteToken (tokenModel);
            return  modelAndView;
        }

        principalModel.setEnabled(true);
        getPrincipalFacade().register(principalModel);

        getPrincipalFacade().deleteToken (tokenModel);
        modelAndView.setViewName("det.message");
        modelAndView.getModel().put("message", registrationConfirmedMessage);
        return modelAndView;
    }

    @GetMapping("/invalidUser")
    public ModelAndView invalidUser(ModelAndView modelAndView) {
        modelAndView.setViewName("det.message");
        modelAndView.getModel().put("message", invalidUser);
        return modelAndView;
    }

    @GetMapping("/expiredToken")
    public ModelAndView expiredToken(ModelAndView modelAndView) {
        modelAndView.setViewName("det.message");
        modelAndView.getModel().put("message", expiredToken);
        return modelAndView;
    }



    public PrincipalFacade getPrincipalFacade() {
        return principalFacade;
    }

    public void setPrincipalFacade(PrincipalFacade principalFacade) {
        this.principalFacade = principalFacade;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
