package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class ShowCompanyStaffProfile extends AppCompatActivity {

    TextView txtEmail,txtName,txtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_company_staff_profile);

        txtEmail=findViewById(R.id.textStaffEmail);
        txtName=findViewById(R.id.textStaffName);
        txtMobile=findViewById(R.id.textStaffMobileNumber);

        Intent intent=getIntent();
        txtMobile.setText(intent.getStringExtra("mobile"));
        txtName.setText(intent.getStringExtra("name"));
        txtEmail.setText(intent.getStringExtra("email"));


    }
}
