package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cadappforuser.adapter.OrderSummaryAdapter;
import com.example.cadappforuser.model.FreelancerOrderModel;
import com.example.cadappforuser.model.OrderSummaryModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderSummary extends AppCompatActivity {

    RecyclerView recyclefree;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    Activity activity;
    OrderSummaryAdapter orderSummaryAdapter;
    ArrayList<OrderSummaryModel> orderSummaryModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Order Summary");
//

        recyclefree = findViewById(R.id.recyclefree);
        act_session = new Act_Session(getApplicationContext());
        context = this;
        activity = this;

        Apigetorder();
    }



    private void Apigetorder() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");

                    orderSummaryModels = baseRequest.getDataList(jsonArray, OrderSummaryModel.class);

                    if (orderSummaryModels.size() != 0) {


                        orderSummaryAdapter =new OrderSummaryAdapter(context,orderSummaryModels);
//                                LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
//                                recyclerView1.setLayoutManager(layoutManager1);
                        LinearLayoutManager layoutManager= new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        recyclefree.setLayoutManager(layoutManager);

                        recyclefree.setHasFixedSize(true);
                        recyclefree.setAdapter(orderSummaryAdapter);


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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/customer_upcomingbooking_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_cart,menu);
        MenuItem menuItem=menu.findItem(R.id.cart1);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
