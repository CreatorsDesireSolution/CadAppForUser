
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyProfileDataModel {

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
    @SerializedName("aboutcompany")
    @Expose
    private String aboutcompany;
    @SerializedName("flag")
    @Expose
    private String flag;

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

    public String getAboutcompany() {
        return aboutcompany;
    }

    public void setAboutcompany(String aboutcompany) {
        this.aboutcompany = aboutcompany;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
