package com.example.cadappforuser;

import android.os.Bundle;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class FreelancerTermAndCondition extends AppCompatActivity {

    CheckBox chk_termsfreelancer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_term_and_condition);

        chk_termsfreelancer= findViewById(R.id.chk_termsfreelancer);
    }
}
