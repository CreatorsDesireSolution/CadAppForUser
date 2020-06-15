package com.example.cadappforuser.ServiceModel;

public class AllServiceModel {

    int imageservice;
    String serviceprice,servicename,sampleservice;


    public AllServiceModel(int imageservice, String serviceprice, String servicename, String sampleservice) {
        this.imageservice = imageservice;
        this.serviceprice = serviceprice;
        this.servicename = servicename;
        this.sampleservice = sampleservice;
    }

    public int getImageservice() {
        return imageservice;
    }

    public void setImageservice(int imageservice) {
        this.imageservice = imageservice;
    }

    public String getServiceprice() {
        return serviceprice;
    }

    public void setServiceprice(String serviceprice) {
        this.serviceprice = serviceprice;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getSampleservice() {
        return sampleservice;
    }

    public void setSampleservice(String sampleservice) {
        this.sampleservice = sampleservice;
    }
}
