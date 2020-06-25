package com.example.cadappforuser;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.ShowDays.Act_ShowDayComapany;
import com.example.cadappforuser.retrofit.BaseRequest;

import java.util.Calendar;

public class FreelancerSetAvalibiltyCustomActivity extends AppCompatActivity {

    TextView txtStartTime,txtEndTime,txtSelectDay;
    Calendar calendarView;
    RelativeLayout relativeLayout;
    Button btnAddSet;
    Button done;
    String daysselect;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    String startimr,endtime;
String check,address,atkm;
String relcheck,reladdress,relstarttime="start time",relendtime="end time",relatkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_avalibilty_custom);

        txtEndTime=findViewById(R.id.txtEndTime);
        txtStartTime=findViewById(R.id.txtStartTime);
        txtSelectDay=findViewById(R.id.txtSelectDay);

       // txtStartTime.setText("start time");
        //txtEndTime.setText("end time");


        context = this;
        act_session = new Act_Session(context);


       btnAddSet=findViewById(R.id.btnAddSet);

        relativeLayout=findViewById(R.id.relative);


        calendarView=Calendar.getInstance();
        final int hours=calendarView.get(Calendar.HOUR_OF_DAY);
        final int minute=calendarView.get(Calendar.MINUTE);

        Intent intent1=getIntent();
        check=intent1.getStringExtra("check");
        address= intent1.getStringExtra("address");
        atkm=intent1.getStringExtra("atkm");
         startimr=intent1.getStringExtra("start");
        endtime=intent1.getStringExtra("end");
        txtStartTime.setText(startimr);
        txtEndTime.setText(endtime);

        final Intent intent = getIntent();
        daysselect = intent.getStringExtra("days");
        relcheck=intent.getStringExtra("check");
        reladdress= intent.getStringExtra("address");
       String startimr1=intent.getStringExtra("starttime");
        String endtime1=intent.getStringExtra("endtime");
        relatkm=intent.getStringExtra("atkm");
        txtStartTime.setText(startimr1);
        txtEndTime.setText(endtime1);
        txtSelectDay.setText(daysselect);




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
                startimr = txtStartTime.getText().toString();
                endtime = txtEndTime.getText().toString();
             Intent intent = new Intent(getApplicationContext(), Act_ShowDayComapany.class);
                intent.putExtra("check",check);
                intent.putExtra("address",address);
                intent.putExtra("starttime",startimr);
                intent.putExtra("endtime",endtime);
                intent.putExtra("atkm",atkm);
                startActivity(intent);

            }
        });

        txtStartTime.setText(startimr1);
        txtEndTime.setText(endtime1);
        txtSelectDay.setText(daysselect);


        btnAddSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startimr = txtStartTime.getText().toString();
                endtime = txtEndTime.getText().toString();
                daysselect = txtSelectDay.getText().toString();
                if (startimr.equals("")){
                    Toast.makeText(context, "Please select start time", Toast.LENGTH_SHORT).show();

                }else if(endtime.equals("")){
                    Toast.makeText(context, "Please select end time", Toast.LENGTH_SHORT).show();

                }else if(daysselect.equals("")){
                    Toast.makeText(context, "Please select days ", Toast.LENGTH_SHORT).show();

                }else {

                    Intent intent1 = new Intent(getApplicationContext(), FreelancerServicesProvide.class);
                    intent1.putExtra("days",daysselect);
                    intent1.putExtra("starttime",startimr);
                    intent1.putExtra("endtime",endtime);
                    intent1.putExtra("address",reladdress);
                    intent1.putExtra("check",relcheck);
                      startActivity(intent1);
                }
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
