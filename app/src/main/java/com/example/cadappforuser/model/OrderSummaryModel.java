
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderSummaryModel {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("statement")
    @Expose
    private String statement;

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

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
