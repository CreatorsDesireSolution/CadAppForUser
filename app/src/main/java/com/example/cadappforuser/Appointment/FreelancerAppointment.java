package com.example.cadappforuser.Appointment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.R;
import com.example.cadappforuser.SeeAll.SeeAllCompany;
import com.example.cadappforuser.adapter.FreelancerAppointmentAdapter;
import com.example.cadappforuser.adapter.SeeAllCompanyAdapter;
import com.example.cadappforuser.model.FreelancerOrderModel;
import com.example.cadappforuser.model.FreelancerProfileDetailsModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FreelancerAppointment extends AppCompatActivity {

    RecyclerView recyclefree;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    Activity activity;
    FreelancerAppointmentAdapter freelancerAppointmentAdapter;
    ArrayList<FreelancerOrderModel> freelancerOrderModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_appointment);

        recyclefree = findViewById(R.id.recycleorderr);
        act_session = new Act_Session(getApplicationContext());
        context = this;
        activity = this;

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Appointment");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FreelancerAppointment.this);
        //      linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclefree.setLayoutManager(linearLayoutManager);
        recyclefree.setHasFixedSize(true);


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
//                                LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
//                                recyclerView1.setLayoutManager(layoutManager1);
                        LinearLayoutManager layoutManager= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        recyclefree.setLayoutManager(layoutManager);

                        recyclefree.setHasFixedSize(true);
                        recyclefree.setAdapter(freelancerAppointmentAdapter);


                        // imageUserLogo.setImageURI(fileget);

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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/customer_bookedservice_api.php?" + "id=" + act_session.userId  + "&&flag=" + act_session.flag;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }

}
