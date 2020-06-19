package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FreelancerAddServicesActivity extends AppCompatActivity {
Button btnService;

Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_add_services);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Add Services");
        spinner=findViewById(R.id.spinner);
        List<String> list=new ArrayList<>();
         list.add("Select Category");
        list.add("RICA Waxing");
        list.add("Honey Waxing");
        list.add("Hair Color & Care");
        list.add("Threading");
        list.add("Waxing");
        list.add("Facial, Cleanup & Detan");
        list.add("Massage");
        list.add("Pedicure & Manicure");

        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 spinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
