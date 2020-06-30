package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CompanyVerificationActivity extends AppCompatActivity {

    Button btnVerify;
    EditText etOtp;

    Act_Session act_session;
    String OTP;
    String OTPchheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_verification);

        act_session= new Act_Session(getApplicationContext());


        btnVerify=findViewById(R.id.btnVerify);
        etOtp= findViewById(R.id.etOtp);


        OTP= act_session.otp;

        etOtp.setText(OTP);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OTPchheck= etOtp.getText().toString();

                if (OTPchheck.equals(OTP)) {
                    startActivity(new Intent(CompanyVerificationActivity.this, BackgoundOfCompanyActivity.class));
                }else {
                    Toast.makeText(CompanyVerificationActivity.this, "please enter valid otp", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}
