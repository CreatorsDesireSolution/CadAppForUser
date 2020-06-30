package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cadappforuser.model.ProfileDetailsModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Act_EditCustomerInformation extends AppCompatActivity {

    int year1 = Calendar.getInstance().get(Calendar.YEAR);
    String date;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    TextView tv_date_from, tv_date_to, tv_dob;
    EditText tv_address;
    Context context;
    BaseRequest baseRequest;
    EditText et_firstname,et_lastname,et_gender,et_email,et_mobile;
    Act_Session act_session;
    ArrayList<ProfileDetailsModel> profile_list1 = new ArrayList<>();
    String firstname,lastname,mobile,gender,email;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__edit_customer_information);

       act_session = new Act_Session(context);
       context = this;
        tv_dob = findViewById(R.id.tv_dob);
        tv_address = findViewById(R.id.tv_address);
        et_firstname = findViewById(R.id.et_firstname);
        et_lastname = findViewById(R.id.et_lastname);
        et_gender = findViewById(R.id.et_gender);
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.et_mobile);
        btn_save = findViewById(R.id.btn_save);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = et_firstname.getText().toString();

            }
        });



        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CurrentLocation.class);
                startActivity(intent);
            }
        });

        Intent intent2=getIntent();
        tv_address.setText(intent2.getStringExtra("address"));
       // txtGender.setText(intent2.getStringExtra("gender"));

        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Act_EditCustomerInformation.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tv_dob.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
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
                    profile_list1 = baseRequest.getDataList(jsonArray, ProfileDetailsModel.class);

                    if (profile_list1.size() != 0) {


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
        String remainingUrl2 = "" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }

}
