package com.example.cadappforuser;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadappforuser.ShowDays.Act_ShowDayComapany;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FreelancerServicesProvide extends AppCompatActivity {

    CheckBox checkSetAvailability;
    String kilo;
    String check="0";
    TextView txtSetAvailability;
    CheckBox checkKm,checkAtMyPlace;
    EditText etKm;TextView etAtMyPlace;
    Button btnNext;
    String adress;
    RadioGroup radioGroup;
    RadioButton typeradioButton;
    String type = "";
    String days;
    CheckBox checkFlexible,checkStrict,checkModerate;
    String kilometer,atmyplace,starttime,endtime,selecteddays;
    BaseRequest baseRequest;
    Act_Session act_session;
    ProgressDialog progressDialog;
    String r;
    String address,chec;
    String  start="start",end="end";

    TextView txtStartTime,txtEndTime,txtSelectDay;
    Calendar calendarView;
    RelativeLayout relativeLayout;
    Button btnAddSet;
    Button done;
    String daysselect;

    Context context;

    String relcheck,reladdress,relstarttime="start time",relendtime="end time",relatkm;

    Button btn_done;
    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7;
   // String check,address,starttime,endtime,atkm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_services_provide);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services Provide area");

        act_session = new Act_Session(getApplicationContext());

        checkKm=findViewById(R.id.checkKm);
        etKm=findViewById(R.id.etKm);
        checkAtMyPlace=findViewById(R.id.checkAtMyPlace);
        etAtMyPlace=findViewById(R.id.etMyPlace);
        btnNext=findViewById(R.id.btnNext);
        radioGroup = findViewById(R.id.radioGroup);
        checkModerate= findViewById(R.id.checkModerate);
        checkFlexible = findViewById(R.id.checkFlexible);
        checkStrict = findViewById(R.id.checkStrict);

       Intent intent1 = getIntent();
       days = intent1.getStringExtra("days");
       starttime = intent1.getStringExtra("starttime");
       endtime = intent1.getStringExtra("endtime");
       chec=intent1.getStringExtra("check");
       address=intent1.getStringExtra("address");
        kilo=intent1.getStringExtra("atkm");
        etKm.setText(kilo);
       Log.d("time","time"+days+starttime+endtime);


        etAtMyPlace.setVisibility(View.VISIBLE);
        etAtMyPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerServicesProvide.this,At_Place_Location.class);
                startActivity(intent);
            }
        });


        kilometer=etKm.getText().toString();
        Log.d("kilome","kelo"+kilometer);
        final Intent intent=getIntent();
        adress=intent.getStringExtra("address");
        etAtMyPlace.setText(adress);

        calendarView=Calendar.getInstance();
        final int hours=calendarView.get(Calendar.HOUR_OF_DAY);
        final int minute=calendarView.get(Calendar.MINUTE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.radioalways) {
                    type = "always";
                    chec="0";

                } else {
                    chec="1";
                    AlertDialog.Builder alert;
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        alert=new AlertDialog.Builder(FreelancerServicesProvide.this,android.R.style.Theme_Material_Dialog_Alert);
                    }
                    else {
                        alert=new AlertDialog.Builder(FreelancerServicesProvide.this);
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
                            TimePickerDialog timePickerDialog=new TimePickerDialog(FreelancerServicesProvide.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                                    txtStartTime.setText(hoursOfDay+" : "+minutes);
                                }
                            },hours,minute,android.text.format.DateFormat.is24HourFormat(FreelancerServicesProvide.this));
                            timePickerDialog.show();
                        }
                    });

                    txtEndTime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TimePickerDialog timePickerDialog=new TimePickerDialog(FreelancerServicesProvide.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                                    txtEndTime.setText(hoursOfDay+" : "+minutes);
                                }
                            },hours,minute,android.text.format.DateFormat.is24HourFormat(FreelancerServicesProvide.this));
                            timePickerDialog.show();
                        }
                    });

                    txtSelectDay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           // Intent intent = new Intent(getApplicationContext(), Act_ShowDayComapany.class);
                            //startActivity(intent);


                            final AlertDialog.Builder alert;
                            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                                alert=new AlertDialog.Builder(FreelancerServicesProvide.this,android.R.style.Theme_Material_Dialog_Alert);
                            }
                            else {
                                alert=new AlertDialog.Builder(FreelancerServicesProvide.this);
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
                            Toast.makeText(FreelancerServicesProvide.this, r, Toast.LENGTH_SHORT).show();


                        }
                    });

                    Toast.makeText(FreelancerServicesProvide.this, r, Toast.LENGTH_SHORT).show();


                    alert.setView(view);

                    alert.setCancelable(false);
                    final AlertDialog alertDialog=alert.create();
                    alertDialog.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
                    alertDialog.show();


                   /* Intent intent=new Intent(FreelancerServicesProvide.this,FreelancerSetAvalibiltyCustomActivity.class);
                    intent.putExtra("check",chec);
                    intent.putExtra("address",adress);
                    intent.putExtra("atkm",kilometer);
                    intent.putExtra("start",start);
                    intent.putExtra("end",end);
                    startActivity(intent);*/

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
            }

        });


        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(FreelancerServicesProvide.this, ""+selecteddays+starttime+endtime+chec, Toast.LENGTH_SHORT).show();

                progressDialog=new ProgressDialog(FreelancerServicesProvide.this,R.style.MyAlertDialogStyle);
                progressDialog.setTitle("Saloon");
                progressDialog.setMessage("Please Wait......");

                progressDialog.show();
                 r = "";

                if (checkFlexible.isChecked()) {
                    r = r + "," + "flexible";
                }
                if (checkModerate.isChecked()) {
                    r = r + "," + "moderate";
                }
                if (checkStrict.isChecked()) {
                    r = r + "," + "strict";
                }

                Toast.makeText(FreelancerServicesProvide.this, ""+r, Toast.LENGTH_SHORT).show();

                kilometer = etKm.getText().toString();
                Toast.makeText(FreelancerServicesProvide.this, ""+kilometer, Toast.LENGTH_SHORT).show();
                atmyplace = etAtMyPlace.getText().toString();
          //      Act_session act_session = new Act_session(getApplicationContext);
                final Act_Session act_session=new Act_Session(getApplicationContext());
                String url="http://aoneservice.net.in/salon/freelancer_services_area.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonObject= null;
                        try {
                            jsonObject = new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");
                            if(status.equals("true") || message.equals("Success")){
                                progressDialog.dismiss();
                                Toast.makeText(FreelancerServicesProvide.this, ""+response, Toast.LENGTH_SHORT).show();
                            Intent intent2=new Intent(FreelancerServicesProvide.this,FreelancerCertificationActivity.class);
                            startActivity(intent2);
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(FreelancerServicesProvide.this, ""+response, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(FreelancerServicesProvide.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String ,String > map=new HashMap<>();
                        Log.d("cancel","canecl"+r);

                        if(chec.equals("1"))
                        {
                            map.put("avalkm","100");
                            map.put("at_my_place",atmyplace);
                            Log.d("starttime","start"+starttime);
                            map.put("start_time",starttime);
                            map.put("end_time",endtime);
                            map.put("days",selecteddays);
                            map.put("cancel_policy",r);
                            map.put("id",act_session.userId);
                        }
                       else {
                            map.put("avalkm",kilometer);
                            map.put("at_my_place",atmyplace);
                            map.put("avalibility",type);
                            map.put("cancel_policy",r);
                            map.put("id",act_session.userId);
                        }

                        return map;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(FreelancerServicesProvide.this);
                requestQueue.add(stringRequest);
            }
        });


        checkKm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    etKm.setVisibility(View.VISIBLE);
                }
                else {
                    etKm.setVisibility(View.GONE);
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
