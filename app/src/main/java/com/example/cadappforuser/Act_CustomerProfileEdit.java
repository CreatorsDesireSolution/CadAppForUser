package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Act_CustomerProfileEdit extends AppCompatActivity {

    TextView tv_editprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__customer_profile_edit);

        tv_editprofile = findViewById(R.id.tv_editprofile);

        tv_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Act_EditCustomerInformation.class);
                startActivity(intent);
            }
        });
    }
}
