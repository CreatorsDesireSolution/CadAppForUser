package com.example.cadappforuser.companymodel;

public class CompanyServicesFeatureAndCategoriesHomeModel {

        int image;
        String servicePrice,serviceName,sample;

        public CompanyServicesFeatureAndCategoriesHomeModel(int image, String servicePrice, String serviceName, String sample) {
            this.image = image;
            this.servicePrice = servicePrice;
            this.serviceName = serviceName;
            this.sample = sample;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(String servicePrice) {
            this.servicePrice = servicePrice;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getSample() {
            return sample;
        }

        public void setSample(String sample) {
            this.sample = sample;
        }


}
