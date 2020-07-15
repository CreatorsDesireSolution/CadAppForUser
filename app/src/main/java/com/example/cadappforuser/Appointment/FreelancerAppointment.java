package com.example.cadappforuser.Appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.R;
import com.example.cadappforuser.retrofit.BaseRequest;

public class FreelancerAppointment extends AppCompatActivity {

    RecyclerView recyclefree;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_appointment);

        recyclefree = findViewById(R.id.recyclefree);
        act_session = new Act_Session(getApplicationContext());
        context = this;
        activity = this;

    }
}
