package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cadappforuser.model.CompanyProfileDataModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompanyPersonalProfile extends AppCompatActivity {

    TextView tv_edit;
    BaseRequest baseRequest;
    Act_Session act_session;
    TextView viewcertificate;
    ArrayList<CompanyProfileDataModel> companyProfileDataModels = new ArrayList<>();
    TextView tv_mobile,tv_companyname,tv_address,tv_nostaff,tv_age,tv_email,tv_background,tv_name,tv_regnumbr,ViewWorkperform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_personal_profile);

        tv_address = findViewById(R.id.tv_location);
        tv_name = findViewById(R.id.tv_companyname);
        viewcertificate = findViewById(R.id.viewcertificate);

        tv_age = findViewById(R.id.tv_ageofcompany);
        tv_nostaff = findViewById(R.id.tv_no_of_staff);
        tv_mobile = findViewById(R.id.tv_mobile);
        tv_email = findViewById(R.id.tv_email);
        tv_background = findViewById(R.id.tv_background);
        tv_companyname = findViewById(R.id.tv_companyname);
        tv_regnumbr= findViewById(R.id.tv_regnumbr);
        ViewWorkperform= findViewById(R.id.ViewWorkperform);

        act_session = new Act_Session(getApplicationContext());

        tv_edit = findViewById(R.id.tv_edit);

        ViewWorkperform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CompanyGetWorkPerformed.class);
                startActivity(intent);
            }
        });


        viewcertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CompanyGetAllCertificate.class);
                startActivity(intent);
            }
        });

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Act_CompanyPersonalProfileEdit.class);
                startActivity(intent);
            }
        });
        Apigetprofile1();

    }

    private void Apigetprofile1() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");
                    companyProfileDataModels = baseRequest.getDataList(jsonArray, CompanyProfileDataModel.class);

                    if (companyProfileDataModels.size() != 0) {

                        tv_name.setText(companyProfileDataModels.get(0).getCompanyname());
                        tv_mobile.setText(companyProfileDataModels.get(0).getMobilenumber());
                        tv_email.setText(companyProfileDataModels.get(0).getEmail());
                        tv_address.setText(companyProfileDataModels.get(0).getAddress());
                        tv_background.setText(companyProfileDataModels.get(0).getAboutcompany());
                        tv_regnumbr.setText(companyProfileDataModels.get(0).getRegnumber());
                        tv_nostaff.setText(companyProfileDataModels.get(0).getNoOfStaff());
                       // tv_age .setText(companyProfileDataModels.get(0).ge);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {

            }

            @Override
            public void onNetworkFailure(int requestCode, String message) {

            }
        });
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/company_dataedit_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }
}
