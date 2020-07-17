package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cadappforuser.SqliteDatabase.Myhelper;
import com.example.cadappforuser.adapter.Order;
import com.example.cadappforuser.adapter.OrderAdapter;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Order> orders,order_for_data;
    Myhelper myhelper;
    int totalPrice=0;
    TextView totals;
    Button placeOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView=findViewById(R.id.rerere);
        totals=findViewById(R.id.total);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));

        orders=new ArrayList<>();

        placeOrder=findViewById(R.id.placeorder);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderActivity.this,PaymentMethodActivity.class);
                startActivity(intent);
            }
        });

        myhelper=new Myhelper(this);
        SQLiteDatabase database = myhelper.getReadableDatabase();
        String sql = "select * from CART";
        Cursor c = database.rawQuery(sql,null);
        while(c.moveToNext()){
            String name = c.getString(1);
            String desc = c.getString(2);
            String price = c.getString(3);
            String item_image=c.getString(4);
            int qty=c.getInt(5);
           if(!price.equals(""))
               totalPrice+=(Integer.parseInt(price) * qty);
            Order item = new Order(item_image,name,"","\u20B9"+price,qty);
            orders.add(item);
        }

        recyclerView.setAdapter(new OrderAdapter(OrderActivity.this,orders));
        totals.setText("\u20B9"+(totalPrice));

    }
}
