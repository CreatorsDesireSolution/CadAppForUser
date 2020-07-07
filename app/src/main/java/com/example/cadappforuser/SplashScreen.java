package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;
    Act_Session act_session;
    String flagcheck;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        act_session = new Act_Session(getApplicationContext());
        context = this;
        flagcheck= act_session.flag;


    }

    private void startTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (act_session.login.equals("yes"))  {
                    if (flagcheck.equals("0")) {
                        Intent i = new Intent(SplashScreen.this, SelectHomeOrShop.class);
                        startActivity(i);
                        finish();

                    } if (flagcheck.equals("1")) {
                        Intent intent = new Intent(SplashScreen.this, FreelancerHomePageActivity.class);
                        startActivity(intent);
                        finish();


                    } if (flagcheck.equals("2")) {
                            Intent intent = new Intent(SplashScreen.this, CompanyHomePageActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                    Intent intent = new Intent(SplashScreen.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }


                }

            },SPLASH_TIME_OUT);


}

    @Override
    protected void onStart() {
        super.onStart();
        startTimer();
    }
}