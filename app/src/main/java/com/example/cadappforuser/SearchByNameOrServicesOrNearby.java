package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.cadappforuser.adapter.ServicesSearchAdapter;
import com.example.cadappforuser.model.ServiceSearchModel;

import java.util.ArrayList;

public class SearchByNameOrServicesOrNearby extends AppCompatActivity {

    CheckBox checkName,checkNearby,checkServices;
    RecyclerView recyclerView;
    ArrayList<ServiceSearchModel> serviceSearchModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name_or_services_or_nearby);
        checkName=findViewById(R.id.checkboxName);
        checkNearby=findViewById(R.id.checkNearBy);
        checkServices=findViewById(R.id.checkServices);

        checkServices.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    startActivity(new Intent(SearchByNameOrServicesOrNearby.this,ServicesActivity.class));
                }

            }
        });

        checkNearby.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    startActivity(new Intent(SearchByNameOrServicesOrNearby.this,SearchServices.class));
                }
            }
        });

        recyclerView=findViewById(R.id.recycleview_search);
        serviceSearchModelArrayList=new ArrayList<>();
        serviceSearchModelArrayList.add(new ServiceSearchModel(R.drawable.facial,"Canaught Place","Jane Doe"));
        serviceSearchModelArrayList.add(new ServiceSearchModel(R.drawable.facial,"Canaught Place","Jane Doe"));
        serviceSearchModelArrayList.add(new ServiceSearchModel(R.drawable.facial,"Canaught Place","Jane Doe"));
        serviceSearchModelArrayList.add(new ServiceSearchModel(R.drawable.facial,"Canaught Place","Jane Doe"));
        serviceSearchModelArrayList.add(new ServiceSearchModel(R.drawable.facial,"Canaught Place","Jane Doe"));
        serviceSearchModelArrayList.add(new ServiceSearchModel(R.drawable.facial,"Canaught Place","Jane Doe"));
        serviceSearchModelArrayList.add(new ServiceSearchModel(R.drawable.facial,"Canaught Place","Jane Doe"));
        serviceSearchModelArrayList.add(new ServiceSearchModel(R.drawable.facial,"Canaught Place","Jane Doe"));

        ServicesSearchAdapter servicesSearchAdapter=new ServicesSearchAdapter(this,serviceSearchModelArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(servicesSearchAdapter);


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
}
