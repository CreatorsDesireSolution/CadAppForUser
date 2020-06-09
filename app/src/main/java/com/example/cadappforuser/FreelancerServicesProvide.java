package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FreelancerServicesProvide extends AppCompatActivity {

    CheckBox checkSetAvailability;
    TextView txtSetAvailability;
    CheckBox checkKm,checkAtMyPlace;
    EditText etKm,etAtMyPlace;
    Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_services_provide);

        checkSetAvailability=findViewById(R.id.checkSetAvalibilty);
        txtSetAvailability=findViewById(R.id.txtSetAvalibilty);
        checkKm=findViewById(R.id.checkKm);
        etKm=findViewById(R.id.etKm);
        checkAtMyPlace=findViewById(R.id.checkAtMyPlace);
        etAtMyPlace=findViewById(R.id.etMyPlace);
        btnNext=findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerServicesProvide.this,FreelancerServicesOffered.class);
                startActivity(intent);
            }
        });

        checkAtMyPlace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
             if(b){
                 etAtMyPlace.setVisibility(View.VISIBLE);
             }
             else{
                 etAtMyPlace.setVisibility(View.GONE);
             }
            }
        });


        checkKm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    etKm.setVisibility(View.VISIBLE);
                }
                else {
                    etKm.setVisibility(View.GONE);
                }
            }
        });

       /* txtSetAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FreelancerServicesProvide.this, FreelancerSetAvalibiltyCustomActivity.class));
            }
        });*/

        checkSetAvailability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    startActivity(new Intent(FreelancerServicesProvide.this, FreelancerSetAvalibiltyCustomActivity.class));
                }
                else {

                }
            }
        });
    }
}
