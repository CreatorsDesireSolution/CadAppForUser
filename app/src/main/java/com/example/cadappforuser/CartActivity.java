package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cadappforuser.SqliteDatabase.Myhelper;
import com.example.cadappforuser.adapter.CartAdapter;
import com.example.cadappforuser.model.CartModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    ArrayList<CartModel>cartModelArrayList;
    Button btnCheckOut;
    TextView txt_total_price;
    int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnCheckOut=findViewById(R.id.btnCheckOut1);
        txt_total_price= findViewById(R.id.txt_total_price);

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
        Myhelper myhelper=new Myhelper(this);
        SQLiteDatabase database = myhelper.getReadableDatabase();
        String sql = "select * from CART";
        Cursor c = database.rawQuery(sql,null);

        if(c.moveToFirst()) {
            while (c.moveToNext()) {
                int id = c.getInt(0);
                String name = c.getString(1);
                String desc = c.getString(2);
                String price = c.getString(3);
                String item_image=c.getString(4);
                int qty=c.getInt(5);
                total+=(Integer.parseInt(price)*qty);
                cartModelArrayList.add(new CartModel(item_image,name,price,desc,id,qty));
            }
        }

       txt_total_price.setText("Rs."+Integer.valueOf(total));
        cartAdapter=new CartAdapter(this,cartModelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_cart,menu);
        MenuItem menuItem=menu.findItem(R.id.cart1);
        menuItem.setOnMenuItemClickListener(
                new MenuItem.OnMenuItemClickListener() {
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

    public void resetGraph(Context context)
    {
        finish();
        startActivity(getIntent());
    }

}
