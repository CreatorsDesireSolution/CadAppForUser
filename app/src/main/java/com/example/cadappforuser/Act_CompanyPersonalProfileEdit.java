package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_CompanyPersonalProfileEdit extends AppCompatActivity {
    TextView tv_seemore;
    LinearLayout lay_workperform;
    boolean isOpen = false;
    TextView tv_workperform;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__company_personal_profile_edit);

        tv_seemore = findViewById(R.id.tv_seemore);
        tv_workperform = findViewById(R.id.tv_workperform);
        lay_workperform = findViewById(R.id.lay_workperform);

        tv_seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Act_seemoredetails_Company.class);
                startActivity(intent);
            }
        });

    }
}
