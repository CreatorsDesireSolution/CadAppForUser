package com.example.cadappforuser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.adapter.CompanyAddServiceAdapater;
import com.example.cadappforuser.adapter.CompanyDetailsAdapter;
import com.example.cadappforuser.adapter.FreelancerDetailAdapter;
import com.example.cadappforuser.companyadapter.CompanyServicesFeturesAndCategoriesHomeAdapter;
import com.example.cadappforuser.companyadapter.CompanyServicesFreelancerAdapterHome;
import com.example.cadappforuser.companymodel.CompanyServicesFeatureAndCategoriesHomeModel;
import com.example.cadappforuser.companymodel.CompanyServicesFreelancerHomeModel;
import com.example.cadappforuser.model.CompanyAddServiceModel;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.example.cadappforuser.model.FreelancerDetailsModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompanyHomePageActivity extends AppCompatActivity  implements  NavigationView
        .OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    RecyclerView recyclerView,recyclerView1;
    ArrayList<CompanyServicesFeatureAndCategoriesHomeModel> companyServicesFeatureAndCategoriesHomeModelArrayList;

    ArrayList<CompanyServicesFreelancerHomeModel> companyServicesFreelancerHomeModelArrayList;
    ArrayList<CompanyDetailsModel> companyDetailsModels= new ArrayList<>();
    ArrayList<FreelancerDetailsModel> freelancerDetailsModels = new ArrayList<>();
    Activity activity;
    Context context;
    CompanyDetailsAdapter companyDetailsAdapter;
    Act_Session act_session;
    BaseRequest baseRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comapny_nav_drawable_layout);

        context = this;
        activity= this;
        act_session = new Act_Session(getApplicationContext());

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recycleView);
        recyclerView1=findViewById(R.id.recycleView1);

        Apigetdetail();
        ApiGetFreelancerDetail();

        companyServicesFeatureAndCategoriesHomeModelArrayList =new ArrayList<>();
        companyServicesFreelancerHomeModelArrayList =new ArrayList<>();
//
//        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.haircut,"450","Haircut","lorem ipsum"));
//        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
//        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
//        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.haircut,"450","Haircut","lorem ipsum"));
//        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.hairspa,"450","Haircut","lorem ipsum"));
//        companyServicesFeatureAndCategoriesHomeModelArrayList.add(new CompanyServicesFeatureAndCategoriesHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//
//        CompanyServicesFeturesAndCategoriesHomeAdapter companyServicesFeturesAndCategoriesHomeAdapter =new CompanyServicesFeturesAndCategoriesHomeAdapter(CompanyHomePageActivity.this, companyServicesFeatureAndCategoriesHomeModelArrayList);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(companyServicesFeturesAndCategoriesHomeAdapter);
//
//        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//        companyServicesFreelancerHomeModelArrayList.add(new CompanyServicesFreelancerHomeModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
//





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

    private void Apigetdetail() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);

                    if (!jsonObject.getString("message").equals("Failed")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        companyDetailsModels = baseRequest.getDataList(jsonArray, CompanyDetailsModel.class);

                        for (int i = 0; i < companyDetailsModels.size(); i++) {
                            if (companyDetailsModels != null) {


                                CompanyDetailsModel model = new CompanyDetailsModel();
                                model.setCompanyname(companyDetailsModels.get(0).getCompanyname());

                               // companyAddServiceModels2.add(model);
                                CompanyDetailsAdapter companyDetailsAdapter =new CompanyDetailsAdapter(CompanyHomePageActivity.this, companyDetailsModels,activity,act_session);
                                LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
                                recyclerView1.setLayoutManager(layoutManager1);
                                recyclerView1.setHasFixedSize(true);
                                recyclerView1.setAdapter(companyDetailsAdapter);



                            } else {
                                Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override

            public void onNetworkFailure(int requestCode, String message) {

            }
        });
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/company_dashboarddata_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }


    private void ApiGetFreelancerDetail() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);

                    if (!jsonObject.getString("message").equals("Failed")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        freelancerDetailsModels = baseRequest.getDataList(jsonArray, FreelancerDetailsModel.class);

                        for (int i = 0; i < freelancerDetailsModels.size(); i++) {
                            if (freelancerDetailsModels != null) {


                                CompanyDetailsModel model = new CompanyDetailsModel();
                                model.setCompanyname(freelancerDetailsModels.get(0).getFirstname());

                                // companyAddServiceModels2.add(model);
                                FreelancerDetailAdapter companyDetailsAdapter =new FreelancerDetailAdapter(CompanyHomePageActivity.this, freelancerDetailsModels,activity,act_session);
                                LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
                                recyclerView.setLayoutManager(layoutManager1);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(companyDetailsAdapter);



                            } else {
                                Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override

            public void onNetworkFailure(int requestCode, String message) {

            }
        });
        String remainingUrl2 = "https://aoneservice.net.in/salon/get-apis/freelancer_dashboarddata_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
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
                startActivity(new Intent(CompanyHomePageActivity.this,CompanyShowServices.class));
                 break;
            case R.id.nav_company_add_services_manually:
                startActivity(new Intent(CompanyHomePageActivity.this,SelectCompanyServicegender.class));
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
