package com.example.cadappforuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cadappforuser.ServiceModel.AllServiceModel;
import com.example.cadappforuser.ServiceModel.NewModel;
import com.example.cadappforuser.adapter.AllServicesAdapter;
import com.example.cadappforuser.adapter.CompanyNewAdapter;
import com.example.cadappforuser.adapter.HomeSearchAdapter;
import com.example.cadappforuser.adapter.NewAdapter;
import com.example.cadappforuser.adapter.ServicesFeturesAndCategoriesHomeAdapter;
import com.example.cadappforuser.adapter.ServicesFreelancerAdapterHome;
import com.example.cadappforuser.companymodel.CompanyNewModel;
import com.example.cadappforuser.model.ServicesFeatureAndCategoriesHomeModel;
import com.example.cadappforuser.model.ServicesFreelancerHomeModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity  implements  NavigationView
        .OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    RecyclerView recyclerView,recyclerView1,recyclerView2;
    ImageView women,man;
androidx.appcompat.widget.SearchView  searchView;
    float val = Float.parseFloat("4");
    String freelancer;
    List<String> list;
    ArrayAdapter<String> adapter;
    ArrayList<ServicesFeatureAndCategoriesHomeModel> servicesFeatureAndCategoriesHomeModelArrayList;

    ArrayList<ServicesFreelancerHomeModel> servicesFreelancerHomeModelArrayList;
    ArrayList<AllServiceModel> allServiceModels;

    ArrayList<NewModel> newModels;
    ArrayList<CompanyNewModel> companyNewModels;


    RecyclerView homerecyclerview;
    RecyclerAdapter recyclerAdapter;
    HomeSearchAdapter homeSearchAdapter;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawable_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        recyclerView=findViewById(R.id.recycleView);
        recyclerView1=findViewById(R.id.recycleView1);
        recyclerView2= findViewById(R.id.recycleView2);

       // homerecyclerview = findViewById(R.id.homerecyclerview);
      //  listView = findViewById(R.id.mylist);
        searchView = findViewById(R.id.searchview);

//        list = new ArrayList<>();
//        list.add("Freelancer");
//        list.add("Company1");
//        list.add("Company2");
//        list.add("Men Freelancer");
//        list.add("Services");
//        list.add("Company1");
//        list.add("Freelancer");

//
//        homeSearchAdapter = new HomeSearchAdapter(list,context
//        );
//        homerecyclerview.setLayoutManager(new LinearLayoutManager(this));
//        homerecyclerview.setAdapter(homeSearchAdapter);
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        homerecyclerview.addItemDecoration(dividerItemDecoration);
//
//        searchView.setQueryHint(Html.fromHtml("<font color = #ffffff>" + getResources().getString(R.string.search) + "</font>"));
//
//
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                homeSearchAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });

//
//        adapter = new ArrayAdapter<>(this,R.layout.default_list,list);
//        listView.setAdapter(adapter);
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });




        servicesFeatureAndCategoriesHomeModelArrayList=new ArrayList<>();
        servicesFreelancerHomeModelArrayList=new ArrayList<>();
        allServiceModels = new ArrayList<>();

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
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(servicesFeturesAndCategoriesHomeAdapter);

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
//        recyclerView1.setLayoutManager(layoutManager1);
//        recyclerView1.setHasFixedSize(true);
//        recyclerView1.setAdapter(servicesFeturesAndCategoriesHomeAdapter);



        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));


        AllServicesAdapter allServicesAdapter=new AllServicesAdapter(HomePageActivity.this,allServiceModels);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setHasFixedSize(true);



        newModels = new ArrayList<>();
        newModels.add(new NewModel(R.drawable.womanfacial,"Man Freelancer",val));
        newModels.add(new NewModel(R.drawable.womanfacial,"Man Freelancer",5));
        newModels.add(new NewModel(R.drawable.saloon2,"Man Freelancer",5));
        newModels.add(new NewModel(R.drawable.womanfacial,"Man Freelancer",5));

        newModels.add(new NewModel(R.drawable.womanfacial,"Man Freelancer",5));
        newModels.add(new NewModel(R.drawable.saloon1,"Women",5));
        newModels.add(new NewModel(R.drawable.womanfacial,"Women Freelancer",5));

        NewAdapter newAdapter=new NewAdapter(HomePageActivity.this,newModels);
        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(linearLayoutManager3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(newAdapter);

        companyNewModels = new ArrayList<>();

        companyNewModels.add(new CompanyNewModel(R.drawable.mansaloon,"Company1",val));
        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company1",5));
        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company1",5));
        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company1",5));
        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company1",5));
        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company1",5));
        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company1",5));

        CompanyNewAdapter companyNewAdapter=new CompanyNewAdapter(HomePageActivity.this,companyNewModels);
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView1.setLayoutManager(linearLayoutManager4);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(companyNewAdapter);




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

               startActivity(new Intent(HomePageActivity.this, HomePageActivity.class));
                break;
            case R.id.nav_chat:
                //startActivity(new Intent(HomePageActivity.this,SearchByNameOrServicesOrNearby.class));
                break;
            case R.id.nav_profile:
                 startActivity(new Intent(HomePageActivity.this,Act_CustomerProfileEdit.class));
                break;
            case R.id.nav_history:
                startActivity(new Intent(HomePageActivity.this,SearchServices.class));
                break;
            case R.id.nav_choose_home_or_shop:
                startActivity(new Intent(HomePageActivity.this,SelectHomeOrShop.class));
                break;

            case R.id.nav_tc:
                startActivity(new Intent(HomePageActivity.this,TermAndCondition.class));
                break;
            case R.id.nav_mycart:
                startActivity(new Intent(HomePageActivity.this,CartActivity.class));
                break;

            case  R.id.nav_logout:
                startActivity(new Intent(HomePageActivity.this,Act_Logout.class));
                break;





        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
