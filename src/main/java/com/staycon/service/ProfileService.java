package com.staycon.service;

import com.staycon.models.PrincipalModel;
import com.staycon.models.ProfileModel;

public interface ProfileService {

    void save (ProfileModel profileModel);
    PrincipalModel getUser (String email);
    ProfileModel getUserProfile (PrincipalModel principalModel);

}
