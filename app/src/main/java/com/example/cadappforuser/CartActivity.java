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

import com.example.cadappforuser.adapter.CartAdapter;
import com.example.cadappforuser.model.CartModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    ArrayList<CartModel>cartModelArrayList;
    Button btnCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnCheckOut=findViewById(R.id.btnCheckOut1);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,OrderSummary.class));
            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Cart");

        recyclerView=findViewById(R.id.cart_recycleview);
        cartModelArrayList=new ArrayList<>();

        cartModelArrayList.add(new CartModel(R.drawable.pro,"Hair cut","450","Lorem Ipsum"));
        cartModelArrayList.add(new CartModel(R.drawable.hairwash,"Hair wash","250","Lorem Ipsum"));
        cartModelArrayList.add(new CartModel(R.drawable.hairwash,"Hair wash","850","Lorem Ipsum"));
        cartModelArrayList.add(new CartModel(R.drawable.hairwash,"Hair spa","350","Lorem Ipsum"));
        cartModelArrayList.add(new CartModel(R.drawable.pro,"Hair cut","450","Lorem Ipsum"));
        cartModelArrayList.add(new CartModel(R.drawable.pro,"Hair cut","450","Lorem Ipsum"));
        cartModelArrayList.add(new CartModel(R.drawable.pro,"Hair cut","450","Lorem Ipsum"));
        cartModelArrayList.add(new CartModel(R.drawable.pro,"Hair cut","450","Lorem Ipsum"));
        cartModelArrayList.add(new CartModel(R.drawable.pro,"Hair cut","450","Lorem Ipsum"));

        cartAdapter=new CartAdapter(this,cartModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
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
