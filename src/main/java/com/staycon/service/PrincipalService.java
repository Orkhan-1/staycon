package com.staycon.service;

import com.staycon.models.PrincipalModel;
import com.staycon.models.VerificationTokenModel;

public interface PrincipalService {

    void register(PrincipalModel principalModel);

    void update (PrincipalModel principalModel);

    String createEmailVerificationToken (PrincipalModel principalModel);

    VerificationTokenModel getVerificationToken (String token);

    void deleteToken (VerificationTokenModel verificationTokenModel);
}