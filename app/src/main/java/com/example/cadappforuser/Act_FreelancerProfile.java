package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Act_FreelancerProfile extends AppCompatActivity {

    Button btn_serviceslist,btn_next;
    FrameLayout lay1;
    TextView background11;
    boolean isOpen = false;
    LinearLayout tvbg;
    TextView name,email,mobile,last,address,experience,background;
    RelativeLayout workperform;
    ImageView imageView;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancernewprofile);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("profile");

        name=findViewById(R.id.name);
        email=findViewById(R.id.tv_email);
        mobile=findViewById(R.id.mobile_nummber);
        address=findViewById(R.id.tv_city);
        experience=findViewById(R.id.established);
        background=findViewById(R.id.backbg);
        workperform= findViewById(R.id.workperform);
        imageView=findViewById(R.id.iv_profile_image);

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
        background.setText(intent.getStringExtra("aboutus"));

         id=intent.getStringExtra("id");
        String img=intent.getStringExtra("image");

        Log.d("img","img"+img);
        Picasso.get().load(img).
                resize(400, 400).centerCrop().into(imageView);

        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectGenderCustomerServiceList.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        workperform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FreelancerWorkPerformShow.class);
                startActivity(intent);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectGenderCustomerServiceList.class);
                 intent.putExtra("id",id);
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
