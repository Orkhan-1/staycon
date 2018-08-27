package com.staycon.facade.impl;

import com.staycon.facade.PrincipalFacade;
import com.staycon.models.PrincipalModel;
import com.staycon.models.VerificationTokenModel;
import com.staycon.service.impl.DefaultPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultPrincipalFacade implements PrincipalFacade {

    @Autowired
    DefaultPrincipalService principalService;

    @Override
    public void register(PrincipalModel principalModel) {
        principalService.register(principalModel);
    }

    @Override
    public void update(PrincipalModel principalModel) {
        principalService.update(principalModel);
    }

    @Override
    public String createEmailVerificationToken(PrincipalModel principalModel) {
        return principalService.createEmailVerificationToken(principalModel);
    }

    public VerificationTokenModel getVerificationToken(String token) {
        return principalService.getVerificationToken(token);
    }

    @Override
    public void deleteToken(VerificationTokenModel verificationTokenModel) {

    }

}