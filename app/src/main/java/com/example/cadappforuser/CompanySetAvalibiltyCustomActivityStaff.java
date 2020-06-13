package com.example.cadappforuser;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CompanySetAvalibiltyCustomActivityStaff extends AppCompatActivity {

    TextView txtStartTime,txtEndTime,txtSelectDay;
    Calendar calendarView;
    RelativeLayout relativeLayout;
    Button btnAddAvalibilty;
    Button done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_set_avalibilty_custom);

        txtEndTime=findViewById(R.id.txtEndTime);
        txtStartTime=findViewById(R.id.txtStartTime);
        txtSelectDay=findViewById(R.id.txtSelectDay);
        btnAddAvalibilty=findViewById(R.id.btnAddAvalibilty);


        btnAddAvalibilty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompanySetAvalibiltyCustomActivityStaff.this,CompanyHomePageActivity.class));
            }
        });


        relativeLayout=findViewById(R.id.relative);




        calendarView=Calendar.getInstance();
        final int hours=calendarView.get(Calendar.HOUR_OF_DAY);
        final int minute=calendarView.get(Calendar.MINUTE);


        txtStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(CompanySetAvalibiltyCustomActivityStaff.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                        txtStartTime.setText(hoursOfDay+" : "+minutes);
                    }
                },hours,minute,android.text.format.DateFormat.is24HourFormat(CompanySetAvalibiltyCustomActivityStaff.this));
                timePickerDialog.show();
            }
        });

        txtEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(CompanySetAvalibiltyCustomActivityStaff.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                        txtEndTime.setText(hoursOfDay+" : "+minutes);
                    }
                },hours,minute,android.text.format.DateFormat.is24HourFormat(CompanySetAvalibiltyCustomActivityStaff.this));
                timePickerDialog.show();
            }
        });

        txtSelectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), Act_ShowDayStaff.class);
                startActivity(intent);
//                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//                PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.popuplay, null, false), 600, 700, true);
//                pw.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
//                @SuppressLint("WrongViewCast") View layout = inflater.inflate(R.layout.popuplay,
//                        (ViewGroup) CompanySetAvalibiltyCustomActivityStaff.this.findViewById(R.id.done));
//
//                done =  layout.findViewById(R.id.done);
//                done.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(),CompanySetAvalibiltyCustomActivityStaff.class);
//                        startActivity(intent);
//                    }
//                });

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
