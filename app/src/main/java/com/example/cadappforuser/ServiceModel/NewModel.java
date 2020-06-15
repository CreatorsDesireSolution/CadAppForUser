package com.example.cadappforuser.ServiceModel;

import android.content.Context;

import java.util.List;

public class NewModel {

    int image;
    String name,rating;

    public NewModel(int image, String name, String rating) {
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
