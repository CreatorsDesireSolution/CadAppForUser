package com.example.cadappforuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.model.FreelancerProfileDetailsModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FreelancerPersonalProfileActivity extends AppCompatActivity {

    EditText userMobile,userFirstName,userLastName,userGender,userLocation,userBackground;
    ImageView userImage,userEditor,iv_profile_bck;
    TextView userEmail,userRating,tv_profile_heading;
    Button btnSubmit;
    BaseRequest baseRequest;
    Act_Session act_session;
    ArrayList<FreelancerProfileDetailsModel> profile_list1 = new ArrayList<>();
    Context context;
    String firstname,lastname,background,email,mobile,gender,address;
    ImageView workperformed,btn_certificate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_personal_profile2);
        act_session = new Act_Session(getApplicationContext());
        userEmail=findViewById(R.id.userEmail);
        userMobile=findViewById(R.id.userMobileNo);
        userFirstName=findViewById(R.id.userFirstName);
        userGender=findViewById(R.id.userGender);
        userLastName=findViewById(R.id.userLastName);
        userRating=findViewById(R.id.userRating);
        userLocation=findViewById(R.id.userLocation);
        userBackground=findViewById(R.id.userBackground);
        userEditor=findViewById(R.id.editProfile);
        iv_profile_bck=findViewById(R.id.iv_profile_bck);
        tv_profile_heading=findViewById(R.id.tv_profile_heading);
        workperformed = findViewById(R.id.menCircleImageView);
        btn_certificate = findViewById(R.id.womenCircleImageView);

        context=this;
        btnSubmit=findViewById(R.id.btnSumbit);

        userEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFirstName.setEnabled(true);
                userBackground.setEnabled(true);
                userLastName.setEnabled(true);
                userMobile.setEnabled(true);
                btnSubmit.setVisibility(View.VISIBLE);
                userEditor.setVisibility(View.GONE);
                tv_profile_heading.setText("Update Profile");

            }
        });


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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFirstName.setEnabled(false);
                userBackground.setEnabled(false);
                userLastName.setEnabled(false);
                userMobile.setEnabled(false);
                btnSubmit.setVisibility(View.GONE);
                userEditor.setVisibility(View.VISIBLE);
                tv_profile_heading.setText("Edit Profile");


                firstname = userFirstName.getText().toString();
                lastname = userLastName.getText().toString();
                mobile = userMobile.getText().toString();
                email = userEmail.getText().toString();
                address = userLocation.getText().toString();
                gender = userGender.getText().toString();
                ApiPost();
            }
        });

        iv_profile_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

                        userFirstName.setText(profile_list1.get(0).getFirstname());
                        userLastName.setText(profile_list1.get(0).getLastname());
                        userEmail.setText(profile_list1.get(0).getEmail());
                        userGender.setText(profile_list1.get(0).getGender());
                        userMobile.setText(profile_list1.get(0).getMobilenumber());
                        userLocation.setText(profile_list1.get(0).getAddress());
                        userBackground.setText(profile_list1.get(0).getBackground());
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

    private void ApiPost() {
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFirstName.setEnabled(false);
                userBackground.setEnabled(false);
                userLastName.setEnabled(false);
                userMobile.setEnabled(false);
            }
        });



    }

}