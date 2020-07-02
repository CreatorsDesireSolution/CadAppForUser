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

import com.example.cadappforuser.model.CompanyProfileDataModel;
import com.example.cadappforuser.model.FreelancerProfileDetailsModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Act_CompanyPersonalProfileEdit extends AppCompatActivity {
    TextView tv_seemore;
    LinearLayout lay_workperform;
    boolean isOpen = false;
    TextView tv_workperform;
    EditText et_name,et_mobile,et_email,et_staff,et_regnumber,et_aboutcompany;
    TextView et_location;
    String address;
    BaseRequest baseRequest;
    Act_Session act_session;
    String companyname,mobile,email,nostaff,registernumber,aboutcompany,editaddress;
    Button btn_save;
    ArrayList<CompanyProfileDataModel> companyProfileDataModels = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__company_personal_profile_edit);

        act_session = new Act_Session(getApplicationContext());
        context = this;


        et_email = findViewById(R.id.et_email);
        et_location = findViewById(R.id.tv_address);
        et_staff = findViewById(R.id.tv_no_of_staff);
        et_mobile = findViewById(R.id.et_mobile);
        et_name = findViewById(R.id.et_firstname);
        btn_save= findViewById(R.id.btn_save);
        et_regnumber = findViewById(R.id.et_regnumber);
        et_aboutcompany = findViewById(R.id.et_aboutcompany);


        et_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CurrentLocation3.class);
                startActivity(intent);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mobile = et_mobile.getText().toString();
            editaddress = et_location.getText().toString();
            email = et_email.getText().toString();
            registernumber = et_regnumber.getText().toString();
            aboutcompany = et_aboutcompany.getText().toString();
            nostaff = et_staff.getText().toString();
            companyname = et_name.getText().toString();
            ApiPost();


            }
        });


        Intent intent2=getIntent();
        address = intent2.getStringExtra("address");
        et_location.setText(address);


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


                        et_name.setText(companyProfileDataModels.get(0).getCompanyname());
                        et_mobile.setText(companyProfileDataModels.get(0).getMobilenumber());
                        et_email.setText(companyProfileDataModels.get(0).getEmail());
                        et_location.setText(companyProfileDataModels.get(0).getAddress());
                        et_aboutcompany.setText(companyProfileDataModels.get(0).getAboutcompany());
                        et_regnumber.setText(companyProfileDataModels.get(0).getRegnumber());
                        et_staff.setText(companyProfileDataModels.get(0).getNoOfStaff());


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
                    Intent intent = new Intent(getApplicationContext(),CompanyHomePageActivity.class);
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
        RequestBody companyname_ = RequestBody.create(MediaType.parse("text/plain"),companyname );
        RequestBody background_ = RequestBody.create(MediaType.parse("text/plain"),aboutcompany );
        RequestBody staff = RequestBody.create(MediaType.parse("text/plain"), nostaff);
        RequestBody register_no = RequestBody.create(MediaType.parse("text/plain"), registernumber);
        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody  address_= RequestBody.create(MediaType.parse("text/plain"), editaddress);
        RequestBody  email_= RequestBody.create(MediaType.parse("text/plain"), email);





        baseRequest.CallUpdateprofileComapay(1,"https://aoneservice.net.in/" , companyname_, background_,
                staff,register_no,mobilenumber_,address_,email_);

    }




}
