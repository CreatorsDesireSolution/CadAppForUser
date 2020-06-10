package com.example.cadappforuser;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StaffCertificationActivity extends AppCompatActivity {

    TextView txt_uploadstaffcertification,txt_uploadpicturestaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_activity_certification);

        txt_uploadstaffcertification = findViewById(R.id.txt_uploadstaffcertification);
        txt_uploadpicturestaff = findViewById(R.id.txt_uploadpicturestaff);
    }
}
