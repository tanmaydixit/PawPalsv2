package com.macbitsgoa.pawpals;

/*
 Created by rajath reghunath on 16-Mar-18.
 */

public class Contacts {

    private String name;
    private String photoUrl;
    private String phoneNo;
    private String email;
    private String designation;

    public Contacts(String name, String photoUrl, String phoneNo, String email, String designation) {

        this.name = name;
        this.photoUrl = photoUrl;
        this.phoneNo = phoneNo;
        this.email = email;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
