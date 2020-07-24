
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreviousBookingDetailsModel {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("customer_fname")
    @Expose
    private String customerFname;
    @SerializedName("customer_lname")
    @Expose
    private String customerLname;
    @SerializedName("customer_mobile")
    @Expose
    private String customerMobile;
    @SerializedName("customer_gender")
    @Expose
    private String customerGender;
    @SerializedName("customer_address")
    @Expose
    private String customerAddress;
    @SerializedName("customer_profile")
    @Expose
    private String customerProfile;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFname() {
        return customerFname;
    }

    public void setCustomerFname(String customerFname) {
        this.customerFname = customerFname;
    }

    public String getCustomerLname() {
        return customerLname;
    }

    public void setCustomerLname(String customerLname) {
        this.customerLname = customerLname;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerProfile() {
        return customerProfile;
    }

    public void setCustomerProfile(String customerProfile) {
        this.customerProfile = customerProfile;
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
