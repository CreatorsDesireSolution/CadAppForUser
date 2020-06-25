package com.example.cadappforuser.ShowDays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.cadappforuser.CompanySetAvalibiltyCustomActivityStaff;
import com.example.cadappforuser.FreelancerSetAvalibiltyCustomActivity;
import com.example.cadappforuser.R;

public class Act_ShowDayComapany extends AppCompatActivity {


    Button btn_done;
    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__show_day_comapany);



        btn_done = findViewById(R.id.done);
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
        chk5 = findViewById(R.id.chk5);
        chk6 = findViewById(R.id.chk6);
        chk7 = findViewById(R.id.chk7);



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
                Toast.makeText(Act_ShowDayComapany.this, r, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),FreelancerSetAvalibiltyCustomActivity.class);
                intent.putExtra("days",r);
                startActivity(intent);


            }
        });



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .6));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount=0.7f;
        params.y = -20;

        getWindow().setAttributes(params);


    }
}
