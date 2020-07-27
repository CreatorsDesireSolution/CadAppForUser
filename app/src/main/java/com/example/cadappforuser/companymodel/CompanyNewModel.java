package com.example.cadappforuser.companymodel;

public class CompanyNewModel {

    String image,userImage;
    String name;
    float rating;

    String email,mobile,lastname,address,experience,about_yourself,no_of_staff,id,km;

    public CompanyNewModel(String image, String name, float rating, String email, String mobile,
                           String lastname, String address,String experience,
                           String about_yourself,String no_of_staff,String id,String km,String userImage) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.email = email;
        this.mobile = mobile;
        this.lastname = lastname;
        this.address = address;
        this.experience= experience;
        this.about_yourself=about_yourself;
        this.no_of_staff=no_of_staff;
        this.id=id;
        this.km = km;
        this.userImage=userImage;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getNo_of_staff() {
        return no_of_staff;
    }

    public void setNo_of_staff(String no_of_staff) {
        this.no_of_staff = no_of_staff;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAbout_yourself() {
        return about_yourself;
    }

    public void setAbout_yourself(String about_yourself) {
        this.about_yourself = about_yourself;
    }
}
