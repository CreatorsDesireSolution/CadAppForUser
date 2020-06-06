package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cadappforuser.adapter.ServicesAdapter;
import com.example.cadappforuser.model.ServicesModel;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ServicesModel> servicesModelArrayList;
    ServicesAdapter servicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services");

        recyclerView=findViewById(R.id.servicesRecycleView);
        servicesModelArrayList=new ArrayList<>();

        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));
        servicesModelArrayList.add(new ServicesModel(R.drawable.womanfacial,"HairCut","Lorem Ipsum"));


        servicesAdapter=new ServicesAdapter(this,servicesModelArrayList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(servicesAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_cart,menu);
        MenuItem menuItem=menu.findItem(R.id.cart1);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
