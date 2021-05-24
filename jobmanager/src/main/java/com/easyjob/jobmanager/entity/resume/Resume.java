package com.easyjob.jobmanager.entity.resume;

import com.easyjob.jobmanager.entity.JobCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Resume implements Serializable {

    @Id
    @SequenceGenerator(name = "resume_sequence", sequenceName = "resume_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_sequence")
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    private String firstName;

    private String lastName;

    private String experience;

    @Column(length = 1024)
    private String about;

    @OneToOne
    private ContactInfo contactInfo;

    @OneToOne
    private Education education;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    private boolean active;

    private LocalDateTime publishDate;

    @Enumerated(EnumType.STRING)
    private JobCategory resumeCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public JobCategory getResumeCategory() {
        return resumeCategory;
    }

    public void setResumeCategory(JobCategory resumeCategory) {
        this.resumeCategory = resumeCategory;
    }
}
