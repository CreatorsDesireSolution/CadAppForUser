package com.example.cadappforuser;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BackgoundOfStaffActivity extends AppCompatActivity {

    EditText et_aboutstaff,et_currentworkstaff,et_previousworkstaff,et_experiencestaff;
    Button btn_nextstaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgound_of_staff);

        et_aboutstaff= findViewById(R.id.et_aboutstaff);
        et_currentworkstaff= findViewById(R.id.et_currentworkstaff);
        et_previousworkstaff= findViewById(R.id.et_previousworkstaff);
        et_experiencestaff= findViewById(R.id.et_experiencestaff);
        btn_nextstaff = findViewById(R.id.btn_nextstaff);
    }
}
