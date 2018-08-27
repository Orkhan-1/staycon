package com.staycon.models;

import org.owasp.html.PolicyFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="profile")
public class ProfileModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne (targetEntity = PrincipalModel.class)
    @JoinColumn (name="principal_id", nullable=false)
    private PrincipalModel principalModel;

    @Column(name = "about", length = 5000)
    @Size (max=5000, message = "{profile.about.size}")
    private String about;

    private String imageDirectory;
    private String imageName;
    private String imageExtension;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrincipalModel getPrincipalModel() {
        return principalModel;
    }

    public void setPrincipalModel(PrincipalModel principalModel) {
        this.principalModel = principalModel;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void saveCopy (ProfileModel profileModel) {
        if (profileModel.about != null) {
            this.about = profileModel.about;
        }
    }

    public void addToBean(ProfileModel profile, PolicyFactory policyFactory) {

        if (profile.about != null) {
            this.about = policyFactory.sanitize(profile.about);
        }
    }

    public String getImageDirectory() {
        return imageDirectory;
    }

    public void setImageDirectory(String imageDirectory) {
        this.imageDirectory = imageDirectory;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }
}