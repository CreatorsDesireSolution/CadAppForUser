package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cadappforuser.model.ProfilesDetailModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Act_CustomerProfileEdit extends AppCompatActivity {

    ImageButton iv_edit;
    Act_Session act_session;
    Context context;
    BaseRequest baseRequest;
    ArrayList<ProfilesDetailModel> profile_list1 = new ArrayList<>();

    TextView tv_name,tv_mobile,tv_adresss,tv_gender,tv_email,tv_dob;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__customer_profile_edit);

      //  context = this;

        iv_edit = findViewById(R.id.iv_edit);
        act_session = new Act_Session(getApplicationContext());
        tv_name = findViewById(R.id.tv_name);
        tv_adresss = findViewById(R.id.tv_addresss);
        tv_dob = findViewById(R.id.tv_dob);
        tv_email = findViewById(R.id.tv_email);
        tv_gender = findViewById(R.id.tv_gender);
        tv_mobile = findViewById(R.id.tv_number);



        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // ApiGetRequest();
                Intent intent = new Intent(getApplicationContext(),Act_EditCustomerInformation.class);
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
                    profile_list1 = baseRequest.getDataList(jsonArray, ProfilesDetailModel.class);

                    if (profile_list1.size() != 0) {

                        tv_name.setText(profile_list1.get(0).getFirstname());
                       // tv.setText(profile_list1.get(0).getLastname());
                        tv_email.setText(profile_list1.get(0).getEmail());
                        tv_gender.setText(profile_list1.get(0).getGender());
                        tv_mobile.setText(profile_list1.get(0).getMobilenumber());
                        tv_adresss.setText(profile_list1.get(0).getAddress());
                        tv_dob.setText(profile_list1.get(0).getDob());


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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/customer_dataedit_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }
}
