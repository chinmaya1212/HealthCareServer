package com.HealthCare.HealthCare.models;

import org.springframework.data.annotation.Id;

public class Doctors {
    String name;
    String specialist;
    String clicn;
    @Id
    String email;
    String abount;
    String profile;

    public Doctors(String profile) {
        this.profile = profile;
    }

    public Doctors(String name, String specialist, String clicn, String email, String abount) {
        this.name = name;
        this.specialist = specialist;
        this.clicn = clicn;
        this.email = email;
        this.abount = abount;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getClicn() {
        return clicn;
    }

    public void setClicn(String clicn) {
        this.clicn = clicn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbount() {
        return abount;
    }

    public void setAbount(String abount) {
        this.abount = abount;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}

