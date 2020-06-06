package com.example.cadappforuser.model;

public class CartModel  {
    int serImage;
    String serName,serPrice,serSample;

    public CartModel(int serImage, String serName, String serPrice, String serSample) {
        this.serImage = serImage;
        this.serName = serName;
        this.serPrice = serPrice;
        this.serSample = serSample;
    }

    public int getSerImage() {
        return serImage;
    }

    public void setSerImage(int serImage) {
        this.serImage = serImage;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public String getSerPrice() {
        return serPrice;
    }

    public void setSerPrice(String serPrice) {
        this.serPrice = serPrice;
    }

    public String getSerSample() {
        return serSample;
    }

    public void setSerSample(String serSample) {
        this.serSample = serSample;
    }
}
