package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Act_CustomerNewHome extends AppCompatActivity {

    Button buttonHome,buttonShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__customer_new_home);

        buttonHome = findViewById(R.id.buttonHome);
        buttonShop = findViewById(R.id.buttonShop);


        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomePageActivity.class);
                startActivity(intent);
            }
        });

        buttonShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomePageActivity.class);
                startActivity(intent);
            }
        });



    }
}
