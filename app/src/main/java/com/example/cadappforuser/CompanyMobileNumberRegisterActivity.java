package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class CompanyMobileNumberRegisterActivity extends AppCompatActivity {

    Button btnGetCode;
    CountryCodePicker ccp;
    EditText etMobileForCode;
    String code;
    String mobilenumber,mobilenumber1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_mobile_number_register);

        btnGetCode=findViewById(R.id.btnGetCode);
        etMobileForCode = findViewById(R.id.etMobileForCode);
        ccp = findViewById(R.id.ccp1);



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
                    startActivity(new Intent(CompanyMobileNumberRegisterActivity.this, CompanyVerificationActivity.class));
                }else {
                    Toast.makeText(CompanyMobileNumberRegisterActivity.this, "please enter valid number", Toast.LENGTH_SHORT).show();
                }

              //  startActivity(new Intent(CompanyMobileNumberRegisterActivity.this,CompanyVerificationActivity.class));
            }
        });

    }
}
