package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cadappforuser.model.CompanyService;

public class Act_SelectGenderForList extends AppCompatActivity {

    Button btnmale,btnfemale;
    String companyid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__select_gender_for_list);

        btnmale = findViewById(R.id.btnMale);
        btnfemale = findViewById(R.id.btnFemale);

        Intent intent = getIntent();
        companyid = intent.getStringExtra("id");

        btnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CompanyShowServices.class);
                intent.putExtra("id",companyid);
                startActivity(intent);

            }
        });
    }
}
