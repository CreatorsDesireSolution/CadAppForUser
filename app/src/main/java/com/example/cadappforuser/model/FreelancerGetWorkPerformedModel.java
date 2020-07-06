
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreelancerGetWorkPerformedModel {

    @SerializedName("freelancer_id")
    @Expose
    private String freelancerId;
    @SerializedName("pic_work_performed")
    @Expose
    private String picWorkPerformed;

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getPicWorkPerformed() {
        return picWorkPerformed;
    }

    public void setPicWorkPerformed(String picWorkPerformed) {
        this.picWorkPerformed = picWorkPerformed;
    }

}
