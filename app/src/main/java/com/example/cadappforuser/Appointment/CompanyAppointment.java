package com.example.cadappforuser.Appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.R;
import com.example.cadappforuser.retrofit.BaseRequest;

public class CompanyAppointment extends AppCompatActivity {
    RecyclerView recycle_company;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_appointment);

        recycle_company = findViewById(R.id.recycle_company);
        act_session = new Act_Session(getApplicationContext());
        context = this;
        activity = this;
    }
}
