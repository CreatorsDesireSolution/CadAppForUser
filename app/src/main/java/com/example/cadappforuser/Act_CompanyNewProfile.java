package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.PrintWriter;

import de.hdodenhof.circleimageview.CircleImageView;

public class Act_CompanyNewProfile extends AppCompatActivity {
    Button btn_serviceslist,btn_next;
    FrameLayout lay1;
    TextView tv_companyname,tv_email,tv_mobile,tv_address,background11,tv_no_of_staff,tv_age;
    String companyname,emailaddress,mobile,location,aboutcompnay,no_of_staff,age;
    ImageView imageView;
    Uri file;
    String companyId;
    TextView tv_certificate;
    RelativeLayout lay_certificate,workperform;
    CircleImageView iv_profile_image;
    Activity activity;


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
        tv_certificate = findViewById(R.id.tv_certificate);
        lay_certificate= findViewById(R.id.lay_certificate);
        workperform= findViewById(R.id.workperform);

      //  btn_serviceslist = findViewById(R.id.serviceslist);
        btn_next = findViewById(R.id.btn_next);
        lay1 = findViewById(R.id.lay1);
        iv_profile_image= findViewById(R.id.iv_profile_image);

        activity = this;




        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Act_SelectCompnayGenderForList.class);
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

        lay_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CompnayCertificateShow.class);
                intent.putExtra("id",companyId);
                startActivity(intent);

            }
        });

        workperform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CompanyGetWorkPerformforShow.class);
                intent.putExtra("id",companyId);
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
//        file = intent2.getParcelableExtra("image");
//        imageView.setImageURI(file);
        companyId = intent2.getStringExtra("id");

        try{
            String img_str=intent2.getStringExtra("image");
            Log.d("prof","prof "+img_str);
            if (!img_str.equals("")){
                Log.d("enco","nco"+img_str);
                Log.d("prof","prof "+"http://aoneservice.net.in/salon/documents/"+img_str);
                Picasso.get().load("http://aoneservice.net.in/salon/documents/"+img_str).
                        resize(100, 100).centerCrop().into(iv_profile_image);
            }
        }catch (Exception e){
            Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show();
        }


        tv_companyname.setText(companyname);
        tv_email.setText(emailaddress);
        tv_mobile.setText(mobile);
        tv_address.setText(location);
        background11.setText(aboutcompnay);
        tv_no_of_staff.setText(no_of_staff);
        tv_age.setText("Since" +age+ "years");


    }
}
