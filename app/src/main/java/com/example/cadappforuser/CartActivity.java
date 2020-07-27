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
    int total=0,count1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnCheckOut=findViewById(R.id.btnCheckOut1);
        txt_total_price= findViewById(R.id.txt_total_price);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Cart");

        recyclerView=findViewById(R.id.cart_recycleview);
        cartModelArrayList=new ArrayList<>();
        Myhelper myhelper=new Myhelper(this);
        SQLiteDatabase database = myhelper.getReadableDatabase();
        String sql = "select * from CART";
        Cursor c = database.rawQuery(sql,null);

       // if(c.moveToFirst()) {
            while (c.moveToNext()) {
                    int id = c.getInt(0);
                    String name = c.getString(1);
                    String desc = c.getString(2);
                    String price = c.getString(3);
                    String item_image=c.getString(4);
                    int qty=c.getInt(5);
                     String Pid=c.getString(6);
                     String status=c.getString(8);
                     String userImage=c.getString(9);
                    Log.d("PID","PID"+Pid);
                    total+=(Integer.parseInt(price)*qty);
                    cartModelArrayList.add(new CartModel(item_image,name,price,desc,id,qty,Pid,status,userImage));
            }
      //  }

        txt_total_price.setText("Rs."+Integer.valueOf(total));
        cartAdapter=new CartAdapter(this,cartModelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        Myhelper myhelper1=new Myhelper(this);
        SQLiteDatabase database1 = myhelper1.getReadableDatabase();
        String sql1 = "select * from CART";
        Cursor c1 = database1.rawQuery(sql1,null);
        while(c1.moveToNext()){
            count1++;
        }

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,OrderActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_cart,menu);
        MenuItem menuItem=menu.findItem(R.id.cart1);
        menuItem.setIcon(Converter.convertLayoutToImage(CartActivity.this,count1,R.drawable.ic_shopping_cart_black_24dp));

        menuItem.setOnMenuItemClickListener(
                new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
                finish();
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
