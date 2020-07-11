package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class FreelancerDetailsForCompay extends AppCompatActivity {


    Button btn_serviceslist,btn_next;
    FrameLayout lay1;
    TextView background11;
    boolean isOpen = false;
    LinearLayout tvbg;
    TextView name,email,mobile,fullname,address,experience,background,tv_certificate;
    String free;
    RelativeLayout lay_certificate,workperform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_details_for_compay);


        name=findViewById(R.id.name);
        email=findViewById(R.id.tv_email);
        mobile=findViewById(R.id.mobile_nummber);
        address=findViewById(R.id.tv_city);
        experience=findViewById(R.id.established);
        background=findViewById(R.id.backbg);
        tv_certificate = findViewById(R.id.tv_certificate);
        lay_certificate = findViewById(R.id.lay_certificate);
        workperform= findViewById(R.id.workperform);
        ImageView imageView=findViewById(R.id.iv_profile_image);



        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Profile");

        //btn_serviceslist = findViewById(R.id.serviceslist);
        btn_next = findViewById(R.id.btn_next);
        lay1 = findViewById(R.id.lay1);
        tvbg= findViewById(R.id.tvbg);
        background11 = findViewById(R.id.background11);



        final Intent intent=getIntent();
        String last=intent.getStringExtra("lastname");
         free= intent.getStringExtra("id");

        name.setText(intent.getStringExtra("firstname")+last);
       // fullname.setText(intent.getStringExtra("firstname"));
        address.setText(intent.getStringExtra("address"));
        mobile.setText(intent.getStringExtra("number"));
        email.setText(intent.getStringExtra("email"));
        experience.setText(intent.getStringExtra("ageofcompany"));
        Toast.makeText(this, ""+intent.getStringExtra("aboutcompnay"), Toast.LENGTH_SHORT).show();
        background11.setText(intent.getStringExtra("aboutcompnay"));

        String img=intent.getStringExtra("image");

        Log.d("img","img"+img);
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+img).
                resize(400, 400).centerCrop().into(imageView);


        lay_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),FreelancerGetCertificateForShow.class);
                intent1.putExtra("id",free);
                startActivity(intent1);


            }
        });

        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Selectgenderforfreelancerservice.class);
                intent.putExtra("id",free);
                startActivity(intent);
            }
        });



        workperform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FreelancerWorkPerformShow.class);
                intent.putExtra("id",free);
                startActivity(intent);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Selectgenderforfreelancerservice.class);
                intent.putExtra("id",free);


                startActivity(intent);
            }
        });


//        background11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isOpen) {
//                    isOpen = false;
//                    tvbg.setVisibility(View.GONE);
//                    background11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
//                } else {
//                    isOpen = true;
//                    tvbg.setVisibility(View.VISIBLE);
//                    background11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
//
//
//                }
//            }
//        });



    }
}
