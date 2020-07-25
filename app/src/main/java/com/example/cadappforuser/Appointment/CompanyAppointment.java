package com.example.cadappforuser.Appointment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.CompanyAppotiment;
import com.example.cadappforuser.R;
import com.example.cadappforuser.adapter.FreelancerAppointmentAdapter;
import com.example.cadappforuser.model.FreelancerOrderModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompanyAppointment extends AppCompatActivity {
    RecyclerView recycle_company;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    Activity activity;
    FreelancerAppointmentAdapter freelancerAppointmentAdapter;
    ArrayList<FreelancerOrderModel> freelancerOrderModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_appointment);


        recycle_company = findViewById(R.id.recycle_company);
        act_session = new Act_Session(getApplicationContext());
        context = this;
        activity = this;

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Appointment");
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CompanyAppointment.this);
//        //      linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        recycle_company.setLayoutManager(linearLayoutManager);
//        recycle_company.setHasFixedSize(true);


        Apigetorder();

    }

    private void Apigetorder() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");

                    freelancerOrderModels = baseRequest.getDataList(jsonArray, FreelancerOrderModel.class);

                    if (freelancerOrderModels.size() != 0) {
                        freelancerAppointmentAdapter =new FreelancerAppointmentAdapter(context, freelancerOrderModels);
                        LinearLayoutManager layoutManager= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        recycle_company.setLayoutManager(layoutManager);

                        recycle_company.setHasFixedSize(true);
                        recycle_company.setAdapter(freelancerAppointmentAdapter);
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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/customer_bookedservice_api.php?" + "id=" + act_session.userId  + "&&flag=" + act_session.flag;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }

}
