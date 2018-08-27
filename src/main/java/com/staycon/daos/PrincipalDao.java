package com.staycon.daos;

import com.staycon.models.PrincipalModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalDao extends CrudRepository<PrincipalModel, Long> {

    PrincipalModel findByEmail(String email);
}
