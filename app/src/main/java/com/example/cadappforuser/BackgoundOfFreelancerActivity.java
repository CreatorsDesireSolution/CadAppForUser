package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BackgoundOfFreelancerActivity extends AppCompatActivity {

    Button btnBackground;
    EditText et_aboutfreelancer,et_currentplacefreelancer,et_priviouswork,et_experience;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgound_of_freelancer);

        et_aboutfreelancer = findViewById(R.id.et_aboutfreelancer);
        et_currentplacefreelancer = findViewById(R.id.et_currentplacefreelancer);
        et_priviouswork = findViewById(R.id.et_priviouswork);
        et_experience = findViewById(R.id.et_experience);


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
