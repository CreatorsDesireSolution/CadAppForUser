package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Act_FreelancerProfile extends AppCompatActivity {

    Button btn_serviceslist,btn_next;
    FrameLayout lay1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancernewprofile);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Profile");

        //btn_serviceslist = findViewById(R.id.serviceslist);
        btn_next = findViewById(R.id.btn_next);
        lay1 = findViewById(R.id.lay1);

        lay1.setOnClickListener(new View.OnClickListener() {
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
