package com.example.cadappforuser;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class StaffAddServicesActivity extends AppCompatActivity {

    EditText etSName,etSDuration;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_activity_add_services);

        etSName = findViewById(R.id.etSName);
        etSDuration = findViewById(R.id.etSDuration);
        btn_add = findViewById(R.id.btn_add);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Add Services");

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
