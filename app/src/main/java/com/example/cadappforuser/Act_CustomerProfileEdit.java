package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cadappforuser.retrofit.BaseRequest;

public class Act_CustomerProfileEdit extends AppCompatActivity {

    ImageButton iv_edit;
    Act_Session act_session;
    Context context;
    BaseRequest baseRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__customer_profile_edit);

        context = this;

        iv_edit = findViewById(R.id.iv_edit);
        act_session = new Act_Session(context);

        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // ApiGetRequest();
                Intent intent = new Intent(getApplicationContext(),Act_EditCustomerInformation.class);
                startActivity(intent);
            }
        });
    }
}
