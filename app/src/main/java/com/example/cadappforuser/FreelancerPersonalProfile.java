package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cadappforuser.model.FreelancerProfileDetailsModel;
import com.example.cadappforuser.model.ProfilesDetailModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FreelancerPersonalProfile extends AppCompatActivity {

    TextView tv_edit;
    BaseRequest baseRequest;
    Act_Session act_session;
    ArrayList<FreelancerProfileDetailsModel> profile_list1 = new ArrayList<>();
    TextView tv_name,tv_mobile,tv_adresss,tv_gender,tv_email,tv_dob,tv_surname,background;
      Button btnSeeList,btn_certificate,workperformed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_personal_profile);


        act_session = new Act_Session(getApplicationContext());
        tv_edit = findViewById(R.id.tv_edit);
        tv_name = findViewById(R.id.name1);
        tv_surname = findViewById(R.id.lastname1);
        tv_email = findViewById(R.id.email1);
        tv_mobile = findViewById(R.id.mobile1);
        tv_gender = findViewById(R.id.gender1);
        tv_adresss = findViewById(R.id.Location1);
        background= findViewById(R.id.background111);
        btn_certificate= findViewById(R.id.btn_certificate);
        workperformed= findViewById(R.id.seeworkperformed);

        btnSeeList=findViewById(R.id.btnSeeList);


        workperformed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FreelancerWorkPerformed.class);
                startActivity(intent);
            }
        });

        btn_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FreelancerGetCertificate.class);
                startActivity(intent);
            }
        });

        btnSeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerPersonalProfile.this,FreelancerGenderSelectSeeList.class);
                startActivity(intent);
            }
        });

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Act_FreelancerPersonalEditProfile.class);
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
                    profile_list1 = baseRequest.getDataList(jsonArray, FreelancerProfileDetailsModel.class);

                    if (profile_list1.size() != 0) {

                        tv_name.setText(profile_list1.get(0).getFirstname());
                        tv_surname.setText(profile_list1.get(0).getLastname());
                        tv_email.setText(profile_list1.get(0).getEmail());
                        tv_gender.setText(profile_list1.get(0).getGender());
                        tv_mobile.setText(profile_list1.get(0).getMobilenumber());
                        tv_adresss.setText(profile_list1.get(0).getAddress());
                        background.setText(profile_list1.get(0).getBackground());
                       // tv_surname.setText(profile_list1.get(0).getLastname());


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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/freelancer_dataedit_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }
}


