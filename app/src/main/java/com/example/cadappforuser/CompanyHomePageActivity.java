package com.example.cadappforuser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.SeeAll.SeeAllCompany;
import com.example.cadappforuser.SeeAll.SeeAllFreelancer;
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
import com.squareup.picasso.Picasso;

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
    String name,mobile;
    TextView seeAll,seeAllFree;
    ImageView imgview;
    String encodeimage;
    SearchView et_search;

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
        seeAll = findViewById(R.id.seeAll);
        seeAllFree = findViewById(R.id.seeAllFree);
        et_search= findViewById(R.id.et_search);

        Apigetdetail();
        ApiGetFreelancerDetail();

        companyServicesFeatureAndCategoriesHomeModelArrayList =new ArrayList<>();
        companyServicesFreelancerHomeModelArrayList =new ArrayList<>();


        mDrawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );


        et_search.setQueryHint(Html.fromHtml("<font color = #000000>" + getResources().getString(R.string.search) + "</font>"));
        LinearLayout ll = (LinearLayout)et_search.getChildAt(0);
        LinearLayout ll2 = (LinearLayout)ll.getChildAt(2);
        LinearLayout ll3 = (LinearLayout)ll2.getChildAt(1);
        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete)ll3.getChildAt(0);
// set the hint text color
        autoComplete.setHintTextColor(getResources().getColor(R.color.black));
// set the text color
        autoComplete.setTextColor(getResources().getColor(R.color.black));

        NavigationView navigationView=findViewById(R.id.navigation_view);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView navigationView1 = (NavigationView) findViewById(R.id.navigation_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.tv_headername);
        TextView nav_mobile =(TextView)hView.findViewById(R.id.tv_headenumber);
        imgview  = (ImageView)hView.findViewById(R.id.profileimage) ;
        name = act_session.companyname;
        mobile = act_session.mobilenumber;

       // fullname = name + lastname;

        nav_user.setText(name);
        nav_mobile .setText(mobile);

        Toast.makeText(activity, name, Toast.LENGTH_SHORT).show();

        try{
            String img_str=act_session.profile_pic;
            Log.d("prof","prof "+img_str);
            if (!img_str.equals("")){
                Log.d("enco","nco"+img_str);
                Log.d("prof","prof "+"http://aoneservice.net.in/salon/documents/"+img_str);
                Picasso.get().load("http://aoneservice.net.in/salon/documents/"+img_str).
                        resize(100, 100).centerCrop().into(imgview);
            }
        }catch (Exception e){
            Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show();
        }

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), SeeAllFreelancer.class);
                startActivity(intent1);
            }
        });

        seeAllFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), SeeAllCompany.class);
                startActivity(intent1);
            }
        });

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
                 startActivity(new Intent(CompanyHomePageActivity.this,CompanyPersonalProfileActivity.class));
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

            case R.id.nav_company_add_services_manually:
                startActivity(new Intent(CompanyHomePageActivity.this,SelectCompanyServicegender.class));
                break;
            case R.id.nav_show_staff_member:
                startActivity(new Intent(CompanyHomePageActivity.this,ChoiceStaffOrComapnyOwner.class));
                break;
            case R.id.nav_logout:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompanyHomePageActivity.this);
                alertDialogBuilder.setMessage("Are you sure,You wanted to Logout");
                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        act_session.clearPreferences(getApplicationContext());
                        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;


        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
