package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cadappforuser.adapter.FreelancerViewOrderAdapter;
import com.example.cadappforuser.adapter.OrderSummaryAdapter;
import com.example.cadappforuser.model.CustomerOrderViewDetail;
import com.example.cadappforuser.model.FreelancerViewOrderModel;
import com.example.cadappforuser.model.OrderSummaryModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerViewOrder extends AppCompatActivity {

    RecyclerView recycle_order;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    Activity activity;
  //  OrderSummaryAdapter freelancerViewOrderAdapter;
    ArrayList<CustomerOrderViewDetail> customerOrderViewDetails = new ArrayList<>();
    String order_id,flag;
    TextView tv_firstname,lastname,address,service_name,service_price,totalamount,mobile_number,bookingtime,bookingdate;
    Button btn_approve,btn_remove;
    String  Accept = "Accept";
    String Decline = "Remove";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_order);

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
        flag= intent.getStringExtra("flag");

        ApiViewOrder(order_id,flag);

    }

    private void ApiViewOrder(String order_id,String flag) {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");

                    customerOrderViewDetails = baseRequest.getDataList(jsonArray, CustomerOrderViewDetail.class);

                    if (customerOrderViewDetails.size() != 0) {



                        tv_firstname.setText( customerOrderViewDetails.get(0).getFreelancerFname());
                        lastname.setText(customerOrderViewDetails.get(0).getFreelancerLname());
                        address.setText(customerOrderViewDetails.get(0).getFreelancerAddress());
                        service_name.setText(customerOrderViewDetails.get(0).getServiceName());
                        service_price.setText(customerOrderViewDetails.get(0).getPrice());
                        totalamount.setText(customerOrderViewDetails.get(0).getPrice());
                        mobile_number.setText(customerOrderViewDetails.get(0).getFreelancerMobile());
                        bookingdate.setText(customerOrderViewDetails.get(0).getServiceDate());
                        bookingtime.setText(customerOrderViewDetails.get(0).getServiceTime());


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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/customer_upcomingbookingdetails_api.php?" + "oid=" + order_id +"&&flag="+ flag;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }

}
