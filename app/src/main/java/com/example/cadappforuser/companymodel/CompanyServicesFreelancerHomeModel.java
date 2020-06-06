package com.example.cadappforuser.companymodel;

public class CompanyServicesFreelancerHomeModel {
    int imageFreelancer;
    String servicePriceFreelancer,serviceNameFreelancer,sampleFreelancer;

    public CompanyServicesFreelancerHomeModel(int imageFreelancer, String servicePriceFreelancer, String serviceNameFreelancer, String sampleFreelancer) {
        this.imageFreelancer = imageFreelancer;
        this.servicePriceFreelancer = servicePriceFreelancer;
        this.serviceNameFreelancer = serviceNameFreelancer;
        this.sampleFreelancer = sampleFreelancer;
    }

    public int getImageFreelancer() {
        return imageFreelancer;
    }

    public void setImageFreelancer(int imageFreelancer) {
        this.imageFreelancer = imageFreelancer;
    }

    public String getServicePriceFreelancer() {
        return servicePriceFreelancer;
    }

    public void setServicePriceFreelancer(String servicePriceFreelancer) {
        this.servicePriceFreelancer = servicePriceFreelancer;
    }

    public String getServiceNameFreelancer() {
        return serviceNameFreelancer;
    }

    public void setServiceNameFreelancer(String serviceNameFreelancer) {
        this.serviceNameFreelancer = serviceNameFreelancer;
    }

    public String getSampleFreelancer() {
        return sampleFreelancer;
    }

    public void setSampleFreelancer(String sampleFreelancer) {
        this.sampleFreelancer = sampleFreelancer;
    }
}
