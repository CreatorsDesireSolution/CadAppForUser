
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaffDetailsModel {

    @SerializedName("staff_id")
    @Expose
    private String staffId;
    @SerializedName("companyid")
    @Expose
    private String companyid;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("mobilenumber")
    @Expose
    private String mobilenumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("certificate")
    @Expose
    private String certificate;
    @SerializedName("pic_work_performed")
    @Expose
    private String picWorkPerformed;
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
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("days")
    @Expose
    private String days;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
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

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

}
