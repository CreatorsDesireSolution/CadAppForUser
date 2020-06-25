package com.example.cadappforuser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadappforuser.ShowDays.Act_ShowDayComapany;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FreelancerServicesProvide extends AppCompatActivity {

    CheckBox checkSetAvailability;
    String kilo;
    String check="0";
    TextView txtSetAvailability;
    CheckBox checkKm,checkAtMyPlace;
    EditText etKm;TextView etAtMyPlace;
    Button btnNext;
    String adress;
    RadioGroup radioGroup;
    RadioButton typeradioButton;
    String type = "";
    String days;
    CheckBox checkFlexible,checkStrict,checkModerate;
    String kilometer,atmyplace,starttime,endtime,selecteddays;
    BaseRequest baseRequest;
    Act_Session act_session;
    ProgressDialog progressDialog;
    String r;
    String address,chec;
    String  start="start",end="end";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_services_provide);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services Provided Area");

        act_session = new Act_Session(getApplicationContext());


        checkKm=findViewById(R.id.checkKm);
        etKm=findViewById(R.id.etKm);
        checkAtMyPlace=findViewById(R.id.checkAtMyPlace);
        etAtMyPlace=findViewById(R.id.etMyPlace);
        btnNext=findViewById(R.id.btnNext);
        radioGroup = findViewById(R.id.radioGroup);
        checkModerate= findViewById(R.id.checkModerate);
        checkFlexible = findViewById(R.id.checkFlexible);
        checkStrict = findViewById(R.id.checkStrict);

       Intent intent1 = getIntent();
       days = intent1.getStringExtra("days");
       starttime = intent1.getStringExtra("starttime");
       endtime = intent1.getStringExtra("endtime");
       chec=intent1.getStringExtra("check");
       address=intent1.getStringExtra("address");
        kilo=intent1.getStringExtra("atkm");
        etKm.setText(kilo);
       Log.d("time","time"+days+starttime+endtime);


        etAtMyPlace.setVisibility(View.VISIBLE);
        etAtMyPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerServicesProvide.this,At_Place_Location.class);
                startActivity(intent);
            }
        });


        kilometer=etKm.getText().toString();
        Log.d("kilome","kelo"+kilometer);
        final Intent intent=getIntent();
        adress=intent.getStringExtra("address");
        etAtMyPlace.setText(adress);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.radioalways) {
                    type = "always";
                    chec="0";

                } else {
                    chec="1";
                    Intent intent=new Intent(FreelancerServicesProvide.this,FreelancerSetAvalibiltyCustomActivity.class);
                    intent.putExtra("check",chec);
                    intent.putExtra("address",adress);
                    intent.putExtra("atkm",kilometer);
                    intent.putExtra("start",start);
                    intent.putExtra("end",end);

                    startActivity(intent);

                }
            }

        });


        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(FreelancerServicesProvide.this, ""+days+starttime+endtime+chec, Toast.LENGTH_SHORT).show();

                progressDialog=new ProgressDialog(FreelancerServicesProvide.this,R.style.MyAlertDialogStyle);
                progressDialog.setTitle("Grocery");
                progressDialog.setMessage("Please Wait......");

                progressDialog.show();
                 r = "";

                if (checkFlexible.isChecked()) {
                    r = r + "," + "flexible";
                }
                if (checkModerate.isChecked()) {
                    r = r + "," + "moderate";
                }
                if (checkStrict.isChecked()) {
                    r = r + "," + "strict";
                }

                Toast.makeText(FreelancerServicesProvide.this, ""+r, Toast.LENGTH_SHORT).show();

                kilometer = etKm.getText().toString();
                Toast.makeText(FreelancerServicesProvide.this, ""+kilometer, Toast.LENGTH_SHORT).show();
                atmyplace = etAtMyPlace.getText().toString();
          //      Act_session act_session = new Act_session(getApplicationContext);
                final Act_Session act_session=new Act_Session(getApplicationContext());
                String url="http://aoneservice.net.in/salon/freelancer_services_area.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonObject= null;
                        try {
                            jsonObject = new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");
                            if(status.equals("true") || message.equals("Success")){
                                progressDialog.dismiss();
                                Toast.makeText(FreelancerServicesProvide.this, ""+response, Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(FreelancerServicesProvide.this, ""+response, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(FreelancerServicesProvide.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String ,String > map=new HashMap<>();
                        Log.d("cancel","canecl"+r);

                        if(chec.equals("1"))
                        {
                            map.put("avalkm","100");
                            map.put("at_my_place",atmyplace);
                            Log.d("starttime","start"+starttime);
                            map.put("start_time",starttime);
                            map.put("end_time",endtime);
                            map.put("days",days);
                            map.put("cancel_policy",r);
                            map.put("id",act_session.userId);
                        }
                       else {
                            map.put("avalkm",kilometer);
                            map.put("at_my_place",atmyplace);
                            map.put("avalibility",type);
                            map.put("cancel_policy",r);
                            map.put("id",act_session.userId);
                        }

                        return map;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(FreelancerServicesProvide.this);
                requestQueue.add(stringRequest);
            }
        });

        /*checkAtMyPlace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
             if(b){
                 etAtMyPlace.setVisibility(View.VISIBLE);
                 etAtMyPlace.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent=new Intent(FreelancerServicesProvide.this,At_Place_Location.class);
                         startActivity(intent);
                     }
                 });

                 etKm.setText(adress);
             }
             else{
                 etAtMyPlace.setVisibility(View.GONE);
             }
            }
        });*/


        checkKm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    etKm.setVisibility(View.VISIBLE);
                }
                else {
                    etKm.setVisibility(View.GONE);
                }
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

  /*  private void ApiPOST() {
        baseRequest = new BaseRequest(getApplicationContext());
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                // act_session.loginSession(context);
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                    act_session = new Act_Session(getApplicationContext(), jsonObject1);

                    Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(RegisterAsFreelancerActivity.this, FreelancerMobileNumberRegisterActivity.class));
                    Intent intent=new Intent(FreelancerServicesProvide.this,FreelancerCertificationActivity.class);
                    startActivity(intent);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onNetworkFailure(int requestCode, String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            }
        });
        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"),act_session.userId );
//        RequestBody lastname_ = RequestBody.create(MediaType.parse("text/plain"),lastname );
//        RequestBody email_ = RequestBody.create(MediaType.parse("text/plain"), email);
//        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobilenumber);
//        RequestBody gender_ = RequestBody.create(MediaType.parse("text/plain"), gender1);
//        RequestBody address_ = RequestBody.create(MediaType.parse("text/plain"), address);
//        RequestBody deviceid_ = RequestBody.create(MediaType.parse("text/plain"), deviceId);
//        RequestBody password_ = RequestBody.create(MediaType.parse("text/plain"), password);
//
//
//        baseRequest.callApiRegisterfreelancer(1,"https://aoneservice.net.in/" , firstname_, lastname_, email_, mobilenumber_, gender_,address_,deviceid_,password_);

    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
