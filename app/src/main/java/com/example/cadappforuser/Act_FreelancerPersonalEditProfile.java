package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_FreelancerPersonalEditProfile extends AppCompatActivity {
    TextView tv_workperform;
    LinearLayout lay_workperform;
    boolean isOpen = false;
    TextView tv_seemore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__freelancer_personal_edit_profile);

        tv_workperform = findViewById(R.id.tv_workperform);
        lay_workperform = findViewById(R.id.lay_workperform);
        tv_seemore = findViewById(R.id.tv_seemore);


        tv_seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Act_seemoredetails_Freelancer.class);
                startActivity(intent);
            }
        });


        tv_workperform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    isOpen = false;
                    lay_workperform.setVisibility(View.GONE);
                    tv_workperform.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                } else {
                    isOpen = true;
                    tv_workperform.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);

                    lay_workperform.setVisibility(View.VISIBLE);


                }
            }
        });
    }
}
