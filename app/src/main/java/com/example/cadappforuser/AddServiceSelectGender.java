package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddServiceSelectGender extends AppCompatActivity {

    Button btnMale,btnFeMale;
    String gender;
    ImageView image_service,iv_camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_select_gender);

        btnFeMale=findViewById(R.id.btnFemale);
        btnMale=findViewById(R.id.btnMale);


        btnFeMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(AddServiceSelectGender.this,FreelancerAddServicesActivity.class);
               intent.putExtra("gender","female");
               startActivity(intent);
            }
        });
        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddServiceSelectGender.this,FreelancerAddServicesActivity.class);
                intent.putExtra("gender","male");
                startActivity(intent);
            }
        });
    }
}
