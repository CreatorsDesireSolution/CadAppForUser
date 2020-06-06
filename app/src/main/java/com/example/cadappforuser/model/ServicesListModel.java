package com.example.cadappforuser.model;

public class ServicesListModel {

    int image;
    String price,sample,name;

    public ServicesListModel(int image, String price, String sample, String name) {
        this.image = image;
        this.price = price;
        this.sample = sample;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
