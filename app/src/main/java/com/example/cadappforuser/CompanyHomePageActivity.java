package com.example.cadappforuser;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.companyadapter.CompanyServicesFeturesAndCategoriesHomeAdapter;
import com.example.cadappforuser.companyadapter.CompanyServicesFreelancerAdapterHome;
import com.example.cadappforuser.companymodel.CompanyServicesFeatureAndCategoriesHomeModel;
import com.example.cadappforuser.companymodel.CompanyServicesFreelancerHomeModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class CompanyHomePageActivity extends AppCompatActivity  implements  NavigationView
        .OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    RecyclerView recyclerView,recyclerView1;
    ArrayList<CompanyServicesFeatureAndCategoriesHomeModel> companyServicesFeatureAndCategoriesHomeModelArrayList;

    ArrayList<CompanyServicesFreelancerHomeModel> companyServicesFreelancerHomeModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comapny_nav_drawable_layout);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recycleView);
        recyclerView1=findViewById(R.id.recycleView1);

        companyServicesFeatureAndCategoriesHomeModelArrayList =new ArrayList<>();
        companyServicesFreelancerHomeModelArrayList =new ArrayList<>();

        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.haircut,"450","Haircut","lorem ipsum"));
        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.haircut,"450","Haircut","lorem ipsum"));
        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));

        CompanyServicesFeturesAndCategoriesHomeAdapter companyServicesFeturesAndCategoriesHomeAdapter =new CompanyServicesFeturesAndCategoriesHomeAdapter(CompanyHomePageActivity.this, companyServicesFeatureAndCategoriesHomeModelArrayList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(companyServicesFeturesAndCategoriesHomeAdapter);

        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));


        CompanyServicesFreelancerAdapterHome companyServicesFreelancerAdapterHome =new CompanyServicesFreelancerAdapterHome(CompanyHomePageActivity.this, companyServicesFreelancerHomeModelArrayList);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(companyServicesFreelancerAdapterHome);



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
                startActivity(new Intent(CompanyHomePageActivity.this,BackgoundOfCompanyActivity.class));
                break;
            case R.id.nav_profile:
                 startActivity(new Intent(CompanyHomePageActivity.this,CompanyPersonalProfile.class));
                break;
                     case R.id.nav_set_avalibilty:
               // startActivity(new Intent(HomePageActivity.this,SetAvalibiltyCustomActivity.class));
                break;
            case R.id.nav_history:
                //startActivity(new Intent(HomePageActivity.this,SearchServices.class));
                break;
            case R.id.nav_add_staff:
                startActivity(new Intent(CompanyHomePageActivity.this,CompanySelectGenderActivity.class));
                break;
            case R.id.nav_add_certificate:
                startActivity(new Intent(CompanyHomePageActivity.this,CertificationActivity.class));
                break;
            case R.id.nav_term_and_condition:
                startActivity(new Intent(CompanyHomePageActivity.this,TermAndCondition.class));
                break;

            case R.id.nav_company_services_provided_and_cancelataion_policy:
               startActivity(new Intent(CompanyHomePageActivity.this,CompanyServicesProvidedAndCancelationPolicy.class));
               break;
            case R.id.nav_company_services_offered:
                startActivity(new Intent(CompanyHomePageActivity.this,FreelancerServicesOffered.class));
                 break;
            case R.id.nav_company_add_services_manually:
                startActivity(new Intent(CompanyHomePageActivity.this,CompanyAddServicesActivity.class));
                break;
            case R.id.nav_show_staff_member:
                startActivity(new Intent(CompanyHomePageActivity.this,ChoiceStaffOrComapnyOwner.class));
                break;
            case R.id.nav_logout:
                startActivity(new Intent(CompanyHomePageActivity.this,Act_Logout.class));
                break;


        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
