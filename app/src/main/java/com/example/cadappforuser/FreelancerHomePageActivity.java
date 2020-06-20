package com.example.cadappforuser;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.ServiceModel.NewModel;
import com.example.cadappforuser.adapter.Ad_Company;
import com.example.cadappforuser.adapter.Ad_Freelancer;
import com.example.cadappforuser.adapter.CompanyNewAdapter;
import com.example.cadappforuser.adapter.NewAdapter;
import com.example.cadappforuser.companymodel.CompanyNewModel;
import com.example.cadappforuser.freelanceradapter.ServicesFeturesAndCategoriesHomeAdapter;
import com.example.cadappforuser.freelanceradapter.ServicesFreelancerAdapterHome;
import com.example.cadappforuser.model.Ad_Companymodel;
import com.example.cadappforuser.model.Ad_freelancermodel;
import com.example.cadappforuser.modelfreelancer.ServicesFeatureAndCategoriesHomeModel;
import com.example.cadappforuser.modelfreelancer.ServicesFreelancerHomeModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FreelancerHomePageActivity extends AppCompatActivity  implements  NavigationView
        .OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    RecyclerView recyclerView,recyclerView1;
    ArrayList<ServicesFeatureAndCategoriesHomeModel> servicesFeatureAndCategoriesHomeModelArrayList;

    ArrayList<ServicesFreelancerHomeModel> servicesFreelancerHomeModelArrayList;

    ArrayList<NewModel> newModels;
    ArrayList<CompanyNewModel> companyNewModels;
    NewAdapter newAdapter;
    CompanyNewAdapter companyNewAdapter;
   ArrayList<Ad_Companymodel> ad_companymodels;
   ArrayList<Ad_freelancermodel> ad_freelancermodels;
   Ad_Freelancer ad_freelancer;
   Ad_Company ad_company;



    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_nav_drawable_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView=findViewById(R.id.freelancerSearchView);

        searchView.setQueryHint(Html.fromHtml("<font color = #000000>" + getResources().getString(R.string.search) + "</font>"));
        LinearLayout ll = (LinearLayout)searchView.getChildAt(0);
        LinearLayout ll2 = (LinearLayout)ll.getChildAt(2);
        LinearLayout ll3 = (LinearLayout)ll2.getChildAt(1);
        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete)ll3.getChildAt(0);
// set the hint text color
        autoComplete.setHintTextColor(getResources().getColor(R.color.black));
// set the text color
        autoComplete.setTextColor(getResources().getColor(R.color.black));

        recyclerView=findViewById(R.id.recycleView);
        recyclerView1=findViewById(R.id.recycleView1);

//        servicesFeatureAndCategoriesHomeModelArrayList=new ArrayList<>();
//        servicesFreelancerHomeModelArrayList=new ArrayList<>();
//
//        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.haircut,"450","Haircut","lorem ipsum"));
//        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
//        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
//        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.haircut,"450","Haircut","lorem ipsum"));
//        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
//        servicesFeatureAndCategoriesHomeModelArrayList.add(new ServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//
//        ServicesFeturesAndCategoriesHomeAdapter servicesFeturesAndCategoriesHomeAdapter=new ServicesFeturesAndCategoriesHomeAdapter(FreelancerHomePageActivity.this,servicesFeatureAndCategoriesHomeModelArrayList);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(servicesFeturesAndCategoriesHomeAdapter);
//
//        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        servicesFreelancerHomeModelArrayList.add(new ServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//
//
//       com.example.cadappforuser.freelanceradapter.ServicesFreelancerAdapterHome servicesFreelancerAdapterHome=new ServicesFreelancerAdapterHome(FreelancerHomePageActivity.this,servicesFreelancerHomeModelArrayList);
//        LinearLayoutManager layoutManager1=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView1.setLayoutManager(layoutManager1);
//        recyclerView1.setHasFixedSize(true);
//        recyclerView1.setAdapter(servicesFreelancerAdapterHome);



        ad_freelancermodels = new ArrayList<>();
        ad_freelancermodels.add(new Ad_freelancermodel(R.drawable.womanfacial,"Man Freelancer",5));
        ad_freelancermodels.add(new Ad_freelancermodel(R.drawable.womanfacial,"Man Freelancer",5));
        ad_freelancermodels.add(new Ad_freelancermodel(R.drawable.saloon2,"Man Freelancer",5));
        ad_freelancermodels.add(new Ad_freelancermodel(R.drawable.womanfacial,"Man Freelancer",5));
        ad_freelancermodels.add(new Ad_freelancermodel(R.drawable.womanfacial,"Man Freelancer",5));
        ad_freelancermodels.add(new Ad_freelancermodel(R.drawable.saloon1,"Women",5));
        ad_freelancermodels.add(new Ad_freelancermodel(R.drawable.womanfacial,"Women Freelancer",5));

        ad_freelancer=new Ad_Freelancer(FreelancerHomePageActivity.this,ad_freelancermodels);
        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(ad_freelancer);

        


        ad_companymodels = new ArrayList<>();

        ad_companymodels.add(new Ad_Companymodel(R.drawable.mansaloon,"Company1",5));
        ad_companymodels.add(new Ad_Companymodel(R.drawable.salooncompany,"Company2",5));
        ad_companymodels.add(new Ad_Companymodel(R.drawable.salooncompany,"Company3",5));
        ad_companymodels.add(new Ad_Companymodel(R.drawable.salooncompany,"Company4",5));
        ad_companymodels.add(new Ad_Companymodel(R.drawable.salooncompany,"Company5",5));
        ad_companymodels.add(new Ad_Companymodel(R.drawable.salooncompany,"Company6",5));
        ad_companymodels.add(new Ad_Companymodel(R.drawable.salooncompany,"Company7",5));

        ad_company=new Ad_Company(FreelancerHomePageActivity.this,ad_companymodels);
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager4);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(ad_company);





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
            case R.id.nav_setbackground:
                startActivity(new Intent(FreelancerHomePageActivity.this,BackgoundOfFreelancerActivity.class));
                break;
            case R.id.nav_add_certificate:
               startActivity(new Intent(FreelancerHomePageActivity.this, FreelancerCertificationActivity.class));

                break;
            case R.id.nav_term_and_condition:
                startActivity(new Intent(FreelancerHomePageActivity.this,FreelancerTermAndCondition.class));
                break;
            case R.id.nav_services_offered:
                startActivity(new Intent(FreelancerHomePageActivity.this,FreelancerServicesOffered.class));
                 break;
            case R.id.nav_add_services:
                startActivity(new Intent(FreelancerHomePageActivity.this, AddServiceSelectGender.class));
                break;
            case R.id.nav_accepted_location:
                startActivity(new Intent(FreelancerHomePageActivity.this,FreelancerServicesProvide.class));
                break;
            case  R.id.nav_profile:
                startActivity(new Intent(FreelancerHomePageActivity.this,FreelancerPersonalProfile.class));


        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
