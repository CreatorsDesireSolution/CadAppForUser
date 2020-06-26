package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompanyStaffBackground extends AppCompatActivity {

    Button btnCompanyStaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_staff_background);

        btnCompanyStaff=findViewById(R.id.btnBackgroundStaff);
        btnCompanyStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CompanyStaffBackground.this,FreelancerCertificationActivity.class);
                startActivity(intent);
            }
        });
    }
}
