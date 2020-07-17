package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selectgenderforfreelancerservice extends AppCompatActivity {
    Button btnmale,btnfemale;
    String freelancer_Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgenderforfreelancerservice);


        btnmale = findViewById(R.id.btnMale);
        btnfemale = findViewById(R.id.btnFemale);

        Intent intent = getIntent();
        freelancer_Id = intent.getStringExtra("id");

        btnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServicesListActivity.class);
                intent.putExtra("id",freelancer_Id);
                startActivity(intent);

            }
        });

        btnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServicesListActivity.class);
                intent.putExtra("id",freelancer_Id);
                startActivity(intent);

            }
        });
    }
}
