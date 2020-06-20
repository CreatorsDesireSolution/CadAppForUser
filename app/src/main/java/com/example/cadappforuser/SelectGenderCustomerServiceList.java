package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectGenderCustomerServiceList extends AppCompatActivity {
    Button btnmale,btnfemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gender_customer_service_list);

        btnmale = findViewById(R.id.btnMale);
        btnfemale = findViewById(R.id.btnFemale);

        btnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServicesListActivity.class);
                startActivity(intent);

            }
        });

        btnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServicesListActivity.class);
                startActivity(intent);

            }
        });
    }
}
