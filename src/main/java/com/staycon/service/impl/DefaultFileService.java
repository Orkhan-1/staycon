package com.staycon.service.impl;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.staycon.exception.InvalidFileException;
import com.staycon.models.ImageModel;
import com.staycon.models.ProfileModel;
import com.staycon.service.FileService;

import javax.imageio.ImageIO;

/**
 * Created by Orkhan Gasanov on May 07, 2018
 */

@Service
public class DefaultFileService implements FileService {

    @Value("${image.extensions}")
    private String fileExtensions;

    @Value("${photo.upload.directory}")
    private String baseDirectory;

    private Random random = new Random();

    private final int WIDTH=100;
    private final int HEIGHT=100;

    private String getFileExtension(String fileName) {
        int dotFile = fileName.lastIndexOf(".");
        if (dotFile < 0) {
            return null;
        }
        return fileName.substring(dotFile + 1).toLowerCase();
    }

    private boolean isImageExtension(String extension) {

        String[] extensions = fileExtensions.split(",");

        for (String test : extensions) {
            if (extension.equalsIgnoreCase(test)) {
                return true;
            }
            ;
        }
        return false;
    }

    private File makeSubdirectory(String basePath, String prefix) {
        int nDirectory = random.nextInt(1000);
        String sDirectory = String.format("%s%03d", prefix, nDirectory);

        File directory = new File(basePath, sDirectory);

        if (!directory.exists()) {
            directory.mkdir();
        }
        return directory;
    }

    @Override
    public ImageModel saveImage(MultipartFile file, String baseDirectory, String basePrefix, String imagePrefix) throws Exception {

        int imageDirectory = random.nextInt(1000);
        String imageName = String.format("%s%03d", imagePrefix, imageDirectory);
        String extension = getFileExtension(file.getOriginalFilename());

        if (extension == null) {
            throw new InvalidFileException("Extesion is null");
        }

        if (!isImageExtension(extension)) {
            throw new InvalidFileException("Uknown Extension");
        }
        String fullImageName = imageName + "." + extension;
        File subDirectory = makeSubdirectory(baseDirectory, basePrefix);
        Path outputPathFile = Paths.get (subDirectory.getCanonicalPath(), fullImageName);
        BufferedImage resizedPhoto = resizePhoto (file, WIDTH, HEIGHT);
        ImageIO.write(resizedPhoto, extension, outputPathFile.toFile());
        return new ImageModel(imageName, extension, subDirectory.getName(), baseDirectory);
    }


    private BufferedImage resizePhoto (MultipartFile file, int width, int height) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());

        if (image.getWidth()<width||image.getHeight()<height) {
            throw new IOException();
        }
        double widthScale = (double) width/image.getWidth();
        double heightScale = (double) height/image.getHeight();
        double scale = Math.max(widthScale, heightScale);
        BufferedImage bufferedImage = new BufferedImage((int) scale*image.getHeight(), (int)scale*image.getHeight(), image.getType());
        Graphics2D graphics2D = bufferedImage.createGraphics();
        AffineTransform transform = AffineTransform.getScaleInstance (scale, scale);
        graphics2D.drawImage(image, transform, null);
        return bufferedImage.getSubimage (0,0,width,height);
    }

    @Override
    public void setImageMetaData(ProfileModel profileModel, ImageModel imageModel) {
        profileModel.setImageDirectory(imageModel.getSubDirectory());
        profileModel.setImageName(imageModel.getImageName());
        profileModel.setImageExtension(imageModel.getExtension());
    }

    @Override
    public void deleteOldFile(ProfileModel profile) {
        try {
            Files.deleteIfExists(getFullImagePath(profile));
            Files.deleteIfExists(getImagePath(profile));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public Path getFullImagePath(ProfileModel profileModel) {
        Path paths = Paths.get(baseDirectory, "default", "default.jpg");
        if (profileModel.getImageName()!=null) {
            return Paths.get(baseDirectory, profileModel.getImageDirectory(), profileModel.getImageName() + "." + profileModel.getImageExtension());
        }
        return paths;
    }


    private Path getImagePath(ProfileModel profileModel) {
        Path paths = Paths.get(baseDirectory, "default", "default.jpg");
        if (profileModel.getImageName()!=null) {
            return Paths.get(baseDirectory, profileModel.getImageDirectory());
        }
        return paths;
    }

}