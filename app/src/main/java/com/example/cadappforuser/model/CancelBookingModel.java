
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelBookingModel {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("customer_profile")
    @Expose
    private String customerProfile;
    @SerializedName("customer_fname")
    @Expose
    private String customerFname;
    @SerializedName("customer_lname")
    @Expose
    private String customerLname;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("price")
    @Expose
    private String price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerProfile() {
        return customerProfile;
    }

    public void setCustomerProfile(String customerProfile) {
        this.customerProfile = customerProfile;
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

}
