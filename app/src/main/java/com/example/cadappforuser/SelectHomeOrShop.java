package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectHomeOrShop extends AppCompatActivity {

    Button btnShop,btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_home_or_shop);

        btnHome=findViewById(R.id.buttonHome);
        btnShop=findViewById(R.id.buttonShop);

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectHomeOrShop.this,Act_ShopLocation.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectHomeOrShop.this,HomeAndShopLocation.class);
                startActivity(intent);
            }
        });
    }
}
