package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChoiceIndividualOrFreelancerOrComapnySignUp extends AppCompatActivity {

    Button btnIndividualFreelancer,btnCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_individual_or_freelancer_or_comapny_sign_up);

        btnCompany=findViewById(R.id.btnCompany);
        btnIndividualFreelancer=findViewById(R.id.btnAsIndividualFreelancer);


        btnIndividualFreelancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoiceIndividualOrFreelancerOrComapnySignUp.this,FreelancerSelectGenderActivity.class));
            }
        });

        btnCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoiceIndividualOrFreelancerOrComapnySignUp.this,CompanySelectGenderActivity.class));
            }
        });
    }
}
