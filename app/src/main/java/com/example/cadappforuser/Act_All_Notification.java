package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Act_All_Notification extends AppCompatActivity {

    TextView tv_title,tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__all__notification);

        tv_title = findViewById(R.id.tv_title1);
        tv_message = findViewById(R.id.tv_message_show);







        if(getIntent().getExtras()!=null)
        {
            for(String key : getIntent().getExtras().keySet())
            {
                if(key.equals("title"))
                    tv_title.setText(getIntent().getExtras().getString(key));
                else if(key.equals("body"))
                    tv_message.setText(getIntent().getExtras().getString(key));

            }

        }
    }
}

