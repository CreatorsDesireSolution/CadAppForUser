package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadappforuser.SqliteDatabase.Myhelper;
import com.example.cadappforuser.adapter.Order;
import com.example.cadappforuser.adapter.OrderAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Order> orders, order_for_data;
    Myhelper myhelper;
    int totalPrice = 0;
    TextView totals;
    Button placeOrder;
    String name, desc, price, item_image, status, Pid, Cid;
    int qty;
    Act_Session act_session;
    ProgressDialog progressDialog;
    String serviceId;
    String url = "http://aoneservice.net.in/salon/customer_servicebook_api.php";

    Calendar calendarView;
    int day,months,year;
    TextView selectedDate,choseDate,selectedTime,choseTime;
    String time="",date="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.rerere);
        totals = findViewById(R.id.total);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));

        act_session = new Act_Session(this);
        Cid = act_session.userId;
        orders = new ArrayList<>();

        calendarView= Calendar.getInstance();
        day=calendarView.get(Calendar.DAY_OF_MONTH);
        months=calendarView.get(Calendar.MONTH);
        year=calendarView.get(Calendar.YEAR);
        months=months+1;

        selectedDate=findViewById(R.id.date);
        selectedTime=findViewById(R.id.time);

        final int hours=calendarView.get(Calendar.HOUR_OF_DAY);
        final int minute=calendarView.get(Calendar.MINUTE);

        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(OrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonhts) {
                        month=month+1;
                        selectedDate.setText(dayOfMonhts+"/"+month+"/"+year);
                        time=dayOfMonhts+"/"+month+"/"+year;
                    }
                },year,months,day);
                datePickerDialog.show();
            }
        });

        selectedTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(OrderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hoursOfDay, int minutes) {
                        selectedTime.setText(hoursOfDay+" : "+minutes);
                        date=hoursOfDay+" : "+minutes;
                    }
                },hours,minute,android.text.format.DateFormat.is24HourFormat(OrderActivity.this));
                timePickerDialog.show();
            }
        });

        selectedDate.setText(selectedDate.getText());
        selectedTime.setText(selectedTime.getText());

        placeOrder = findViewById(R.id.placeorder);

        myhelper = new Myhelper(this);
        SQLiteDatabase database = myhelper.getReadableDatabase();
        String sql = "select * from CART";
        Cursor c = database.rawQuery(sql, null);
        while (c.moveToNext()) {
            name = c.getString(1);
            desc = c.getString(2);
            price = c.getString(3);
            item_image = c.getString(4);
            status = c.getString(8);
            Pid = c.getString(6);
            qty = c.getInt(5);
           serviceId=c.getString(7);
            if (!price.equals(""))
                totalPrice += (Integer.parseInt(price) * qty);

            Order item = new Order(item_image, name, "", "\u20B9" + price, qty, status, Pid);
            orders.add(item);
        }

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(OrderActivity.this, R.style.MyAlertDialogStyle);
                progressDialog.setTitle("Upload");
                progressDialog.setMessage("Please Wait......");
                progressDialog.show();


                if(time.equals("") || date.equals("")){
                     Toast.makeText(OrderActivity.this, "Please Select Date Or Time", Toast.LENGTH_SHORT).show();
                     progressDialog.dismiss();
                }
                else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject;
                                jsonObject = new JSONObject(response);
                                String sucess = jsonObject.getString("success");
                                if (sucess.equals("1")) {
                                    progressDialog.dismiss();
                                    Toast.makeText(OrderActivity.this, "Order Successfull", Toast.LENGTH_SHORT).show();
                                    SQLiteDatabase database = myhelper.getWritableDatabase();
                                    database.execSQL("delete from CART");
                                    database.close();
                                    Intent intent = new Intent(OrderActivity.this, HomeAndShopLocation.class);
                                    startActivity(intent);

                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(OrderActivity.this, "not insert", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(OrderActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put("customer_id", Cid);
                            map.put("service_id", serviceId);
                            map.put("fid", Pid);
                            map.put("service_name", name);
                            map.put("service_price", price);
                            map.put("service_qty", Integer.toString(qty));
                            map.put("service_time", time);
                            map.put("service_date", date);
                            map.put("flag", status);

                            Log.d("customer_id", "customer" + act_session.userId);
                            Log.d("status", "status" + status);
                            Log.d("customer_id", "customer" + act_session.userId);
                            return map;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(OrderActivity.this);
                    requestQueue.add(stringRequest);
                }
            }
        });


        recyclerView.setAdapter(new OrderAdapter(OrderActivity.this, orders));
        totals.setText("\u20B9" + (totalPrice));
    }
}