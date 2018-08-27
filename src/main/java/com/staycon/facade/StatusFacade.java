package com.staycon.facade;

import com.staycon.models.StatusModel;
import org.springframework.data.domain.Page;

public interface StatusFacade {

    void save(StatusModel status);
    Page getPage(int pageNumber);
    void deleteStatus(Long id);
    StatusModel getStatusById(Long id);
    StatusModel getLastAdded();
}