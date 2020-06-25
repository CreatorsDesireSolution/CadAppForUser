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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FreelancerAddServicesActivity extends AppCompatActivity {
    Button btn_addservice;
    Spinner spinner;
    EditText et_setprice,et_servicedescription,et_servicename;
    String setprice,setservicename,description,selectcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_add_services);


        btn_addservice = findViewById(R.id.btn_addservices);
        et_servicedescription = findViewById(R.id.et_servicedescription);
        et_servicename = findViewById(R.id.etSName);
        et_setprice = findViewById(R.id.etSetPrice);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Add Services");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                Object item = adapterView.getItemAtPosition(i);
                 selectcategory = spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

         btn_addservice.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 setprice = et_setprice.getText().toString();
                 description = et_servicedescription.getText().toString();
                 setservicename = et_servicename.getText().toString();

                 if (setprice.equals("")){

                     Toast.makeText(FreelancerAddServicesActivity.this, "Please set the price", Toast.LENGTH_SHORT).show();
                 }else  if (description.equals("")){
                     Toast.makeText(FreelancerAddServicesActivity.this, "Please add description of service", Toast.LENGTH_SHORT).show();

                 }else  if (setservicename.equals("")){

                 }else if (selectcategory.equals("Select Category")){

                 }else {
                     

                 }


             }
         });


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
