package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Act_SelectCompnayGenderForListFreelancer extends AppCompatActivity {
    Button btnmale,btnfemale;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__select_compnay_gender_for_list);

        btnmale = findViewById(R.id.btnMale);
        btnfemale = findViewById(R.id.btnFemale);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");


        btnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServiceListComapnyForShowFreelancer.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }
        });

        btnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServiceListComapnyForShowFreelancer.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }
        });
    }
}
