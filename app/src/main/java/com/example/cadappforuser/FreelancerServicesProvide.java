package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FreelancerServicesProvide extends AppCompatActivity {

    CheckBox checkSetAvailability;
    TextView txtSetAvailability;
    CheckBox checkKm,checkAtMyPlace;
    EditText etKm;TextView etAtMyPlace;
    Button btnNext;
    String adress;
    RadioGroup radioGroup;
    RadioButton typeradioButton;
    String type = "";
    String days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_services_provide);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services Provided Area");


        checkKm=findViewById(R.id.checkKm);
        etKm=findViewById(R.id.etKm);
        checkAtMyPlace=findViewById(R.id.checkAtMyPlace);
        etAtMyPlace=findViewById(R.id.etMyPlace);
        btnNext=findViewById(R.id.btnNext);
        radioGroup = findViewById(R.id.radioGroup);

//        Intent intent1 = getIntent();
//        days = intent1.getStringExtra("days");



        etAtMyPlace.setVisibility(View.VISIBLE);
        etAtMyPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerServicesProvide.this,At_Place_Location.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        adress=intent.getStringExtra("address");
        etAtMyPlace.setText(adress);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerServicesProvide.this,FreelancerCertificationActivity.class);
                startActivity(intent);
            }
        });

        /*checkAtMyPlace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
             if(b){
                 etAtMyPlace.setVisibility(View.VISIBLE);
                 etAtMyPlace.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent=new Intent(FreelancerServicesProvide.this,At_Place_Location.class);
                         startActivity(intent);
                     }
                 });

                 etKm.setText(adress);
             }
             else{
                 etAtMyPlace.setVisibility(View.GONE);
             }
            }
        });*/


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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.radioalways) {
                    type = "always";
                } else {
                    type = "setavailability";
                    startActivity(new Intent(FreelancerServicesProvide.this, FreelancerSetAvalibiltyCustomActivity.class));

                }
            }

        });

       /* txtSetAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FreelancerServicesProvide.this, FreelancerSetAvalibiltyCustomActivity.class));
            }
        });*/

//        checkSetAvailability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    startActivity(new Intent(FreelancerServicesProvide.this, FreelancerSetAvalibiltyCustomActivity.class));
//                }
//                else {
//
//                }
//            }
//        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
