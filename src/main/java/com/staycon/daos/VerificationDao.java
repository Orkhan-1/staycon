package com.staycon.daos;

import com.staycon.models.VerificationTokenModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationDao extends CrudRepository<VerificationTokenModel, Long> {

    VerificationTokenModel findByToken (String token);
}