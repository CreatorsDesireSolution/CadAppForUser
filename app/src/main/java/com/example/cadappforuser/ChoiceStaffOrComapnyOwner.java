package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceStaffOrComapnyOwner extends AppCompatActivity {

    Button btnStaffMember,btnCompanyOwner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_staff_or_comapny_owner);

        btnStaffMember=findViewById(R.id.btnStaffMember);
        btnCompanyOwner=findViewById(R.id.btnCompanyOwner);

        btnCompanyOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoiceStaffOrComapnyOwner.this,ShowStaffList.class));
            }
        });

        btnStaffMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoiceStaffOrComapnyOwner.this,CompanyStaffList.class));
            }
        });
    }
}
