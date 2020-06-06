package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.cadappforuser.adapter.OrderSummaryAdapter;
import com.example.cadappforuser.model.OrderSummaryModel;

import java.util.ArrayList;

public class OrderSummary extends AppCompatActivity {

    Button btnOrderProceed;
    RecyclerView recyclerView;
    OrderSummaryAdapter  orderSummaryAdapter;
    ArrayList<OrderSummaryModel> orderSummaryModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Order Summary");
        btnOrderProceed=findViewById(R.id.btnProceed);

        btnOrderProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSummary.this,PaymentMethodActivity.class));
            }
        });

        orderSummaryModelArrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.summary_recycleview);

        orderSummaryModelArrayList.add(new OrderSummaryModel("450","Hair cut","Lorem Ipsum"));
        orderSummaryModelArrayList.add(new OrderSummaryModel("150","Hair wash","Lorem Ipsum"));
        orderSummaryModelArrayList.add(new OrderSummaryModel("550","Hair spa","Lorem Ipsum"));
        orderSummaryModelArrayList.add(new OrderSummaryModel("250","Facial","Lorem Ipsum"));
        orderSummaryModelArrayList.add(new OrderSummaryModel("450","Massage","Lorem Ipsum"));
        orderSummaryModelArrayList.add(new OrderSummaryModel("450","Hair cut","Lorem Ipsum"));
        orderSummaryModelArrayList.add(new OrderSummaryModel("450","Hair cut","Lorem Ipsum"));
        orderSummaryModelArrayList.add(new OrderSummaryModel("450","Hair cut","Lorem Ipsum"));

        orderSummaryAdapter=new OrderSummaryAdapter(this,orderSummaryModelArrayList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(orderSummaryAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
