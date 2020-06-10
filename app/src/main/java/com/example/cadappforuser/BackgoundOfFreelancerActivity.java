package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BackgoundOfFreelancerActivity extends AppCompatActivity {

    Button btnBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgound_of_freelancer);

        btnBackground=findViewById(R.id.btnBackground);
        btnBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BackgoundOfFreelancerActivity.this,Act_Forgot_password.class);
                startActivity(intent);
            }
        });
    }
}
