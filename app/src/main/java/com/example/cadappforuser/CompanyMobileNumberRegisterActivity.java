package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CompanyMobileNumberRegisterActivity extends AppCompatActivity {

    Button btnGetCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_mobile_number_register);

        btnGetCode=findViewById(R.id.btnGetCode);

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompanyMobileNumberRegisterActivity.this,CompanyVerificationActivity.class));
            }
        });

    }
}
