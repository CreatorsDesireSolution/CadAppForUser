
package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadappforuser.Appointment.FreelancerAppointment;
import com.example.cadappforuser.adapter.FreelancerAppointmentAdapter;
import com.example.cadappforuser.adapter.FreelancerViewOrderAdapter;
import com.example.cadappforuser.model.FreelancerOrderModel;
import com.example.cadappforuser.model.FreelancerViewOrderModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FreelancerViewOrder extends AppCompatActivity {

    RecyclerView recycle_order;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    Activity activity;
    FreelancerViewOrderAdapter freelancerViewOrderAdapter;
    ArrayList<FreelancerViewOrderModel> freelancerViewOrders = new ArrayList<>();
    String order_id,customer_id;
    TextView tv_firstname,lastname,address,service_name,service_price,totalamount,mobile_number,bookingtime,bookingdate;
    Button btn_approve,btn_remove;
    String  Accept = "Accept";
    String Decline = "Decline";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_view_order);

//        recycle_order = findViewById(R.id.recycle_order);

        tv_firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        service_name = findViewById(R.id.FirstserviceName);
        service_price = findViewById(R.id.AmountFirstService);
        totalamount = findViewById(R.id.totalamount);
        btn_approve = findViewById(R.id.btn_approve);
        address = findViewById(R.id.location_address);
        btn_remove = findViewById(R.id.btn_remove);
        mobile_number = findViewById(R.id.mobile_number);
        bookingtime= findViewById(R.id.bookingtime);
        bookingdate = findViewById(R.id.bookingdate);

        act_session = new Act_Session(getApplicationContext());
        context = this;
        activity = this;

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("View Order");


        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        customer_id= intent.getStringExtra("customer_id");

        ApiViewOrder(order_id);




        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Apiresponse(Decline);
            }
        });

        btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apiresponse(Accept);
            }
        });

    }


    private void ApiViewOrder(String order_id) {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");

                    freelancerViewOrders = baseRequest.getDataList(jsonArray, FreelancerViewOrderModel.class);

                    if (freelancerViewOrders.size() != 0) {



                        tv_firstname.setText( freelancerViewOrders.get(0).getCustomerFname());
                        lastname.setText(freelancerViewOrders.get(0).getCustomerLname());
                        address.setText(freelancerViewOrders.get(0).getCustomerAddress());
                        service_name.setText(freelancerViewOrders.get(0).getServiceName());
                        service_price.setText(freelancerViewOrders.get(0).getPrice());
                        totalamount.setText(freelancerViewOrders.get(0).getPrice());
                        mobile_number.setText(freelancerViewOrders.get(0).getCustomerMobile());
                        bookingdate.setText(freelancerViewOrders.get(0).getServiceDate());
                        bookingtime.setText(freelancerViewOrders.get(0).getServiceTime());


//                        freelancerViewOrderAdapter =new FreelancerViewOrderAdapter(context, freelancerViewOrders);
////                                LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
////                                recyclerView1.setLayoutManager(layoutManager1);
//                        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(FreelancerViewOrder.this);
//                        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
//                        recycle_order.setLayoutManager(gridLayoutManager);
//                        recycle_order.setHasFixedSize(true);
//                        recycle_order.setAdapter(freelancerViewOrderAdapter);

                        // imageUserLogo.setImageURI(fileget);

                        // tv_surname.setText(profile_list1.get(0).getLastname());


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {

            }

            @Override
            public void onNetworkFailure(int requestCode, String message) {

            }
        });
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/customer_bookservicedetails_api.php?" + "oid=" + order_id;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }

    public  void Apiresponse(String status){
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
      //          act_session.loginSession(context);

                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                   // act_session = new Act_Session(context, jsonObject1);
                  //  String image=jsonObject1.getString("profile_pic");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNetworkFailure(int requestCode, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        });
        RequestBody customer_id_ = RequestBody.create(MediaType.parse("text/plain"), customer_id);
        RequestBody order_id_ = RequestBody.create(MediaType.parse("text/plain"), order_id);
        RequestBody ffname_ = RequestBody.create(MediaType.parse("text/plain"), act_session.firstname);
        RequestBody status_ = RequestBody.create(MediaType.parse("text/plain"),status);

        baseRequest.callAPIAcceptDecline(1, "https://aoneservice.net.in/", customer_id_, order_id_,ffname_,status_);

    }


}
