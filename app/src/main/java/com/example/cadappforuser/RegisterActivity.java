package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    TextView txtGender;
    EditText etAddress;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Register");

        txtGender=findViewById(R.id.etGender);
        etAddress=findViewById(R.id.etAddress);

        final Intent intent=getIntent();
        final String gender=intent.getStringExtra("gender");
        txtGender.setText(gender);

        etAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(RegisterActivity.this,CurrentLocation.class);
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
                 startActivity(new Intent(RegisterActivity.this,MobileNumberRegisterActivity.class));
             }
         });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
