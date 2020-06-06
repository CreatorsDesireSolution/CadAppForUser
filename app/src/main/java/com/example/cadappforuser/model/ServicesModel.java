package com.example.cadappforuser.model;

public class ServicesModel {

    int image;
    String servicesName,serviceSample;

    public ServicesModel(int image, String servicesName, String serviceSample) {
        this.image = image;
        this.servicesName = servicesName;
        this.serviceSample = serviceSample;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public String getServiceSample() {
        return serviceSample;
    }

    public void setServiceSample(String serviceSample) {
        this.serviceSample = serviceSample;
    }
}
