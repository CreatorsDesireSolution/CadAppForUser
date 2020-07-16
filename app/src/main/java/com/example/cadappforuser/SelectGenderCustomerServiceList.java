package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SelectGenderCustomerServiceList extends AppCompatActivity {
    Button btnmale,btnfemale;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gender_customer_service_list);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Select Gender");

        btnmale = findViewById(R.id.btnMale);
        btnfemale = findViewById(R.id.btnFemale);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");

        btnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesListActivity.class);
               intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        btnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesListActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

}
