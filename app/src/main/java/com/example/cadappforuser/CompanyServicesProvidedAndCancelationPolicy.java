package com.example.cadappforuser;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CompanyServicesProvidedAndCancelationPolicy extends AppCompatActivity {

    RadioGroup radioGroup,radioGroup_cancelation,radioGroup_acceptance;
    String type = "",cancelation="",acceptance="";
    LinearLayout linearLayout;

    Calendar calendarView;
    String chec="0";
    TextView txtStartTime,txtEndTime,txtSelectDay;
    Button btnAddSet;
    Button btn_done;
    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7;
    String kilometer,atmyplace,starttime,endtime,selecteddays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services Provide area");
        setContentView(R.layout.company_services_provided_and_cancelation_policy);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup_cancelation = findViewById(R.id.radioGroup_cancelation);
        radioGroup_acceptance = findViewById(R.id.radioGroup_acceptance);
        linearLayout = findViewById(R.id.linearmain);

        calendarView= Calendar.getInstance();
        final int hours=calendarView.get(Calendar.HOUR_OF_DAY);
        final int minute=calendarView.get(Calendar.MINUTE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.set_availability) {
                    //startActivity(new Intent(CompanyServicesProvidedAndCancelationPolicy.this, FreelancerSetAvalibiltyCustomActivity.class));
                    chec="1";
                    AlertDialog.Builder alert;
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        alert=new AlertDialog.Builder(CompanyServicesProvidedAndCancelationPolicy.this,android.R.style.Theme_Material_Dialog_Alert);
                    }
                    else {
                        alert=new AlertDialog.Builder(CompanyServicesProvidedAndCancelationPolicy.this);
                    }
                    LayoutInflater inflater=getLayoutInflater();
                    View view=inflater.inflate(R.layout.activity_set_avalibilty_custom,null);

                    txtEndTime=view.findViewById(R.id.txtEndTime);
                    txtStartTime=view.findViewById(R.id.txtStartTime);
                    txtSelectDay=view.findViewById(R.id.txtSelectDay);

                    btnAddSet=view.findViewById(R.id.btnAddSet);

                    txtStartTime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TimePickerDialog timePickerDialog=new TimePickerDialog(CompanyServicesProvidedAndCancelationPolicy.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                                    txtStartTime.setText(hoursOfDay+" : "+minutes);
                                }
                            },hours,minute,android.text.format.DateFormat.is24HourFormat(CompanyServicesProvidedAndCancelationPolicy.this));
                            timePickerDialog.show();
                        }
                    });

                    txtEndTime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TimePickerDialog timePickerDialog=new TimePickerDialog(CompanyServicesProvidedAndCancelationPolicy.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                                    txtEndTime.setText(hoursOfDay+" : "+minutes);
                                }
                            },hours,minute,android.text.format.DateFormat.is24HourFormat(CompanyServicesProvidedAndCancelationPolicy.this));
                            timePickerDialog.show();
                        }
                    });

                    txtSelectDay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final AlertDialog.Builder alert;
                            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                                alert=new AlertDialog.Builder(CompanyServicesProvidedAndCancelationPolicy.this,android.R.style.Theme_Material_Dialog_Alert);
                            }
                            else {
                                alert=new AlertDialog.Builder(CompanyServicesProvidedAndCancelationPolicy.this);
                            }
                            LayoutInflater inflater=getLayoutInflater();
                            View view1=inflater.inflate(R.layout.activity_act__show_day_comapany,null);
                            btn_done = view1.findViewById(R.id.done);
                            chk1 = view1.findViewById(R.id.chk1);
                            chk2 = view1.findViewById(R.id.chk2);
                            chk3 = view1.findViewById(R.id.chk3);
                            chk4 = view1.findViewById(R.id.chk4);
                            chk5 = view1.findViewById(R.id.chk5);
                            chk6 = view1.findViewById(R.id.chk6);
                            chk7 = view1.findViewById(R.id.chk7);

                            alert.setView(view1);

                            alert.setCancelable(false);
                            final AlertDialog alertDialog=alert.create();
                            alertDialog.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
                            alertDialog.show();

                            btn_done.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String r = "";
                                    if (chk1.isChecked()) {
                                        r = r + "," + chk1.getText();
                                    }
                                    if (chk2.isChecked()) {
                                        r = r + "," + chk2.getText();
                                    }
                                    if (chk3.isChecked()) {
                                        r = r + "," + chk3.getText();
                                    }
                                    if (chk4.isChecked()) {
                                        r = r + "," + chk4.getText();
                                    }
                                    if (chk5.isChecked()) {
                                        r = r + "," + chk5.getText();
                                    }
                                    if (chk6.isChecked()) {
                                        r = r + "," + chk6.getText();
                                    }
                                    if (chk7.isChecked()) {
                                        r = r + "," + chk7.getText();
                                    }
                                    txtSelectDay.setText(r);
                                    //Intent intent = new Intent(getApplicationContext(),FreelancerSetAvalibiltyCustomActivity.class);
                                    //startActivity(intent);
                                    alertDialog.dismiss();
                                }
                            });
                           // Toast.makeText(CompanyServicesProvidedAndCancelationPolicy.this, r, Toast.LENGTH_SHORT).show();
                        }
                    });

                    //Toast.makeText(CompanyServicesProvidedAndCancelationPolicy.this, r, Toast.LENGTH_SHORT).show();

                    alert.setView(view);
                    alert.setCancelable(false);
                    final AlertDialog alertDialog=alert.create();
                    alertDialog.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
                    alertDialog.show();

                    btnAddSet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            starttime = txtStartTime.getText().toString();
                            endtime = txtEndTime.getText().toString();
                            selecteddays=txtSelectDay.getText().toString();
                            //Toast.makeText(FreelancerServicesProvide.this,""+starttime+" "+endtime+" "+selecteddays, Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    });
                }
                else {
                    type = "Always";
                    chec="0";

                }
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
