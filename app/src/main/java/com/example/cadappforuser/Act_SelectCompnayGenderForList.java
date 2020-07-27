package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Act_SelectCompnayGenderForList extends AppCompatActivity {
    Button btnmale,btnfemale;
    String id,userImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__select_compnay_gender_for_list);

        btnmale = findViewById(R.id.btnMale);
        btnfemale = findViewById(R.id.btnFemale);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        userImage=intent.getStringExtra("user");


        btnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServiceListComapnyForShow.class);
                intent.putExtra("id",id);
                intent.putExtra("user",userImage);
                startActivity(intent);

            }
        });

        btnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServiceListComapnyForShow.class);
                intent.putExtra("id",id);

                startActivity(intent);

            }
        });
    }
}
