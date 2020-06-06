package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BackgoundOfCompanyActivity extends AppCompatActivity {

    Spinner sp_male,sp_female,sp_team_size;
    String team,male,female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgound_of_company);


        sp_team_size = findViewById(R.id.sp_team_size);
        sp_male = findViewById(R.id.sp_male);
        sp_female = findViewById(R.id.sp_female);


        ArrayAdapter<String> teamadapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.team));
        teamadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_team_size.setAdapter(teamadapter);



        final ArrayAdapter<String> male_adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.male));
        male_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_male.setAdapter(male_adapter);


        ArrayAdapter<String> female_adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.female));
        female_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_female.setAdapter(female_adapter);




        sp_team_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                team = parentView.getSelectedItem().toString();
                if (team.equals("team")){
                    Intent intent = getIntent();
//                   Spinner_item = intent.getStringExtra("SPINNER_ITEM");
//                   sp_country.setText(Spinner_item);
                    Toast.makeText(getApplicationContext(), " Please Choose Team", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });



        sp_male.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                male = parentView.getSelectedItem().toString();
                if (male.equals("male")){
                    Intent intent = getIntent();
//                   Spinner_item = intent.getStringExtra("SPINNER_ITEM");
//                   sp_country.setText(Spinner_item);
                    Toast.makeText(getApplicationContext(), " Please select Numbers", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        sp_female.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                female = parentView.getSelectedItem().toString();
                if (female.equals("female")){
                    Intent intent = getIntent();
//                   Spinner_item = intent.getStringExtra("SPINNER_ITEM");
//                   sp_country.setText(Spinner_item);
                    Toast.makeText(getApplicationContext(), " Please Choose numbers", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

    }
}
