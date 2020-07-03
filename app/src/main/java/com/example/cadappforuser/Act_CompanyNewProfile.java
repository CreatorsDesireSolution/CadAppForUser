package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class Act_CompanyNewProfile extends AppCompatActivity {
    Button btn_serviceslist,btn_next;
    FrameLayout lay1;
    TextView tv_companyname,tv_email,tv_mobile,tv_address,background11,tv_no_of_staff,tv_age;
    String companyname,emailaddress,mobile,location,aboutcompnay,no_of_staff,age;
    ImageView imageView;
    Uri file;
    String companyId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companyprofileedit);

        tv_companyname = findViewById(R.id.companyname);
        tv_email = findViewById(R.id.tv_email);
        tv_address = findViewById(R.id.tv_city);
        tv_mobile = findViewById(R.id.mobile_nummber);
        background11 = findViewById(R.id.background11);
        tv_no_of_staff= findViewById(R.id.no_of_staff);
        tv_age = findViewById(R.id.established);
        imageView = findViewById(R.id.iv_profile_image);

      //  btn_serviceslist = findViewById(R.id.serviceslist);
        btn_next = findViewById(R.id.btn_next);
        lay1 = findViewById(R.id.lay1);

        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Act_SelectGenderForList.class);
                intent.putExtra("id",companyId);
                startActivity(intent);
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesListActivity.class);
                startActivity(intent);
            }
        });

        Intent intent2 = getIntent();
        location = intent2.getStringExtra("address");
        emailaddress = intent2.getStringExtra("email");
        mobile = intent2.getStringExtra("number");
        companyname = intent2.getStringExtra("companyname");
        aboutcompnay = intent2.getStringExtra("aboutcompnay");
        no_of_staff = intent2.getStringExtra("no_of_staff");
        age = intent2.getStringExtra("ageofcompany");
        file = intent2.getParcelableExtra("image");
        imageView.setImageURI(file);
        companyId = intent2.getStringExtra("id");


        tv_companyname.setText(companyname);
        tv_email.setText(emailaddress);
        tv_mobile.setText(mobile);
        tv_address.setText(location);
        background11.setText(aboutcompnay);
        tv_no_of_staff.setText(no_of_staff);
        tv_age.setText("Since" +age+ "years");


    }
}
