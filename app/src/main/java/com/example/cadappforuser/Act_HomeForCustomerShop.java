package com.example.cadappforuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cadappforuser.ServiceModel.AllServiceModel;
import com.example.cadappforuser.ServiceModel.NewModel;
import com.example.cadappforuser.adapter.AllServicesAdapter;
import com.example.cadappforuser.adapter.CompanyNewAdapter;
import com.example.cadappforuser.adapter.NewAdapter;
import com.example.cadappforuser.companymodel.CompanyNewModel;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Act_HomeForCustomerShop extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    RecyclerView recyclerView,recyclerView1,recyclerView2;
    Context context;
    androidx.appcompat.widget.SearchView  searchView;
    AllServicesAdapter allServicesAdapter;
    ArrayList<AllServiceModel> allServiceModels;
    ArrayList<NewModel> newModels;
    ArrayList<CompanyDetailsModel> companyNewModels;

    NewAdapter newAdapter;
    CompanyNewAdapter companyNewAdapter;
    TextView txtCurrentLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__home_for_customer_shop);

        toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        context = this;

        recyclerView=findViewById(R.id.recycleView);
        recyclerView1=findViewById(R.id.recycleView1);

        searchView = findViewById(R.id.searchview);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.navigation_view);

        txtCurrentLocation=findViewById(R.id.txtLocation);
        Intent intent=getIntent();
        txtCurrentLocation.setText(intent.getStringExtra("address"));

        searchView.setIconifiedByDefault(false);
      //  searchView.setQueryHint("Search");
        searchView.setQueryHint(Html.fromHtml("<font color = #000000>" + getResources().getString(R.string.search) + "</font>"));
        LinearLayout ll = (LinearLayout)searchView.getChildAt(0);
        LinearLayout ll2 = (LinearLayout)ll.getChildAt(2);
        LinearLayout ll3 = (LinearLayout)ll2.getChildAt(1);
        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete)ll3.getChildAt(0);
// set the hint text color
        autoComplete.setHintTextColor(getResources().getColor(R.color.black));
// set the text color
        autoComplete.setTextColor(getResources().getColor(R.color.black));



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newAdapter.getFilter().filter(newText);
                companyNewAdapter.getFilter().filter(newText);

                return true;
            }
        });



        newModels = new ArrayList<>();
        //newModels.add(new NewModel(R.drawable.womanfacial,"Man Freelancer",5));
        //newModels.add(new NewModel(R.drawable.womanfacial,"Man Freelancer",5));
        //newModels.add(new NewModel(R.drawable.saloon2,"Man Freelancer",5));
       // newModels.add(new NewModel(R.drawable.womanfacial,"Man Freelancer",5));
        //newModels.add(new NewModel(R.drawable.womanfacial,"Man Freelancer",5));
        //newModels.add(new NewModel(R.drawable.saloon1,"Women",5));
        //newModels.add(new NewModel(R.drawable.womanfacial,"Women Freelancer",5));

        newAdapter=new NewAdapter(Act_HomeForCustomerShop.this,newModels);
        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(newAdapter);


        companyNewModels = new ArrayList<>();

//        companyNewModels.add(new CompanyNewModel(R.drawable.mansaloon,"Company1",5));
//        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company2",5));
//        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company3",5));
//        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company4",5));
//        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company5",5));
//        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company6",5));
//        companyNewModels.add(new CompanyNewModel(R.drawable.salooncompany,"Company7",5));

      //  companyNewAdapter=new CompanyNewAdapter(Act_HomeForCustomerShop.this,companyNewModels);
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager4);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(companyNewAdapter);



        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close

        );
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

//        if(Build.VERSION.SDK_INT>=21){
//            Window window=this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));
//        }


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

                startActivity(new Intent(Act_HomeForCustomerShop.this, HomePageActivity.class));
                break;
            case R.id.nav_chat:
                //startActivity(new Intent(HomePageActivity.this,SearchByNameOrServicesOrNearby.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(Act_HomeForCustomerShop.this,CustomerPersonalProfileActivity.class));
                break;
            case R.id.nav_history:
                startActivity(new Intent(Act_HomeForCustomerShop.this,SearchServices.class));
                break;
            case R.id.nav_choose_home_or_shop:
                startActivity(new Intent(Act_HomeForCustomerShop.this,SelectHomeOrShop.class));
                break;

            case R.id.nav_tc:
                startActivity(new Intent(Act_HomeForCustomerShop.this,TermAndCondition.class));
                break;
            case R.id.nav_mycart:
                startActivity(new Intent(Act_HomeForCustomerShop.this,CartActivity.class));
                break;

            case  R.id.nav_logout:
                startActivity(new Intent(Act_HomeForCustomerShop.this,Act_Logout.class));
                break;

            case R.id.nav_add_fev:
                startActivity(new Intent(Act_HomeForCustomerShop.this,Act_AddToFev.class));
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
