package com.example.cadappforuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CalenderActivity extends AppCompatActivity {

    Button btnConfirm;
    Calendar calendarView;
    int day,months,year;
    TextView selectedDate,choseDate,selectedTime,choseTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        btnConfirm=findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalenderActivity.this,CartActivity.class));
            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Calender");

        calendarView=Calendar.getInstance();
        day=calendarView.get(Calendar.DAY_OF_MONTH);
        months=calendarView.get(Calendar.MONTH);
        year=calendarView.get(Calendar.YEAR);
        months=months+1;

        selectedDate=findViewById(R.id.textSelectedDate);
        choseDate=findViewById(R.id.textChoseCalender);
        selectedTime=findViewById(R.id.textSelectedTime);
        choseTime=findViewById(R.id.textChoseTime);

        selectedDate.setText(day+"/"+months+"/"+year);

         final int hours=calendarView.get(Calendar.HOUR_OF_DAY);
        final int minute=calendarView.get(Calendar.MINUTE);

        choseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DatePickerDialog datePickerDialog=new DatePickerDialog(CalenderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonhts) {
                      month=month+1;
                      selectedDate.setText(dayOfMonhts+"/"+month+"/"+year);
                    }
                },year,months,day);
                datePickerDialog.show();
            }
        });

        choseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(CalenderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                       selectedTime.setText(hoursOfDay+" : "+minutes);
                    }
                },hours,minute,android.text.format.DateFormat.is24HourFormat(CalenderActivity.this));
                timePickerDialog.show();
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
