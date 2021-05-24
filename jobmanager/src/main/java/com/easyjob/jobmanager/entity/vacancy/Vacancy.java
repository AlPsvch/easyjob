package com.easyjob.jobmanager.entity.vacancy;

import com.easyjob.jobmanager.entity.JobCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Vacancy implements Serializable {

    @Id
    @SequenceGenerator(name = "vacancy_sequence", sequenceName = "vacancy_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacancy_sequence")
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    private String country;

    private String city;

    private String experience;

    private String address;

    @Enumerated(EnumType.STRING)
    private EmploymentMode employmentMode;

    private String companyName;

    @Column(length = 1024)
    private String companyInfo;

    @Column(length = 1024)
    private String description;

    private String imageUrl;

    private boolean active;

    private String phone;

    private String email;

    private LocalDateTime publishDate;

    private Integer salary;

    @Enumerated(EnumType.STRING)
    private JobCategory vacancyCategory;

    public Vacancy() {
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmploymentMode getEmploymentMode() {
        return employmentMode;
    }

    public void setEmploymentMode(EmploymentMode employmentMode) {
        this.employmentMode = employmentMode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public JobCategory getVacancyCategory() {
        return vacancyCategory;
    }

    public void setVacancyCategory(JobCategory vacancyCategory) {
        this.vacancyCategory = vacancyCategory;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
