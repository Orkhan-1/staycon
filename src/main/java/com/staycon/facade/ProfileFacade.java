package com.staycon.facade;

import com.staycon.models.PrincipalModel;
import com.staycon.models.ProfileModel;

public interface ProfileFacade {

    PrincipalModel findByEmail (String email);
    ProfileModel getUserProfile (PrincipalModel principalModel);
    void save (ProfileModel profileModel);
}
