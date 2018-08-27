package com.staycon.controllers;

import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.validation.Valid;

import com.staycon.facade.ProfileFacade;
import com.staycon.models.ImageModel;
import com.staycon.models.PrincipalModel;
import com.staycon.models.ProfileModel;
import com.staycon.service.FileService;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    private ProfileFacade profileFacade;

    @Autowired
    private PolicyFactory policyFactory;

    @Value("${photo.upload.directory}")
    private String baseDirectory;

    private String basePrefix = "photo";
    private String imagePrefix = "image";

    @Autowired
    private FileService fileService;

    private PrincipalModel getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return profileFacade.findByEmail(email);
    }

    @GetMapping("/profile")
    public ModelAndView showProfile(ModelAndView modelAndView) {

        PrincipalModel user = getUser();
        ProfileModel profileModel = profileFacade.getUserProfile(user);

        if (profileModel == null) {
            profileModel = new ProfileModel();
            profileModel.setPrincipalModel(user);
            profileFacade.save(profileModel);
        }

        ProfileModel exposedProfile = new ProfileModel();
        exposedProfile.saveCopy(profileModel);
        modelAndView.getModel().put("profile", exposedProfile);
        modelAndView.setViewName("det.profile");
        return modelAndView;
    }

    @GetMapping("/editprofile")
    public ModelAndView editProfile(ModelAndView modelAndView) {

        PrincipalModel user = getUser();
        ProfileModel profileModel = profileFacade.getUserProfile(user);

        ProfileModel exposedProfile = new ProfileModel();
        exposedProfile.saveCopy(profileModel);
        modelAndView.getModel().put("profileModel", exposedProfile);
        modelAndView.setViewName("det.editprofile");
        return modelAndView;
    }

    @PostMapping("/editprofile")
    public ModelAndView saveEditedProfile(ModelAndView modelAndView, @Valid ProfileModel profileModel, BindingResult bindingResult) {

        modelAndView.setViewName("det.editprofile");
        PrincipalModel user = getUser();
        ProfileModel profile = profileFacade.getUserProfile(user);

        profile.addToBean(profileModel, policyFactory);

        if (!bindingResult.hasErrors()) {
            profileFacade.save(profile);
            modelAndView.setViewName("redirect:/profile");
        }
        return modelAndView;
    }

    @PostMapping("/upload-profile-photo")
    public ModelAndView saveEditedProfile(ModelAndView modelAndView, @RequestParam("file") MultipartFile file) throws Exception {
        modelAndView.setViewName("redirect:/profile");
        ProfileModel profile = profileFacade.getUserProfile(getUser());

        if (profile.getImageName()!=null) {
            fileService.deleteOldFile (profile);
        };

        ImageModel imageModel = fileService.saveImage(file, baseDirectory, basePrefix, imagePrefix);
        fileService.setImageMetaData(profile, imageModel);
        profileFacade.save(profile);
        return modelAndView;
    }


    @GetMapping ("/profilePhoto")
    public ResponseEntity <InputStreamResource> getPhoto () throws Exception {
        ProfileModel profile = profileFacade.getUserProfile(getUser());
        Path photoPath =  fileService.getFullImagePath(profile);
        return ResponseEntity
                        .ok()
                        .contentLength(Files.size(photoPath))
                        .contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(photoPath.toString())))
                        .body (new InputStreamResource(Files.newInputStream(photoPath, StandardOpenOption.READ)));
    }

    public ProfileFacade getProfileFacade() {
        return profileFacade;
    }

    public void setProfileFacade(ProfileFacade profileFacade) {
        this.profileFacade = profileFacade;
    }
}
