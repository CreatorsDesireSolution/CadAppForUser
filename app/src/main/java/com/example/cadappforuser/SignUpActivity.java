package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.FingerprintGestureController;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    Button btnAsCustomer,btnAsServiceProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        btnAsCustomer=findViewById(R.id.btnAsCustomer);
        btnAsServiceProvider=findViewById(R.id.btnAsServiceProvider);

        btnAsCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,SelectGenderActivity.class));

            }
        });
        btnAsServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,ChoiceIndividualOrFreelancerOrComapnySignUp.class));

            }
        });
    }

}
