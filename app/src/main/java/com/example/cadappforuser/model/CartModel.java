package com.example.cadappforuser.model;

public class CartModel  {
    String serImage,userImage;
    String serName,serPrice,serSample,PId,status;
    int serId,qty;

    public CartModel(String serImage, String serName, String serPrice, String serSample,int serId,int qty,String PId,String status,String userImage) {
        this.serImage = serImage;
        this.serName = serName;
        this.serPrice = serPrice;
        this.serSample = serSample;
        this.serId=serId;
        this.qty=qty;
        this.PId=PId;
        this.status=status;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSerId() {
        return serId;
    }

    public void setSerId(int serId) {
        this.serId = serId;
    }

    public String getSerImage() {
        return serImage;
    }

    public void setSerImage(String serImage) {
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
