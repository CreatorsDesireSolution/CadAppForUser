package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class FreelancerMobileNumberRegisterActivity extends AppCompatActivity {

    Button btnGetCode;
    CountryCodePicker ccp;
    String code;
    EditText etMobileForCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_mobile_number_register);

        btnGetCode=findViewById(R.id.btnGetCode);
        ccp = findViewById(R.id.ccpfreelancer);
        etMobileForCode = findViewById(R.id.etMobileForCode);



        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {

                code = ccp.getSelectedCountryCodeWithPlus();

            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FreelancerMobileNumberRegisterActivity.this,FreeelancerVerificationActivity.class));
            }
        });

    }
}
