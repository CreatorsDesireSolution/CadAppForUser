package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadappforuser.model.FreelancerProfileDetailsModel;
import com.example.cadappforuser.model.ProfilesDetailModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Act_FreelancerPersonalEditProfile extends AppCompatActivity {
    TextView tv_workperform;
    LinearLayout lay_workperform;
    boolean isOpen = false;
    TextView tv_seemore;
    TextView tv_address;
    Context context;
    BaseRequest baseRequest;
    EditText et_firstname,et_lastname,et_gender,et_email,et_mobile;
    Act_Session act_session;

    String firstname,lastname,mobile,gender,email,address,DOB;
    Button btn_save;
    ArrayList<FreelancerProfileDetailsModel> profile_list1 = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__freelancer_personal_edit_profile);


        tv_address = findViewById(R.id.tv_address);
        et_firstname = findViewById(R.id.et_firstname);
        et_lastname = findViewById(R.id.et_lastname);
        et_gender = findViewById(R.id.et_gender);
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.et_mobile);
        btn_save = findViewById(R.id.btn_save);

        act_session= new Act_Session(getApplicationContext());



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = et_firstname.getText().toString();
                lastname = et_lastname.getText().toString();
                mobile= et_mobile.getText().toString();
                gender = et_gender.getText().toString();

                address = tv_address.getText().toString();
                email= et_email.getText().toString();
                ApiPostInformation();

            }
        });


        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CurrentLocation2.class);
                startActivity(intent);
            }
        });


        Intent intent2=getIntent();
        address = intent2.getStringExtra("address");
        tv_address.setText(address);


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

                        et_firstname.setText(profile_list1.get(0).getFirstname());
                        et_lastname.setText(profile_list1.get(0).getLastname());
                        et_email.setText(profile_list1.get(0).getEmail());
                        et_gender.setText(profile_list1.get(0).getGender());
                        et_mobile.setText(profile_list1.get(0).getMobilenumber());
                        tv_address.setText(profile_list1.get(0).getAddress());


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
    private void ApiPostInformation() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                act_session.loginSession(context);
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                    act_session = new Act_Session(context, jsonObject1);

                    Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),FreelancerHomePageActivity.class);
                    // intent.putExtra("mobilenumber",mobilenumber);
                    startActivity(intent);


                    finish();




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onNetworkFailure(int requestCode, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        });
        RequestBody firstname_ = RequestBody.create(MediaType.parse("text/plain"),firstname );
        RequestBody lastname_ = RequestBody.create(MediaType.parse("text/plain"),lastname );
        RequestBody email_ = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody gender_ = RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody address_ = RequestBody.create(MediaType.parse("text/plain"), address);





        baseRequest.CallUpdateprofileFreelancer(1,"https://aoneservice.net.in/" , firstname_, lastname_,
                email_,mobilenumber_,gender_,address_);

    }


}
