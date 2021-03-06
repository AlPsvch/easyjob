package com.easyjob.jobmanager.entity.resume;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Education implements Serializable {

    @Id
    @SequenceGenerator(name = "education_sequence", sequenceName = "education_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "education_sequence")
    @Column(nullable = false, updatable = false)
    private Long id;

    private String university;

    private String faculty;

    private String qualification;

    private Integer entranceYear;

    private Integer graduationYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Integer getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Integer entranceYear) {
        this.entranceYear = entranceYear;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }
}
