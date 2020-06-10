package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FreelancerCertificationActivity extends AppCompatActivity {

    Button btnCertificate;
    TextView txt_uploadcertification,txt_upload_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        btnCertificate=findViewById(R.id.btnCertificate);
        txt_uploadcertification = findViewById(R.id.txt_uploadcertification);
        txt_upload_picture = findViewById(R.id.txt_upload_picture);

        btnCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerCertificationActivity.this,BackgoundOfFreelancerActivity.class);
                startActivity(intent);
            }
        });
    }

}
