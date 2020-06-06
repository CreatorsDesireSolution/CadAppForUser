package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class PaymentMethodActivity extends AppCompatActivity {

    ImageView btnCash,btnPaytm,btnAtm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("PAYMENT METHOD");
        btnAtm=findViewById(R.id.btnAtm);
        btnCash=findViewById(R.id.btnCash);
        btnPaytm=findViewById(R.id.btnPaytm);
        btnPaytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentMethodActivity.this,PaymentMethodAddCart.class));

            }
        });
        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentMethodActivity.this,PaymentMethodAddCart.class));

            }
        });
        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentMethodActivity.this,PaymentMethodAddCart.class));

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
