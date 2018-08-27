package com.staycon.facade.impl;

import com.staycon.facade.ProfileFacade;
import com.staycon.models.PrincipalModel;
import com.staycon.models.ProfileModel;
import com.staycon.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultProfileFacade implements ProfileFacade {

    @Autowired
    ProfileService profileService;

    @Override
    public PrincipalModel findByEmail(String email) {
        return profileService.getUser(email);
    }

    @Override
    public ProfileModel getUserProfile(PrincipalModel principalModel) {
        return profileService.getUserProfile (principalModel);
    }

    @Override
    public void save(ProfileModel profileModel) {
        profileService.save(profileModel);
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
