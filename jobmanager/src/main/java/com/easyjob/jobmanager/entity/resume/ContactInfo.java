package com.easyjob.jobmanager.entity.resume;

import com.easyjob.jobmanager.entity.resume.Resume;

import javax.persistence.*;

@Entity
public class ContactInfo {

    @Id
    @SequenceGenerator(name = "contact_info_sequence", sequenceName = "contact_info_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_info_sequence")
    @Column(nullable = false, updatable = false)
    private Long id;

    private String country;

    private String address;

    private String skype;

    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
