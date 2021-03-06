package com.example.cadappforuser.adapter;

public class FevListModel {

    String image;
    String price,sample,name,key_id,fav_status;

    public FevListModel(String image, String price, String sample, String name,String key_id,String fav_status) {
        this.image = image;
        this.price = price;
        this.sample = sample;
        this.name = name;
        this.key_id=key_id;
        this.fav_status=fav_status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFav_status() {
        return fav_status;
    }

    public void setFav_status(String fav_status) {
        this.fav_status = fav_status;
    }
}

