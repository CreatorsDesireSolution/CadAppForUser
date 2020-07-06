package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FreelancerPersonalProfileActivity extends AppCompatActivity {

    EditText userMobile,userFirstName,userLastName,userGender,userLocation,userBackground;
    ImageView userImage,userEditor;
    TextView userEmail,userRating;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_personal_profile2);

        userEmail=findViewById(R.id.userEmail);
        userMobile=findViewById(R.id.userMobileNo);
        userFirstName=findViewById(R.id.userFirstName);
        userGender=findViewById(R.id.userGender);
        userLastName=findViewById(R.id.userLastName);
        userRating=findViewById(R.id.userRating);
        userLocation=findViewById(R.id.userLocation);
        userBackground=findViewById(R.id.userBackground);
        userEditor=findViewById(R.id.editProfile);

        btnSubmit=findViewById(R.id.btnSumbit);
        userEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userFirstName.setEnabled(true);
                userBackground.setEnabled(true);
                userLastName.setEnabled(true);
                userMobile.setEnabled(true);
                btnSubmit.setVisibility(View.VISIBLE);
            }
        });

    }
}
