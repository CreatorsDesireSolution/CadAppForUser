
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreelancerServiceListModel {

    @SerializedName("Service_id")
    @Expose
    private String serviceId;
    @SerializedName("freelancer_id")
    @Expose
    private String freelancerId;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
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
