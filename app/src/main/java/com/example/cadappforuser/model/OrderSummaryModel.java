package com.example.cadappforuser.model;

public class OrderSummaryModel {
    String ordPrice,ordName,ordSample;

    public OrderSummaryModel(String ordPrice, String ordName, String ordSample) {
        this.ordPrice = ordPrice;
        this.ordName = ordName;
        this.ordSample = ordSample;
    }

    public String getOrdPrice() {
        return ordPrice;
    }

    public void setOrdPrice(String ordPrice) {
        this.ordPrice = ordPrice;
    }

    public String getOrdName() {
        return ordName;
    }

    public void setOrdName(String ordName) {
        this.ordName = ordName;
    }

    public String getOrdSample() {
        return ordSample;
    }

    public void setOrdSample(String ordSample) {
        this.ordSample = ordSample;
    }
}
