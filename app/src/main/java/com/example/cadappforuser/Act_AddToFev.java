package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cadappforuser.adapter.AddFevAdapter;
import com.example.cadappforuser.adapter.FevListModel;
import com.example.cadappforuser.adapter.ServicesListAdapter;
import com.example.cadappforuser.model.ServicesListModel;

import java.util.ArrayList;

public class Act_AddToFev extends AppCompatActivity {

    RecyclerView recyclerviewfev;
    ArrayList<FevListModel> fevListModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__add_to_fev);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Fevorite List");

        recyclerviewfev = findViewById(R.id.recyclerviewfev);
        fevListModels = new ArrayList<>();


        fevListModels.add(new FevListModel(R.drawable.hairwash,"450","Lorem Ipsum","Facial"));

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        AddFevAdapter addFevAdapter=new AddFevAdapter(this,fevListModels);

        recyclerviewfev.setLayoutManager(layoutManager);
        recyclerviewfev.setHasFixedSize(true);
        recyclerviewfev.setAdapter(addFevAdapter);


    }
}
