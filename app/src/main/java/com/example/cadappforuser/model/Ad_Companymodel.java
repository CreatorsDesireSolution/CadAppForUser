package com.example.cadappforuser.model;

public class Ad_Companymodel {

    String id;
    String  image;
    String name;
    float rating;

    public Ad_Companymodel(String  id,String  image, String name, float rating) {
        this.id=id;
        this.image = image;
        this.name = name;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
