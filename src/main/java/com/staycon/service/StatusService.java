package com.staycon.service;

import org.springframework.data.domain.Page;

import com.staycon.models.StatusModel;

public interface StatusService {

    void save (StatusModel status);
    StatusModel getLastAdded ();
    Page getPage(int pageNumber);
    void deleteStatus (Long id);
    StatusModel getStatusById(Long id);
}
