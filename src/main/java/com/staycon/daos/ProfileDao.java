package com.staycon.daos;

import com.staycon.models.PrincipalModel;
import com.staycon.models.ProfileModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends CrudRepository <ProfileModel, Long> {

    ProfileModel findByPrincipalModel (PrincipalModel principalModel);
}