
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreelancerOrderModel {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("customer_fname")
    @Expose
    private String customer_fname;
    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("customer_lname")
    @Expose
    private String customer_lname;


    @SerializedName("customer_id")
    @Expose
    private String customer_id;

    public String getCustomer_fname() {
        return customer_fname;
    }

    public void setCustomer_fname(String customer_fname) {
        this.customer_fname = customer_fname;
    }

    public String getCustomer_lname() {
        return customer_lname;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_lname(String customer_lname) {
        this.customer_lname = customer_lname;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customer_fname;
    }

    public void setCustomerName(String customerName) {
        this.customer_fname = customerName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
