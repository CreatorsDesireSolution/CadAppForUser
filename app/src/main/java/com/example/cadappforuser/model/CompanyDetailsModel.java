
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyDetailsModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("companyname")
    @Expose
    private String companyname;
    @SerializedName("regnumber")
    @Expose
    private String regnumber;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("mobilenumber")
    @Expose
    private String mobilenumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("no_of_staff")
    @Expose
    private String noOfStaff;
    @SerializedName("about_company")
    @Expose
    private String aboutCompany;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("about_your_company")
    @Expose
    private String aboutYourCompany;
    @SerializedName("total_year_establishment")
    @Expose
    private String totalYearEstablishment;
    @SerializedName("team_size")
    @Expose
    private String teamSize;
    @SerializedName("no_of_men")
    @Expose
    private String noOfMen;
    @SerializedName("no_of_women")
    @Expose
    private String noOfWomen;
    @SerializedName("availability")
    @Expose
    private String availability;
    @SerializedName("cancel_policy")
    @Expose
    private String cancelPolicy;
    @SerializedName("acceptance")
    @Expose
    private String acceptance;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getNoOfStaff() {
        return noOfStaff;
    }

    public void setNoOfStaff(String noOfStaff) {
        this.noOfStaff = noOfStaff;
    }

    public String getAboutCompany() {
        return aboutCompany;
    }

    public void setAboutCompany(String aboutCompany) {
        this.aboutCompany = aboutCompany;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAboutYourCompany() {
        return aboutYourCompany;
    }

    public void setAboutYourCompany(String aboutYourCompany) {
        this.aboutYourCompany = aboutYourCompany;
    }

    public String getTotalYearEstablishment() {
        return totalYearEstablishment;
    }

    public void setTotalYearEstablishment(String totalYearEstablishment) {
        this.totalYearEstablishment = totalYearEstablishment;
    }

    public String getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(String teamSize) {
        this.teamSize = teamSize;
    }

    public String getNoOfMen() {
        return noOfMen;
    }

    public void setNoOfMen(String noOfMen) {
        this.noOfMen = noOfMen;
    }

    public String getNoOfWomen() {
        return noOfWomen;
    }

    public void setNoOfWomen(String noOfWomen) {
        this.noOfWomen = noOfWomen;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCancelPolicy() {
        return cancelPolicy;
    }

    public void setCancelPolicy(String cancelPolicy) {
        this.cancelPolicy = cancelPolicy;
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
