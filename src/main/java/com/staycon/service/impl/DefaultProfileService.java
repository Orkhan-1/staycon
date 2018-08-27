package com.staycon.service.impl;

import com.staycon.daos.PrincipalDao;
import com.staycon.daos.ProfileDao;
import com.staycon.models.PrincipalModel;
import com.staycon.models.ProfileModel;
import com.staycon.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultProfileService implements ProfileService {

    @Autowired
    private PrincipalDao principalDao;

    @Autowired
    private ProfileDao profileDao;

    @Override
    public void save(ProfileModel profileModel) {
        profileDao.save(profileModel);
    }

    @Override
    public PrincipalModel getUser (String email) {
        return principalDao.findByEmail(email);
    }

    @Override
    public ProfileModel getUserProfile(PrincipalModel principalModel) {
        return profileDao.findByPrincipalModel(principalModel);
    }

    public PrincipalDao getPrincipalDao() {
        return principalDao;
    }

    public void setPrincipalDao(PrincipalDao principalDao) {
        this.principalDao = principalDao;
    }

    public ProfileDao getProfileDao() {
        return profileDao;
    }

    public void setProfileDao(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }
}
