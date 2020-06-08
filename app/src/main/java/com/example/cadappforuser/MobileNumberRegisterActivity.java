package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hbb20.CountryCodePicker;

public class MobileNumberRegisterActivity extends AppCompatActivity {

    Button btnGetCode;
    String code;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number_register);

        btnGetCode=findViewById(R.id.btnGetCode);
        ccp = findViewById(R.id.ccp);

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MobileNumberRegisterActivity.this,VerificationActivity.class));
            }
        });


        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {

                code = ccp.getSelectedCountryCodeWithPlus();

            }
        });


    }
}
