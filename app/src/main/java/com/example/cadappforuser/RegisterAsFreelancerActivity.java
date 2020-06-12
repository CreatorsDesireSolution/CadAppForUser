package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterAsFreelancerActivity extends AppCompatActivity {

    Button btnRegister;
    TextView txtGender;
    TextView etAddress;
    EditText etFirstName,etLatName,etUserEmail,etUsePhoneNumber,etGender,etReferralCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_freelancer_register);



        txtGender=findViewById(R.id.etGender);
        etAddress=findViewById(R.id.etAddress);
        etFirstName = findViewById(R.id.etFirstName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etLatName = findViewById(R.id.etLatName);
        etUsePhoneNumber = findViewById(R.id.etUsePhoneNumber);
        etReferralCode = findViewById(R.id.etReferralCode);


        final Intent intent=getIntent();
        final String gender=intent.getStringExtra("gender");
        txtGender.setText(gender);

        etAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(RegisterAsFreelancerActivity.this,CurrentLocation.class);
                intent1.putExtra("gender",gender);
                startActivity(intent1);
            }
        });

        Intent intent2=getIntent();
        etAddress.setText(intent2.getStringExtra("address"));
        txtGender.setText(intent2.getStringExtra("gender"));

        btnRegister=findViewById(R.id.btnSignedIn);
         btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(RegisterAsFreelancerActivity.this,FreelancerMobileNumberRegisterActivity.class));
             }
         });



    }

}
