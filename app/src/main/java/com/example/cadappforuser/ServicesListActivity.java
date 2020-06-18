package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cadappforuser.adapter.ServicesListAdapter;
import com.example.cadappforuser.model.ServicesListModel;

import java.util.ArrayList;

public class ServicesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ServicesListModel> servicesListModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services List");

        recyclerView=findViewById(R.id.recycle);
        servicesListModelArrayList=new ArrayList<>();

        servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"450","Lorem Ipsum","Facial","0","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.pro,"250","Lorem Ipsum","Hair Styling","1","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"550","Lorem Ipsum","Hair Wash","2","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.pro,"650","Lorem Ipsum","Hair Spa","3","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"150","Lorem Ipsum","Hair cut","4","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.pro,"850","Lorem Ipsum","Hair Styling","5","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"50","Lorem Ipsum","Hair Wash","6","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.pro,"150","Lorem Ipsum","Hair Cut","7","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"450","Lorem Ipsum","Face Massage","8","0"));

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        ServicesListAdapter servicesListAdapter=new ServicesListAdapter(this,servicesListModelArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(servicesListAdapter);

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
