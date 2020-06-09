package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class PrachiOffered extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prachi_offered);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("variable");
            TextView c = (TextView) findViewById(R.id.txt_checkvalue);
            c.setText(value);
        }
    }
    }

