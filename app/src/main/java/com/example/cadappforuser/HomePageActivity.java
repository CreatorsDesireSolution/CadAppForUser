package com.example.cadappforuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.cadappforuser.adapter.ServicesFeturesAndCategoriesHomeAdapter;
import com.example.cadappforuser.adapter.ServicesFreelancerAdapterHome;
import com.example.cadappforuser.model.ServicesFeatureAndCategoriesHomeModel;
import com.example.cadappforuser.model.ServicesFreelancerHomeModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity  implements  NavigationView
        .OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    RecyclerView recyclerView,recyclerView1;
    ImageView women,man;
    ArrayList<ServicesFeatureAndCategoriesHomeModel> servicesFeatureAndCategoriesHomeModelArrayList;

    ArrayList<ServicesFreelancerHomeModel> servicesFreelancerHomeModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawable_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recycleView);
        recyclerView1=findViewById(R.id.recycleView1);

        women=findViewById(R.id.woman);
        man=findViewById(R.id.man);

        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePageActivity.this,HomeAndShopLocation.class);
                startActivity(intent);
            }
        });

        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePageActivity.this,HomeAndShopLocation.class);
                startActivity(intent);
            }
        });

        servicesFeatureAndCategoriesHomeModelArrayList=new ArrayList<>();
        servicesFreelancerHomeModelArrayList=new ArrayList<>();

        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.haircut,"450","Haircut","lorem ipsum"));
        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.haircut,"450","Haircut","lorem ipsum"));
        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));

        ServicesFeturesAndCategoriesHomeAdapter servicesFeturesAndCategoriesHomeAdapter=new ServicesFeturesAndCategoriesHomeAdapter(HomePageActivity.this,servicesFeatureAndCategoriesHomeModelArrayList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(servicesFeturesAndCategoriesHomeAdapter);

        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));


        ServicesFreelancerAdapterHome servicesFreelancerAdapterHome=new ServicesFreelancerAdapterHome(HomePageActivity.this,servicesFreelancerHomeModelArrayList);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(servicesFreelancerAdapterHome);



        mDrawerLayout=findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        NavigationView navigationView=findViewById(R.id.navigation_view);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));
        }

    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:

               //startActivity(new Intent(HomePageActivity.this, HomePageActivity.class));
                break;
            case R.id.nav_chat:
                //startActivity(new Intent(HomePageActivity.this,SearchByNameOrServicesOrNearby.class));
                break;
            case R.id.nav_profile:
                 startActivity(new Intent(HomePageActivity.this,BarberInfo.class));
                break;
            case R.id.nav_booking_and_calender:
                break;
            case R.id.nav_history:
                startActivity(new Intent(HomePageActivity.this,SearchServices.class));
                break;
            case R.id.nav_choose_home_or_shop:
                startActivity(new Intent(HomePageActivity.this,SelectHomeOrShop.class));
                break;

            case R.id.nav_services_list:
                startActivity(new Intent(HomePageActivity.this,ServicesListActivity.class));
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
