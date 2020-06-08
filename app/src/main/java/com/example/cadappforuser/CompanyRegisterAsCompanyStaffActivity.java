package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CompanyRegisterAsCompanyStaffActivity extends AppCompatActivity {

    Button btnRegister;
    TextView txtGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_as_staff_register);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Register");

        txtGender=findViewById(R.id.etGender);

        Intent intent=getIntent();
        String gender=intent.getStringExtra("gender");
        txtGender.setText(gender);

        btnRegister=findViewById(R.id.btnSignedIn);
         btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(CompanyRegisterAsCompanyStaffActivity.this,CompanySetAvalibiltyCustomActivityStaff.class));
             }
         });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
