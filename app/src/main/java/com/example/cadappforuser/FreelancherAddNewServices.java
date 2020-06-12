package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FreelancherAddNewServices extends AppCompatActivity {

    Button btn_addservices,btnNextService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancher_add_new_services);

        btn_addservices = findViewById(R.id.btn_addservices);
        btnNextService = findViewById(R.id.btn_nextservice);


        btn_addservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FreelancerAddServicesActivity.class);
                startActivity(intent);
            }
        });

        btnNextService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FreelancerCertificationActivity.class);
                startActivity(intent);
            }
        });


    }
}
