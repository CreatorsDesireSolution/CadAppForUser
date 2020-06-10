package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FreelancerCertificationActivity extends AppCompatActivity {

    Button btnCertificate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        btnCertificate=findViewById(R.id.btnCertificate);

        btnCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerCertificationActivity.this,BackgoundOfFreelancerActivity.class);
                startActivity(intent);
            }
        });
    }

}
