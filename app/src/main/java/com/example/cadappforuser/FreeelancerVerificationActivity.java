package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FreeelancerVerificationActivity extends AppCompatActivity {

    Button btnVerify;
    EditText etOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        btnVerify=findViewById(R.id.btnVerify);
        etOtp = findViewById(R.id.etOtp);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(FreeelancerVerificationActivity.this,FreelancerHomePageActivity.class));
                startActivity(new Intent(FreeelancerVerificationActivity.this,FreelancerServicesProvide.class));
            }
        });
    }
}
