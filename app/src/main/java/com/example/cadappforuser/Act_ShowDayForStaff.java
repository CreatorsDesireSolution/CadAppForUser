package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.cadappforuser.ShowDays.Act_ShowDayComapany;

public class Act_ShowDayForStaff extends AppCompatActivity {

    Button btn_done;
    CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7;
    String check, address, start, end, atkm,days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__show_day_for_staff);

        btn_done = findViewById(R.id.done);
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
        chk5 = findViewById(R.id.chk5);
        chk6 = findViewById(R.id.chk6);
        chk7 = findViewById(R.id.chk7);
        Intent intent=getIntent();
      start=intent.getStringExtra("starttime");
      end=intent.getStringExtra("endtime");

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String r = "";

                if (chk1.isChecked()) {
                    r = r + "," + chk1.getText();
                }
                if (chk2.isChecked()) {
                    r = r + "," + chk2.getText();
                }
                if (chk3.isChecked()) {
                    r = r + "," + chk3.getText();
                }
                if (chk4.isChecked()) {
                    r = r + "," + chk4.getText();
                }
                if (chk5.isChecked()) {
                    r = r + "," + chk5.getText();
                }
                if (chk6.isChecked()) {
                    r = r + "," + chk6.getText();
                }
                if (chk7.isChecked()) {
                    r = r + "," + chk7.getText();
                }
                Toast.makeText(Act_ShowDayForStaff.this, r, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CompanySetAvalibiltyCustomActivityStaff.class);
                intent.putExtra("starttime",start);
                intent.putExtra("endtime",end);
                intent.putExtra("days",r);

                startActivity(intent);

            }
        });
    }
}
