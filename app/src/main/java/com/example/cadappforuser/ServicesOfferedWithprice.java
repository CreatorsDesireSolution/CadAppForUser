package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cadappforuser.adapter.OfferedWithPriceAdapter;
import com.example.cadappforuser.model.OfferedWithPriceModel;

import java.util.ArrayList;

public class ServicesOfferedWithprice extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<OfferedWithPriceModel> offeredWithPriceModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_offered_withprice);

        recyclerView=findViewById(R.id.recycleviewPrice);
        offeredWithPriceModelArrayList=new ArrayList<>();


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
