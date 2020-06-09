package com.example.cadappforuser.model;

public class OfferedWithPriceModel {
    String ServiceName,policy,price;

    public OfferedWithPriceModel(String serviceName, String policy) {
        ServiceName = serviceName;
        this.policy = policy;
        //this.price = price;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

   /* public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }*/
}
