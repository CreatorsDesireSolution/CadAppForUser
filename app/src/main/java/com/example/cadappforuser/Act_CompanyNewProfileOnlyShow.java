package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Act_CompanyNewProfileOnlyShow extends AppCompatActivity {
    Button btn_serviceslist,btn_next;
    FrameLayout lay1;
    RelativeLayout lay_certificate,workperform;


String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companyprofileeditshow);
        lay_certificate = findViewById(R.id.lay_certificate);
        workperform= findViewById(R.id.workperform);

      //  btn_serviceslist = findViewById(R.id.serviceslist);
       
        lay1 = findViewById(R.id.lay1);

        final Intent intent=getIntent();
        id=intent.getStringExtra("id");


        workperform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),CompanyGetWorkPerformforShow.class);
                intent1.putExtra("id",id);
                startActivity(intent1);


            }
        });


        lay_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),CompanyGetCertificateForShow.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
            }
        });

        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Act_SelectCompnayGenderForListFreelancer.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });





    }
}
