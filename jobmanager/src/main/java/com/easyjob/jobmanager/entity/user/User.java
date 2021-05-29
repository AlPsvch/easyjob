package com.easyjob.jobmanager.entity.user;

import com.easyjob.jobmanager.entity.resume.Resume;
import com.easyjob.jobmanager.entity.vacancy.Vacancy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(nullable = false, updatable = false)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private PersonalityType personalityType;

    @OneToMany
    private List<Vacancy> userVacancies;

    @OneToMany
    private List<Resume> userResumes;

    @OneToMany
    private List<Vacancy> savedVacancies;

    @OneToMany
    private List<Resume> savedResumes;

    private LocalDateTime registrationDate;

    private String role; //ROLE_USER, ROLE_ADMIN

    private boolean active;

    private boolean locked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PersonalityType getPersonalityType() {
        return personalityType;
    }

    public void setPersonalityType(PersonalityType personalityType) {
        this.personalityType = personalityType;
    }

    public List<Vacancy> getUserVacancies() {
        return userVacancies;
    }

    public void setUserVacancies(List<Vacancy> userVacancies) {
        this.userVacancies = userVacancies;
    }

    public List<Resume> getUserResumes() {
        return userResumes;
    }

    public void setUserResumes(List<Resume> userResumes) {
        this.userResumes = userResumes;
    }

    public List<Vacancy> getSavedVacancies() {
        return savedVacancies;
    }

    public void setSavedVacancies(List<Vacancy> savedVacancies) {
        this.savedVacancies = savedVacancies;
    }

    public List<Resume> getSavedResumes() {
        return savedResumes;
    }

    public void setSavedResumes(List<Resume> savedResumes) {
        this.savedResumes = savedResumes;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
