package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.cadappforuser.model.CheckBoxModel;

import java.util.ArrayList;

public class PrachiOffered extends AppCompatActivity {

    ArrayList<CheckBoxModel> checkBoxModels= new ArrayList<>();
    TextView checkboxvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prachi_offered);

        checkboxvalue = findViewById(R.id.txt_checkvalue);
       // checkboxvalue.setText(checkBoxModels.get(0).getChecked());





    }
    }

