package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new MyThread().start();


    }

    class MyThread extends Thread
    {
        @Override
        public void run()
        {
            super.run();
            try {
                Thread.sleep(4000);
                Intent in=new Intent(SplashScreen.this, SignInActivity.class);
                startActivity(in);
                finish();
            }
            catch (Exception e)
            {

            }
        }
    }
}
