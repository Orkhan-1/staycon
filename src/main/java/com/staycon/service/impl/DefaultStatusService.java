package com.staycon.service.impl;

import com.staycon.daos.StatusDao;
import com.staycon.models.StatusModel;
import com.staycon.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DefaultStatusService implements StatusService {

    private final static int SIZE = 3;
    @Autowired
    private StatusDao statusDao;

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED, propagation= Propagation.REQUIRES_NEW, rollbackFor=Exception.class, timeout = 5, readOnly = false)
    public void save(StatusModel status) {
        getStatusDao().save(status);
    }

    @Override
    public Page<StatusModel> getPage(int pageNumber) {
        PageRequest page = new PageRequest(pageNumber - 1, SIZE, Sort.Direction.DESC, "addedDate");
        return getStatusDao().findAll(page);
    }

    @Override
    public void deleteStatus(Long id) {
        getStatusDao().delete(id);
    }

    @Override
    public StatusModel getStatusById(Long id) {
        return getStatusDao().findOne(id);
    }

    @Override
    public StatusModel getLastAdded() {
        return getStatusDao().findFirstByOrderByAddedDateDesc();
    }

    public StatusDao getStatusDao() {
        return statusDao;
    }

    public void setStatusDao(StatusDao statusDao) {
        this.statusDao = statusDao;
    }
}
