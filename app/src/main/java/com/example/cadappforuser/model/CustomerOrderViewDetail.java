
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerOrderViewDetail {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("freelancer_id")
    @Expose
    private String freelancerId;
    @SerializedName("freelancer_fname")
    @Expose
    private String freelancerFname;
    @SerializedName("freelancer_lname")
    @Expose
    private String freelancerLname;
    @SerializedName("freelancer_mobile")
    @Expose
    private String freelancerMobile;
    @SerializedName("freelancer_gender")
    @Expose
    private String freelancerGender;
    @SerializedName("freelancer_address")
    @Expose
    private String freelancerAddress;
    @SerializedName("freelancer_profile")
    @Expose
    private String freelancerProfile;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("service_qty")
    @Expose
    private String serviceQty;
    @SerializedName("service_time")
    @Expose
    private String serviceTime;
    @SerializedName("service_date")
    @Expose
    private String serviceDate;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getFreelancerFname() {
        return freelancerFname;
    }

    public void setFreelancerFname(String freelancerFname) {
        this.freelancerFname = freelancerFname;
    }

    public String getFreelancerLname() {
        return freelancerLname;
    }

    public void setFreelancerLname(String freelancerLname) {
        this.freelancerLname = freelancerLname;
    }

    public String getFreelancerMobile() {
        return freelancerMobile;
    }

    public void setFreelancerMobile(String freelancerMobile) {
        this.freelancerMobile = freelancerMobile;
    }

    public String getFreelancerGender() {
        return freelancerGender;
    }

    public void setFreelancerGender(String freelancerGender) {
        this.freelancerGender = freelancerGender;
    }

    public String getFreelancerAddress() {
        return freelancerAddress;
    }

    public void setFreelancerAddress(String freelancerAddress) {
        this.freelancerAddress = freelancerAddress;
    }

    public String getFreelancerProfile() {
        return freelancerProfile;
    }

    public void setFreelancerProfile(String freelancerProfile) {
        this.freelancerProfile = freelancerProfile;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getServiceQty() {
        return serviceQty;
    }

    public void setServiceQty(String serviceQty) {
        this.serviceQty = serviceQty;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

}
