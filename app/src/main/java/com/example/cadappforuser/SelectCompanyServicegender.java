package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectCompanyServicegender extends AppCompatActivity {
    Button btnmale,btnfemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company_servicegender);


        btnmale = findViewById(R.id.btnMale);
        btnfemale = findViewById(R.id.btnFemale);

        btnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CompanyAddServicesActivity.class);
                intent.putExtra("gender","Male");
                startActivity(intent);

            }
        });

        btnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CompanyAddServicesActivity.class);
                intent.putExtra("gender","Female");
                startActivity(intent);

            }
        });
    }
}
