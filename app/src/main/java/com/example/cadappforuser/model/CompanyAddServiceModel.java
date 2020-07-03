
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyAddServiceModel {

    @SerializedName("service_id")
    @Expose
    private String id;
    @SerializedName("companyid")
    @Expose
    private String companyid;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("set_price")
    @Expose
    private String setPrice;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("service_gender")
    @Expose
    private String serviceGender;
    @SerializedName("service_image")
    @Expose
    private String serviceImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSetPrice() {
        return setPrice;
    }

    public void setSetPrice(String setPrice) {
        this.setPrice = setPrice;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getServiceGender() {
        return serviceGender;
    }

    public void setServiceGender(String serviceGender) {
        this.serviceGender = serviceGender;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

}
