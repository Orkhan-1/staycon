package com.staycon.controllers;

import com.staycon.StayConApplication;
import com.staycon.daos.StatusDao;
import com.staycon.models.StatusModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = StayConApplication.class)
@Transactional
public class StatusTest {

    @Autowired
    private StatusDao statusDao;

    @Test
    public void testSave () {
        StatusModel model = new StatusModel ("It should add to database");
        statusDao.save(model);
        assertNotNull("Shouldn't be null", model.getId());

        StatusModel foundModel = statusDao.findOne(model.getId());

        assertEquals("Should be equal", model, foundModel);
    }

    @Test
    public void testFindLatest() {

        Calendar calendar = Calendar.getInstance();

        StatusModel lastStatusUpdate = null;

        for (int i = 0; i < 10; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);

            StatusModel status = new StatusModel("Status update " + i, calendar.getTime());

            statusDao.save(status);

            lastStatusUpdate = status;
        }

        StatusModel retrieved = statusDao.findFirstByOrderByAddedDateDesc();

        assertEquals("Latest status update", lastStatusUpdate, retrieved);
    }
}