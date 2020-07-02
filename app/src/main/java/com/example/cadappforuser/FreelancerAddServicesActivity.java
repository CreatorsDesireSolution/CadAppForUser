package com.example.cadappforuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FreelancerAddServicesActivity extends AppCompatActivity {
    Button btn_addservice;
    Spinner spinner;
    EditText et_setprice,et_servicedescription,et_servicename,et_duration;
    String setprice,setservicename,description,selectcategory,duration;
    BaseRequest baseRequest;
    Context context;
    Act_Session act_session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_add_services);

        context = this;
        act_session = new Act_Session(context);
        btn_addservice = findViewById(R.id.btn_add_service);
        et_duration = findViewById(R.id.et_duration);
        et_servicedescription = findViewById(R.id.et_servicedescription);
        et_servicename = findViewById(R.id.etSName);
        et_setprice = findViewById(R.id.etSetPrice);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Add Services");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        List<String> list=new ArrayList<>();
         list.add("Select Category");
        list.add("RICA Waxing");
        list.add("Honey Waxing");
        list.add("Hair Color & Care");
        list.add("Threading");
        list.add("Waxing");
        list.add("Facial, Cleanup & Detan");
        list.add("Massage");
        list.add("Pedicure & Manicure");

         btn_addservice.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 setprice = et_setprice.getText().toString();
                 description = et_servicedescription.getText().toString();
                 setservicename = et_servicename.getText().toString();
                 duration = et_duration.getText().toString();

                 if (setprice.equals("")){

                     Toast.makeText(FreelancerAddServicesActivity.this, "Please set the price", Toast.LENGTH_SHORT).show();
                 }else  if (description.equals("")){
                     Toast.makeText(FreelancerAddServicesActivity.this, "Please add description of service", Toast.LENGTH_SHORT).show();

                 }else  if (setservicename.equals("")){
                     Toast.makeText(FreelancerAddServicesActivity.this, "Please set service name", Toast.LENGTH_SHORT).show();

                 }else if (duration.equals("")){
                     Toast.makeText(FreelancerAddServicesActivity.this, "Please enter duration", Toast.LENGTH_SHORT).show();

                 }else {

                 ApiAddService();

                 }


             }
         });


    }

    private void ApiAddService() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                // act_session.loginSession(context);
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                    Toast.makeText(getApplicationContext(), "Add Successfully", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(RegisterAsFreelancerActivity.this, FreelancerMobileNumberRegisterActivity.class));
                    Intent intent = new Intent(getApplicationContext(),FreelancerHomePageActivity.class);
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
        RequestBody userid_ = RequestBody.create(MediaType.parse("text/plain"),act_session.userId );
        RequestBody service_name_ = RequestBody.create(MediaType.parse("text/plain"),setservicename );
        RequestBody description_ = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody set_price_ = RequestBody.create(MediaType.parse("text/plain"), setprice);
        RequestBody duration_ = RequestBody.create(MediaType.parse("text/plain"), duration);

        baseRequest.callApiAddservicefreelancer(1,"https://aoneservice.net.in/" , userid_, service_name_, description_, set_price_, duration_);

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

