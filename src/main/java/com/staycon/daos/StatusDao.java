package com.staycon.daos;

import com.staycon.models.StatusModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDao extends PagingAndSortingRepository<StatusModel, Long> {

    StatusModel findFirstByOrderByAddedDateDesc();
}
