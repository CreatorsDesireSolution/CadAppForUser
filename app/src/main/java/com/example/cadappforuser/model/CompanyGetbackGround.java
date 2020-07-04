
package com.example.cadappforuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyGetbackGround {

    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("about_your_company")
    @Expose
    private String aboutYourCompany;
    @SerializedName("total_year_establishment")
    @Expose
    private String totalYearEstablishment;
    @SerializedName("team_size")
    @Expose
    private String teamSize;
    @SerializedName("no_of_men")
    @Expose
    private String noOfMen;
    @SerializedName("no_of_womenr")
    @Expose
    private String noOfWomenr;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAboutYourCompany() {
        return aboutYourCompany;
    }

    public void setAboutYourCompany(String aboutYourCompany) {
        this.aboutYourCompany = aboutYourCompany;
    }

    public String getTotalYearEstablishment() {
        return totalYearEstablishment;
    }

    public void setTotalYearEstablishment(String totalYearEstablishment) {
        this.totalYearEstablishment = totalYearEstablishment;
    }

    public String getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(String teamSize) {
        this.teamSize = teamSize;
    }

    public String getNoOfMen() {
        return noOfMen;
    }

    public void setNoOfMen(String noOfMen) {
        this.noOfMen = noOfMen;
    }

    public String getNoOfWomenr() {
        return noOfWomenr;
    }

    public void setNoOfWomenr(String noOfWomenr) {
        this.noOfWomenr = noOfWomenr;
    }

}
