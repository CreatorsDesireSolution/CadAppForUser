package com.example.cadappforuser;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class TermAndCondition extends AppCompatActivity {

    CheckBox checkBox;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_condition);

        checkBox = findViewById(R.id.checkBox);
        btn_submit = findViewById(R.id.btn_submit);
    }
}
