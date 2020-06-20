package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FreelancerGenderSelectSeeList extends AppCompatActivity {
    Button btnmale,btnfemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_gender_select_see_list);

        btnmale = findViewById(R.id.btnMale);
        btnfemale = findViewById(R.id.btnFemale);

        btnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServicesListActivityForShow.class);
                startActivity(intent);

            }
        });
    }
}
