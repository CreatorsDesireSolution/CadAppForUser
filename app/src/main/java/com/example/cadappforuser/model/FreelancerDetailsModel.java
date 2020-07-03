
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreelancerDetailsModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobilenumber")
    @Expose
    private String mobilenumber;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("avalkm")
    @Expose
    private String avalkm;
    @SerializedName("at_my_place")
    @Expose
    private String atMyPlace;
    @SerializedName("avalibility")
    @Expose
    private String avalibility;
    @SerializedName("cancel_policy")
    @Expose
    private String cancelPolicy;
    @SerializedName("about_yourself")
    @Expose
    private String aboutYourself;
    @SerializedName("curr_workplace")
    @Expose
    private String currWorkplace;
    @SerializedName("prev_workplace")
    @Expose
    private String prevWorkplace;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("certificate")
    @Expose
    private String certificate;
    @SerializedName("pic_work_performed")
    @Expose
    private String picWorkPerformed;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAvalkm() {
        return avalkm;
    }

    public void setAvalkm(String avalkm) {
        this.avalkm = avalkm;
    }

    public String getAtMyPlace() {
        return atMyPlace;
    }

    public void setAtMyPlace(String atMyPlace) {
        this.atMyPlace = atMyPlace;
    }

    public String getAvalibility() {
        return avalibility;
    }

    public void setAvalibility(String avalibility) {
        this.avalibility = avalibility;
    }

    public String getCancelPolicy() {
        return cancelPolicy;
    }

    public void setCancelPolicy(String cancelPolicy) {
        this.cancelPolicy = cancelPolicy;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    public String getCurrWorkplace() {
        return currWorkplace;
    }

    public void setCurrWorkplace(String currWorkplace) {
        this.currWorkplace = currWorkplace;
    }

    public String getPrevWorkplace() {
        return prevWorkplace;
    }

    public void setPrevWorkplace(String prevWorkplace) {
        this.prevWorkplace = prevWorkplace;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getPicWorkPerformed() {
        return picWorkPerformed;
    }

    public void setPicWorkPerformed(String picWorkPerformed) {
        this.picWorkPerformed = picWorkPerformed;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
