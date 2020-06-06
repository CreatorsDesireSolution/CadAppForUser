package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StaffProfile extends AppCompatActivity {

    TextView textStaffName,textStaffEmail,textStaffMobile;
    TextView addServices,addCertification,addBackground;
    ImageView imgCertification,imgBackground,imgServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_profile);

        textStaffName=findViewById(R.id.textStaffName);
        textStaffEmail=findViewById(R.id.textStaffEmail);
        textStaffMobile=findViewById(R.id.textStaffMobileNumber);

        addBackground=findViewById(R.id.addBackGround);
        addCertification=findViewById(R.id.addCertification);
        addServices=findViewById(R.id.addServices);

        imgCertification=findViewById(R.id.imgCertifivcation);
        imgServices=findViewById(R.id.imgServices);
        imgBackground=findViewById(R.id.imgBackground);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String mobile=intent.getStringExtra("mobile");
        String email=intent.getStringExtra("email");

        textStaffName.setText(name);
        textStaffEmail.setText(email);
        textStaffMobile.setText(mobile);

        addServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(StaffProfile.this,StaffAddServicesActivity.class));
            }
        });

        addCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(StaffProfile.this,BackgoundOfStaffActivity.class));
            }
        });

        addBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  startActivity(new Intent(StaffProfile.this,StaffCertificationActivity.class));
            }
        });

        imgBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffProfile.this,BackgoundOfStaffActivity.class));
            }
        });

        imgServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffProfile.this,StaffAddServicesActivity.class));
            }
        });

        imgCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffProfile.this,StaffCertificationActivity.class));
            }
        });



    }
}
