package com.example.cadappforuser;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CompanySetAvalibiltyCustomActivityStaff extends AppCompatActivity {

    TextView txtStartTime,txtEndTime,txtSelectDay;
    Calendar calendarView;
    RelativeLayout relativeLayout;
    Button btnAddAvalibilty;
    String daysselect,startimr,endtime;
    String start,end,day;
    Context context;
    Act_Session act_session;
    ProgressDialog progressDialog;
String url="http://aoneservice.net.in/salon/company_staffsetavailability_api.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_set_avalibilty_custom);

        act_session=new Act_Session(getApplicationContext());

        txtEndTime=findViewById(R.id.txtEndTime);
        txtStartTime=findViewById(R.id.txtStartTime);
        txtSelectDay=findViewById(R.id.txtSelectDay);
        btnAddAvalibilty=findViewById(R.id.btnAddAvalibilty);

        context = this;

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
                startimr = txtStartTime.getText().toString();
                endtime = txtEndTime.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Act_ShowDayForStaff.class);
                intent.putExtra("starttime",startimr);
                intent.putExtra("endtime",endtime);
                startActivity(intent);
            }
        });

        startimr=txtStartTime.getText().toString();
        endtime=txtEndTime.getText().toString();

        txtStartTime.setText(startimr);
        txtEndTime.setText(endtime);



        Intent intent=getIntent();
        start=intent.getStringExtra("starttime");
        end=intent.getStringExtra("endtime");
        day=intent.getStringExtra("days");

        txtEndTime.setText(end);
        txtStartTime.setText(start);
        txtSelectDay.setText(day);



        btnAddAvalibilty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog=new ProgressDialog(CompanySetAvalibiltyCustomActivityStaff.this,R.style.MyAlertDialogStyle);
                progressDialog.setTitle("Grocery");
                progressDialog.setMessage("Please Wait......");
                progressDialog.show();


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
                                    Toast.makeText(CompanySetAvalibiltyCustomActivityStaff.this, ""+response+act_session.staffid, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(CompanySetAvalibiltyCustomActivityStaff.this,CompanyStaffBackground.class));

                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(CompanySetAvalibiltyCustomActivityStaff.this, ""+response, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(CompanySetAvalibiltyCustomActivityStaff.this, ""+error, Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String ,String > map=new HashMap<>();
                            Log.d("day","canecl"+day);
                            Log.d("start","canecl"+start);
                            Log.d("ens","canecl"+end);

                            map.put("start_time",start);
                            map.put("end_time",end);
                            map.put("days",day);
                            map.put("id",act_session.staffid);

                            return map;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(CompanySetAvalibiltyCustomActivityStaff.this);
                    requestQueue.add(stringRequest);
            }
        });


        relativeLayout=findViewById(R.id.relative);



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
