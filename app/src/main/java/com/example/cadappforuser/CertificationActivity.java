package com.example.cadappforuser;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CertificationActivity extends AppCompatActivity {

    TextView txt_uploadcertification,txt_uploadPicture;
    Button btn_nextcertificate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_certification);

        txt_uploadPicture =findViewById(R.id.txt_uploadPicture);
        txt_uploadcertification = findViewById(R.id.txt_uploadcertification);

        btn_nextcertificate = findViewById(R.id.btn_nextcertificate);
    }
}
