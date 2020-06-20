package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_FreelancerProfile extends AppCompatActivity {

    Button btn_serviceslist,btn_next;
    FrameLayout lay1;
    TextView background11;
    boolean isOpen = false;
    LinearLayout tvbg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancernewprofile);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Profile");

        //btn_serviceslist = findViewById(R.id.serviceslist);
        btn_next = findViewById(R.id.btn_next);
        lay1 = findViewById(R.id.lay1);
        tvbg= findViewById(R.id.tvbg);
        background11 = findViewById(R.id.background11);

        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectGenderCustomerServiceList.class);
                startActivity(intent);
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectGenderCustomerServiceList.class);
                startActivity(intent);
            }
        });


        background11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    isOpen = false;
                    tvbg.setVisibility(View.GONE);
                    background11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                } else {
                    isOpen = true;
                    tvbg.setVisibility(View.VISIBLE);
                    background11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);


                }
            }
        });






    }
}
