package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class FreelancerMobileNumberRegisterActivity extends AppCompatActivity {

    Button btnGetCode;
    CountryCodePicker ccp;
    String code;
    EditText etMobileForCode;
    String mobilenumber,mobilenumber1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_mobile_number_register);

        btnGetCode=findViewById(R.id.btnGetCode);
        ccp = findViewById(R.id.ccpfreelancer);
        etMobileForCode = findViewById(R.id.etMobileForCode);



        Intent intent = getIntent();
        mobilenumber = intent.getStringExtra("mobilenumber");
        etMobileForCode.setText(mobilenumber);




        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {

                code = ccp.getSelectedCountryCodeWithPlus();

            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobilenumber1 = etMobileForCode.getText().toString();

                if (mobilenumber1.equals(mobilenumber)) {
                    startActivity(new Intent(FreelancerMobileNumberRegisterActivity.this, FreeelancerVerificationActivity.class));
                }else {
                    Toast.makeText(FreelancerMobileNumberRegisterActivity.this, "please enter valid number", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
