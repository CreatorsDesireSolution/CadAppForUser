package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class MobileNumberRegisterActivity extends AppCompatActivity {

    Button btnGetCode;
    String code;
    CountryCodePicker ccp;
    EditText etMobileForCode;
    String mobilenumber,mobilenumber1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number_register);

        btnGetCode=findViewById(R.id.btnGetCode);
        ccp = findViewById(R.id.ccp);
        etMobileForCode= findViewById(R.id.etMobileForCode);


        Intent intent = getIntent();
        mobilenumber = intent.getStringExtra("mobilenumber");
        etMobileForCode.setText(mobilenumber);


        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobilenumber1 = etMobileForCode.getText().toString();

                if (mobilenumber1.equals(mobilenumber)) {
                    startActivity(new Intent(MobileNumberRegisterActivity.this, VerificationActivity.class));
                }else {
                    Toast.makeText(MobileNumberRegisterActivity.this, "please enter valid number", Toast.LENGTH_SHORT).show();
                }
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
