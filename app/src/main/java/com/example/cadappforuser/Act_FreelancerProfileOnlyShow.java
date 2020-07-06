package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Act_FreelancerProfileOnlyShow extends AppCompatActivity {

    FrameLayout lay1;
    TextView background11;
    boolean isOpen = false;
    LinearLayout tvbg;
    String id;
    RelativeLayout lay_certificate,workperform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancernewprofileonltshow);
        lay_certificate = findViewById(R.id.lay_certificate);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Profile");

        //btn_serviceslist = findViewById(R.id.serviceslist);

        lay1 = findViewById(R.id.lay1);
        tvbg= findViewById(R.id.tvbg);
        background11 = findViewById(R.id.background11);
        workperform= findViewById(R.id.workperform);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FreelancerGenderSelectSeeList.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        lay_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FreelancerGetCertificateForShow.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        workperform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FreelancerWorkPerformShow.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });


        background11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    isOpen = false;
                    tvbg.setVisibility(View.GONE);
                    background11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                } else {
                    isOpen = true;
                    tvbg.setVisibility(View.VISIBLE);
                    background11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);


                }
            }
        });






    }
}
