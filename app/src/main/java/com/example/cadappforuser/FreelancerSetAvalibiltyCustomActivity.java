package com.example.cadappforuser;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class FreelancerSetAvalibiltyCustomActivity extends AppCompatActivity {

    TextView txtStartTime,txtEndTime,txtSelectDay;
    Calendar calendarView;
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_avalibilty_custom);

        txtEndTime=findViewById(R.id.txtEndTime);
        txtStartTime=findViewById(R.id.txtStartTime);
        txtSelectDay=findViewById(R.id.txtSelectDay);


        relativeLayout=findViewById(R.id.relative);




        calendarView=Calendar.getInstance();
        final int hours=calendarView.get(Calendar.HOUR_OF_DAY);
        final int minute=calendarView.get(Calendar.MINUTE);


        txtStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(FreelancerSetAvalibiltyCustomActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                        txtStartTime.setText(hoursOfDay+" : "+minutes);
                    }
                },hours,minute,android.text.format.DateFormat.is24HourFormat(FreelancerSetAvalibiltyCustomActivity.this));
                timePickerDialog.show();
            }
        });

        txtEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(FreelancerSetAvalibiltyCustomActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                        txtEndTime.setText(hoursOfDay+" : "+minutes);
                    }
                },hours,minute,android.text.format.DateFormat.is24HourFormat(FreelancerSetAvalibiltyCustomActivity.this));
                timePickerDialog.show();
            }
        });

        txtSelectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.popuplay, null, false), 600, 700, true);
                pw.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Set Avalibilty");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount=0.7f;
        params.y = -20;

        getWindow().setAttributes(params);

    }
}
