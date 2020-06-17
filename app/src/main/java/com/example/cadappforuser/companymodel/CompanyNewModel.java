package com.example.cadappforuser.companymodel;

public class CompanyNewModel {

    int image;
    String name;
    float rating;

    public CompanyNewModel(int image, String name, float rating) {
        this.image = image;
        this.name = name;
        this.rating = rating;
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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

    public void setRating(Float rating) {
        this.rating = rating;
    }
}


