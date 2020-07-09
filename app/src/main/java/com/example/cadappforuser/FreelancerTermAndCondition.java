package com.example.cadappforuser;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FreelancerTermAndCondition extends AppCompatActivity {

    CheckBox chk_termsfreelancer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_term_and_condition);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Term & Condition");

        chk_termsfreelancer= findViewById(R.id.chk_termsfreelancer);

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
