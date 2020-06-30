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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

import org.json.JSONArray;
import org.json.JSONObject;

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
    NewAdapter newAdapter;
    CompanyNewAdapter companyNewAdapter;
    AllServicesAdapter allServicesAdapter;
    TextView txtCurrentLocation;
    String apiurl="https://aoneservice.net.in/salon/get-apis/freelancer_data_api.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawable_layout);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        txtCurrentLocation=findViewById(R.id.txtLocation);

        Intent intent=getIntent();
        txtCurrentLocation.setText(intent.getStringExtra("address"));

        recyclerView=findViewById(R.id.recycleView);
        recyclerView1=findViewById(R.id.recycleView1);
        recyclerView2= findViewById(R.id.recycleView2);

       // homerecyclerview = findViewById(R.id.homerecyclerview);
      //  listView = findViewById(R.id.mylist);
        searchView = findViewById(R.id.searchview);

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
                 allServicesAdapter.getFilter().filter(newText);

                return true;
            }
        });

        servicesFeatureAndCategoriesHomeModelArrayList=new ArrayList<>();
        servicesFreelancerHomeModelArrayList=new ArrayList<>();
        allServiceModels = new ArrayList<>();


        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Massage","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Hair Spa","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Face Massage","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Classic Massage","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Threading","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Beard Cut","lorem ipsum"));
        allServiceModels.add(new AllServiceModel(R.drawable.facial,"450","Haircut","lorem ipsum"));


        allServicesAdapter=new AllServicesAdapter(HomePageActivity.this,allServiceModels);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setAdapter(allServicesAdapter);

        newModels = new ArrayList<>();

        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager3);

        StringRequest request=new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (sucess.equals("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                //String category=object.getString("item_category");
                                String id = object.getString("id");
                                String name = object.getString("firstname");
                                String lastname = object.getString("lastname");
                                String email = object.getString("email");
                                String mobilenumber = object.getString("mobilenumber");
                                String gender = object.getString("gender");
                                String address = object.getString("address");
                                //String item_image = object.getString("item_image");
                                //String u = "https://inventivepartner.com/petmart/images/" + item_image;
                                newModels.add(new NewModel(R.drawable.womanfacial,name,5));
                                newAdapter=new NewAdapter(HomePageActivity.this,newModels);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(newAdapter);

                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomePageActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);


        companyNewModels = new ArrayList<>();

        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager4);


        StringRequest request1=new StringRequest(Request.Method.POST, "https://aoneservice.net.in/salon/get-apis/company_data_api.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (sucess.equals("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            //String category=object.getString("item_category");
                            String id = object.getString("id");
                            String nameC = object.getString("companyname");
                            //String lastname = object.getString("lastname");
                            //String email = object.getString("email");
                            //String mobilenumber = object.getString("mobilenumber");
                            //String gender = object.getString("gender");
                           /// String address = object.getString("address");
                            //String item_image = object.getString("item_image");
                            //String u = "https://inventivepartner.com/petmart/images/" + item_image;
                            companyNewModels.add(new CompanyNewModel(R.drawable.mansaloon,nameC,3));

                            companyNewAdapter=new CompanyNewAdapter(HomePageActivity.this,companyNewModels);

                            recyclerView1.setHasFixedSize(true);
                            recyclerView1.setAdapter(companyNewAdapter);

                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomePageActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue1= Volley.newRequestQueue(this);
        requestQueue1.add(request1);

         //companyNewAdapter=new CompanyNewAdapter(HomePageActivity.this,companyNewModels);
        //LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //recyclerView1.setLayoutManager(linearLayoutManager4);
        //recyclerView1.setHasFixedSize(true);
        //recyclerView1.setAdapter(companyNewAdapter);

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

            case R.id.nav_add_fev:
                startActivity(new Intent(HomePageActivity.this,Act_AddToFev.class));
                break;
            case  R.id.nav_aboutus:
                startActivity(new Intent(HomePageActivity.this,Act_AboutUS.class));
                break;
            case  R.id.nav_contactus:
                startActivity(new Intent(HomePageActivity.this,Act_ContactUs.class));
                break;







        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
