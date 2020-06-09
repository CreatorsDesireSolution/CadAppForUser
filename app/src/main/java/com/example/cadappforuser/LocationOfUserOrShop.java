package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocationOfUserOrShop extends AppCompatActivity {

    TextView locationHomeShop;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_of_user_or_shop);
        locationHomeShop=findViewById(R.id.locationHomeShop);
        btnNext=findViewById(R.id.btnNextService);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LocationOfUserOrShop.this,FreelancerServicesOffered.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        locationHomeShop.setText(intent.getStringExtra("address"));
    }
}
