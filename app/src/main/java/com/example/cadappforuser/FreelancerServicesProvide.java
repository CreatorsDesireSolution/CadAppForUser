package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.cadappforuser.ShowDays.Act_ShowDayComapany;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FreelancerServicesProvide extends AppCompatActivity {

    CheckBox checkSetAvailability;
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



        etAtMyPlace.setVisibility(View.VISIBLE);
        etAtMyPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerServicesProvide.this,At_Place_Location.class);
                startActivity(intent);
            }
        });


        final Intent intent=getIntent();
        adress=intent.getStringExtra("address");
        etAtMyPlace.setText(adress);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = "";

                if (checkFlexible.isChecked()) {
                    r = r + "," + checkFlexible.getText();
                }
                if (checkModerate.isChecked()) {
                    r = r + "," + checkModerate.getText();
                }
                if (checkStrict.isChecked()) {
                    r = r + "," + checkStrict.getText();
                }

                kilometer = etKm.getText().toString();
                atmyplace = etAtMyPlace.getText().toString();

                if (kilometer.equals("")){

                }else if (atmyplace.equals("")){

                }else {
                    ApiPOST();
                }





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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.radioalways) {
                    type = "always";
                } else {
                    startActivity(new Intent(FreelancerServicesProvide.this, FreelancerSetAvalibiltyCustomActivity.class));

                }
            }

        });

       /* txtSetAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FreelancerServicesProvide.this, FreelancerSetAvalibiltyCustomActivity.class));
            }
        });*/

//        checkSetAvailability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    startActivity(new Intent(FreelancerServicesProvide.this, FreelancerSetAvalibiltyCustomActivity.class));
//                }
//                else {
//
//                }
//            }
//        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void ApiPOST() {
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
        RequestBody lastname_ = RequestBody.create(MediaType.parse("text/plain"),lastname );
        RequestBody email_ = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobilenumber);
        RequestBody gender_ = RequestBody.create(MediaType.parse("text/plain"), gender1);
        RequestBody address_ = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody deviceid_ = RequestBody.create(MediaType.parse("text/plain"), deviceId);
        RequestBody password_ = RequestBody.create(MediaType.parse("text/plain"), password);


        baseRequest.callApiRegisterfreelancer(1,"https://aoneservice.net.in/" , firstname_, lastname_, email_, mobilenumber_, gender_,address_,deviceid_,password_);

    }

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
