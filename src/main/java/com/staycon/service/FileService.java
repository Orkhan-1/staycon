package com.staycon.service;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import com.staycon.models.ImageModel;
import com.staycon.models.ProfileModel;

/**
 * Created by Orkhan Gasanov on May 07, 2018
 */
public interface FileService {

    ImageModel saveImage (MultipartFile file, String baseDirectory, String basePrefix, String imagePrefix) throws Exception;
    void setImageMetaData (ProfileModel profileModel, ImageModel imageModel);
    Path getFullImagePath(ProfileModel profile);
    void deleteOldFile(ProfileModel profile);
}
