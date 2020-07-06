
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyGetWorkPerformedModel {

    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("pic_work_performed")
    @Expose
    private String picWorkPerformed;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPicWorkPerformed() {
        return picWorkPerformed;
    }

    public void setPicWorkPerformed(String picWorkPerformed) {
        this.picWorkPerformed = picWorkPerformed;
    }

}
