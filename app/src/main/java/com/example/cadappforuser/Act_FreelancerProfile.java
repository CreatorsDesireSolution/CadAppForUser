package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Act_FreelancerProfile extends AppCompatActivity {

    Button btn_serviceslist,btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__freelancer_profile);

        btn_serviceslist = findViewById(R.id.serviceslist);
        btn_next = findViewById(R.id.btn_next);

        btn_serviceslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesListActivity.class);
                startActivity(intent);
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesListActivity.class);
                startActivity(intent);
            }
        });




    }
}
