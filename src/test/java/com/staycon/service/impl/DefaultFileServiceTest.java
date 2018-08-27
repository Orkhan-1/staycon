package com.staycon.service.impl;

import java.io.File;
import java.lang.reflect.Method;

import javax.transaction.Transactional;

import com.staycon.service.impl.DefaultFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.staycon.StayConApplication;
import com.staycon.service.FileService;

import static org.junit.Assert.*;

/**
 * Created by Orkhan Gasanov on May 07, 2018
 */

/*@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = StayConApplication.class)
@Transactional*/
public class DefaultFileServiceTest {

    @Autowired
    private FileService fileService;

    @Value("${photo.upload.directory}")
    private String basePath;

   /* @Test
    public void testGetFileExtensions () throws Exception {

        Method method = DefaultFileService.class.getDeclaredMethod("getFileExtension", String.class);
        method.setAccessible(true);

        assertEquals("should be equal to png", "png", (String) method.invoke(fileService, "test.png"));
        assertEquals("should be equal to png", "png", (String) method.invoke(fileService, "test1.png"));
        assertEquals("should be equal to png", "png", (String) method.invoke(fileService, "test2.png"));
        assertEquals("should be equal to jpeg", "jpeg", (String) method.invoke(fileService, "test.jpeg"));

    }

    @Test
    public void testisImageExtension () throws Exception {

        Method method = DefaultFileService.class.getDeclaredMethod("isImageExtension", String.class);
        method.setAccessible(true);

        assertEquals("should be equal to png", true,  method.invoke(fileService, "png"));
        assertEquals("should be equal to png", true,  method.invoke(fileService, "jpeg"));
        assertEquals("should be equal to png", true,  method.invoke(fileService, "JPG"));

    }

    @Test
    public void testmakeSubdirectory () throws Exception {

        Method method = DefaultFileService.class.getDeclaredMethod("makeSubdirectory", String.class, String.class);
        method.setAccessible(true);

        for (int i=0; i<10000; i++) {
            File file = (File) method.invoke(fileService, basePath, "photo");
            assertTrue("Dirtectory should exist "+ file.getAbsolutePath(), file.exists());
        }
    }*/
}