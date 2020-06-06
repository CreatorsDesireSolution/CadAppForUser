package com.example.cadappforuser.model;

public class ServiceSearchModel {
    int imageServices;
    String placeServices,nameServices;

    public ServiceSearchModel(int imageServices, String placeServices, String nameServices) {
        this.imageServices = imageServices;
        this.placeServices = placeServices;
        this.nameServices = nameServices;
    }

    public int getImageServices() {
        return imageServices;
    }

    public void setImageServices(int imageServices) {
        this.imageServices = imageServices;
    }

    public String getPlaceServices() {
        return placeServices;
    }

    public void setPlaceServices(String placeServices) {
        this.placeServices = placeServices;
    }

    public String getNameServices() {
        return nameServices;
    }

    public void setNameServices(String nameServices) {
        this.nameServices = nameServices;
    }
}
