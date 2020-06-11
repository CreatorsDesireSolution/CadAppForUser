package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cadappforuser.SqliteDatabase.DatabaseHelper;
import com.example.cadappforuser.adapter.OfferedWithPriceAdapter;
import com.example.cadappforuser.model.OfferedWithPriceModel;

import java.util.ArrayList;

public class ServicesOfferedWithprice extends AppCompatActivity {
  RecyclerView recyclerView;
    Button btnOffered;
    DatabaseHelper db;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;

    ListView serviceList;
ArrayList<OfferedWithPriceModel> offeredWithPriceModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_offered_withprice);

      //  recyclerView=findViewById(R.id.recycleviewPrice);

        offeredWithPriceModelArrayList=new ArrayList<>();
        btnOffered=findViewById(R.id.btnOffered);


        db=new DatabaseHelper(this);
        arrayList=new ArrayList<>();
        serviceList=findViewById(R.id.listview);



        serviceList.refreshDrawableState();
        arrayList.clear();
        viewData();

        btnOffered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ServicesOfferedWithprice.this,FreelancerCertificationActivity.class);
                startActivity(intent);
            }
        });

//
//        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
//        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
//        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
//        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
//        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
//        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
//        offeredWithPriceModelArrayList.add(new OfferedWithPriceModel("massage","fixed"));
//
//        OfferedWithPriceAdapter offeredWithPriceAdapter=new OfferedWithPriceAdapter(this,offeredWithPriceModelArrayList);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(offeredWithPriceAdapter);
    }


    private void viewData() {

        Cursor cursor=db.ShowData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "not select", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                arrayList.add(cursor.getString(1));
            }
            arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
            serviceList.setAdapter(arrayAdapter);
            arrayList=null;
            serviceList.refreshDrawableState();
            arrayAdapter.notifyDataSetChanged();

        }
        cursor.close();
    }
}
