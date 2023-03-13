package com.HealthCare.HealthCare.models;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Doctors")
public class Doctors {
    @Id
    String email;
    String name;
    String specialist;
    String clinc;
    @Indexed(expireAfterSeconds = 43200)
    public String about;
    String profile;
    boolean slot10_11;
    boolean slot11_12;
    boolean  slot1_2;
    boolean slot2_3;
    boolean ishomvisit;

    private LocalDateTime lastUpdated;


    public Doctors() {
        // Default constructor required for MongoDB
    }

    public Doctors(String email, String name, String specialist, String clinc, String about, String profile, boolean slot10_11, boolean slot11_12, boolean slot1_2, boolean slot2_3, boolean ishomvisit, LocalDateTime lastUpdated) {
        this.email = email;
        this.name = name;
        this.specialist = specialist;
        this.clinc = clinc;
        this.about = about;
        this.profile = profile;
        this.slot10_11 = slot10_11;
        this.slot11_12 = slot11_12;
        this.slot1_2 = slot1_2;
        this.slot2_3 = slot2_3;
        this.ishomvisit = ishomvisit;
        this.lastUpdated = lastUpdated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getClinc() {
        return clinc;
    }

    public void setClinc(String clinc) {
        this.clinc = clinc;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isSlot10_11() {
        return slot10_11;
    }

    public void setSlot10_11(boolean slot10_11) {
        this.slot10_11 = slot10_11;
    }

    public boolean isSlot11_12() {
        return slot11_12;
    }

    public void setSlot11_12(boolean slot11_12) {
        this.slot11_12 = slot11_12;
    }

    public boolean isSlot1_2() {
        return slot1_2;
    }

    public void setSlot1_2(boolean slot1_2) {
        this.slot1_2 = slot1_2;
    }

    public boolean isSlot2_3() {
        return slot2_3;
    }

    public void setSlot2_3(boolean slot2_3) {
        this.slot2_3 = slot2_3;
    }

    public boolean isIshomvisit() {
        return ishomvisit;
    }

    public void setIshomvisit(boolean ishomvisit) {
        this.ishomvisit = ishomvisit;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
