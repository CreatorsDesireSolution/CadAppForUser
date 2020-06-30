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
import android.widget.Toast;

public class Act_FreelancerProfile extends AppCompatActivity {

    Button btn_serviceslist,btn_next;
    FrameLayout lay1;
    TextView background11;
    boolean isOpen = false;
    LinearLayout tvbg;
    TextView name,email,mobile,last,address,experience,background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancernewprofile);

        name=findViewById(R.id.name);
        email=findViewById(R.id.tv_email);
        mobile=findViewById(R.id.mobile_nummber);
        address=findViewById(R.id.tv_city);
        experience=findViewById(R.id.established);
        background=findViewById(R.id.backbg);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Profile");

        //btn_serviceslist = findViewById(R.id.serviceslist);
        btn_next = findViewById(R.id.btn_next);
        lay1 = findViewById(R.id.lay1);
        tvbg= findViewById(R.id.tvbg);
        background11 = findViewById(R.id.background11);

        Intent intent=getIntent();
        String last=intent.getStringExtra("last");
        name.setText(intent.getStringExtra("name" +last));
        address.setText(intent.getStringExtra("address"));
        mobile.setText(intent.getStringExtra("mobile"));
        email.setText(intent.getStringExtra("email"));
        experience.setText(intent.getStringExtra("experience"));
        Toast.makeText(this, ""+intent.getStringExtra("aboutus"), Toast.LENGTH_SHORT).show();
       // background.setText(intent.getStringExtra("aboutus"));




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
