package com.staycon.models;

/**
 * Created by Orkhan Gasanov on May 10, 2018
 */

public class ImageModel {

    private String imageName;
    private String extension;
    private String subDirectory;
    private String baseDirectory;

    public ImageModel(String imageName, String extension, String subDirectory, String baseDirectory) {
        this.imageName = imageName;
        this.extension = extension;
        this.subDirectory = subDirectory;
        this.baseDirectory = baseDirectory;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSubDirectory() {
        return subDirectory;
    }

    public void setSubDirectory(String subDirectory) {
        this.subDirectory = subDirectory;
    }

    public String getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

}
