package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cadappforuser.adapter.OfferedWithPriceAdapter;
import com.example.cadappforuser.model.OfferedWithPriceModel;

import java.util.ArrayList;

public class ServicesOfferedWithprice extends AppCompatActivity {
RecyclerView recyclerView;
Button btnOffered;
ArrayList<OfferedWithPriceModel> offeredWithPriceModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_offered_withprice);
        recyclerView=findViewById(R.id.recycleviewPrice);
        offeredWithPriceModelArrayList=new ArrayList<>();

        btnOffered=findViewById(R.id.btnOffered);

        btnOffered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ServicesOfferedWithprice.this,FreelancerCertificationActivity.class);
                startActivity(intent);
            }
        });

        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));

        OfferedWithPriceAdapter offeredWithPriceAdapter=new OfferedWithPriceAdapter(this,offeredWithPriceModelArrayList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(offeredWithPriceAdapter);
    }
}
