package com.staycon.facade.impl;

import com.staycon.models.StatusModel;
import com.staycon.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.staycon.facade.StatusFacade;

@Component
public class DefaultStatusFacade implements StatusFacade {

    @Autowired
    StatusService statusService;

    @Override
    public void save(StatusModel status) {
        getStatusService().save(status);
    }

    @Override
    public Page getPage(int pageNumber) {
        return getStatusService().getPage(pageNumber);
    }

    @Override
    public void deleteStatus(Long id) {
        getStatusService().deleteStatus(id);
    }

    @Override
    public StatusModel getStatusById(Long id) {
        return getStatusService().getStatusById(id);
    }

    @Override
    public StatusModel getLastAdded() {
        return getStatusService().getLastAdded();
    }

    public StatusService getStatusService() {
        return statusService;
    }

    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }
}
