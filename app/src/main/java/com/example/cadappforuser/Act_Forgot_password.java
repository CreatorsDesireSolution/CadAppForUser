package com.example.cadappforuser;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Act_Forgot_password extends AppCompatActivity {

    EditText et_newpassword,et_cnfrmpaswd;
    CheckBox chk_terms;
    Button btn_passsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__forgot_password);

        et_newpassword = findViewById(R.id.et_newpassword);
        et_cnfrmpaswd = findViewById(R.id.et_cnfrmpaswd);

        chk_terms = findViewById(R.id.chk_terms);
        btn_passsubmit = findViewById(R.id.btn_passsubmit);

    }
}
