package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Act_CompanyNewProfileOnlyShow extends AppCompatActivity {
    Button btn_serviceslist,btn_next;
    FrameLayout lay1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companyprofileeditshow);

      //  btn_serviceslist = findViewById(R.id.serviceslist);
       
        lay1 = findViewById(R.id.lay1);

        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Act_SelectGenderForList.class);
                startActivity(intent);
            }
        });





    }
}
