package com.example.cadappforuser;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    TextView txt_DOB;
    java.util.Calendar calendarView;
    int day,months,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Register");


        calendarView= java.util.Calendar.getInstance();
        day=calendarView.get(java.util.Calendar.DAY_OF_MONTH);
        months=calendarView.get(java.util.Calendar.MONTH);
        year=calendarView.get(java.util.Calendar.YEAR);
        months=months+1;

        btnRegister=findViewById(R.id.btnSignedIn);
        txt_DOB = findViewById(R.id.et_DOB);


        txt_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonhts) {
                        month=month+1;
                        txt_DOB.setText(dayOfMonhts+"/"+month+"/"+year);
                    }
                },year,months,day);
                datePickerDialog.show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(RegisterActivity.this,MobileNumberRegisterActivity.class));
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
