package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectGenderActivity extends AppCompatActivity {

    Button btnMale,btnFeMale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gender);

        btnFeMale=findViewById(R.id.btnFemale);
        btnMale=findViewById(R.id.btnMale);

        btnFeMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectGenderActivity.this,RegisterActivity.class));
            }
        });
        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectGenderActivity.this,RegisterActivity.class));
            }
        });
    }
}
