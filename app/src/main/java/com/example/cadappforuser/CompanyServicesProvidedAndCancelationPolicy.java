package com.example.cadappforuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CompanyServicesProvidedAndCancelationPolicy extends AppCompatActivity {

    RadioGroup radioGroup,radioGroup_cancelation,radioGroup_acceptance;
    String type = "",cancelation="",acceptance="";
    LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //final LinearLayout layout = new LinearLayout(this);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services Provide area");
        setContentView(R.layout.company_services_provided_and_cancelation_policy);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup_cancelation = findViewById(R.id.radioGroup_cancelation);
        radioGroup_acceptance = findViewById(R.id.radioGroup_acceptance);
        linearLayout = findViewById(R.id.linearmain);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.set_availability) {
                    startActivity(new Intent(CompanyServicesProvidedAndCancelationPolicy.this, FreelancerSetAvalibiltyCustomActivity.class));
                }
                    type = "Always";
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
