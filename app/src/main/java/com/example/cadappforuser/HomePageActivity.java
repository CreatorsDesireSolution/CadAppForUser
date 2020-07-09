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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadappforuser.SeeAll.SeeAllCompany;
import com.example.cadappforuser.SeeAll.SeeAllFreelancer;
import com.example.cadappforuser.ServiceModel.AllServiceModel;
import com.example.cadappforuser.ServiceModel.NewModel;
import com.example.cadappforuser.adapter.AllServicesAdapter;
import com.example.cadappforuser.adapter.CompanyDetailsAdapter;
import com.example.cadappforuser.adapter.CompanyNewAdapter;
import com.example.cadappforuser.adapter.HomeSearchAdapter;
import com.example.cadappforuser.adapter.NewAdapter;
import com.example.cadappforuser.adapter.ServicesFeturesAndCategoriesHomeAdapter;
import com.example.cadappforuser.adapter.ServicesFreelancerAdapterHome;
import com.example.cadappforuser.companymodel.CompanyNewModel;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.example.cadappforuser.model.ServicesFeatureAndCategoriesHomeModel;
import com.example.cadappforuser.model.ServicesFreelancerHomeModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    ArrayList<CompanyDetailsModel> companyNewModels;
    Activity activity;
    Context context;
    CompanyDetailsAdapter companyDetailsAdapter;
    Act_Session act_session;
    BaseRequest baseRequest;
    RecyclerView homerecyclerview;
    RecyclerAdapter recyclerAdapter;
    HomeSearchAdapter homeSearchAdapter;
    NewAdapter newAdapter;
    CompanyNewAdapter companyNewAdapter;
    AllServicesAdapter allServicesAdapter;
    TextView txtCurrentLocation;
    TextView tv_headername,tv_headernumber;
    String URL = "https://aoneservice.net.in/salon/get-apis/company_data_api.php";
    String apiurl="https://aoneservice.net.in/salon/get-apis/distance_api.php";
    TextView seeAll,seeAllFree;
    String lastname,fullname,name,mobile;
    ImageView nav_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawable_layout);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        activity= this;
        act_session = new Act_Session(getApplicationContext());

        txtCurrentLocation=findViewById(R.id.txtLocation);
        seeAll = findViewById(R.id.seeAll);
        seeAllFree = findViewById(R.id.seeAllFree);

        final Intent intent=getIntent();
        txtCurrentLocation.setText(intent.getStringExtra("address"));

        recyclerView=findViewById(R.id.recycleView);
        recyclerView1=findViewById(R.id.recycleView1);
        recyclerView2=findViewById(R.id.recycleView2);

        NavigationView navigationView =  findViewById(R.id.navigation_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = hView.findViewById(R.id.tv_headername);
        TextView nav_mobile =hView.findViewById(R.id.tv_headenumber);
        nav_image=hView.findViewById(R.id.profileimage);
        name = act_session.firstname;
        lastname = act_session.lastname;
        mobile = act_session.mobilenumber;

        fullname = name + lastname;

        nav_user.setText(fullname);
        nav_mobile .setText(mobile);


        act_session=new Act_Session(context);
     //   act_session.getuser(act_session.profile_pic );
//        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+act_session.profile_pic).
//                resize(400, 400).centerCrop().into(nav_image);

        searchView = findViewById(R.id.searchview);

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

        Apigetdetail();


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
//               newAdapter.getFilter().filter(newText);
     //            companyNewAdapter.getFilter().filter(newText);
      //           allServicesAdapter.getFilter().filter(newText);

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

        Bundle b = getIntent().getExtras();
        final double lat= b.getDouble("lat");
        final double lng=b.getDouble("lng");

        Log.d("lat","lat"+Double.toString(lat));
        Log.d("lng","lng"+Double.toString(lng));


        Toast.makeText(activity, ""+lat+" "+lng, Toast.LENGTH_SHORT).show();

        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager3);

        final StringRequest request=new StringRequest(Request.Method.POST, "https://aoneservice.net.in/salon/get-apis/distance_api.php", new Response.Listener<String>() {
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
                                String experinace=object.getString("experience");
                                //String gender = object.getString("gender");
                                String km=object.getString("km");
                                Toast.makeText(activity, ""+km, Toast.LENGTH_SHORT).show();
                                String address = object.getString("address");
                                String aboutus=object.getString("about_yourself");
                                //Toast.makeText(context, ""+aboutus, Toast.LENGTH_SHORT).show();
                                //String item_image = object.getString("item_image");
                                //String u = "https://inventivepartner.com/petmart/images/" + item_image;
                                newModels.add(new NewModel(R.drawable.womanfacial,name,5,email,mobilenumber,lastname,address,experinace,aboutus));
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("latif",Double.toString(lat));
                map.put("longif",Double.toString(lng));
                return map;
            }
        };
            RequestQueue requestQueue = Volley.newRequestQueue(HomePageActivity.this);
            requestQueue.add(request);

        companyNewModels = new ArrayList<>();

        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager4);

        mDrawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close

        );
        NavigationView navigationView1=findViewById(R.id.navigation_view);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView1.setNavigationItemSelectedListener(this);

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
                        companyNewModels = baseRequest.getDataList(jsonArray, CompanyDetailsModel.class);

                        for (int i = 0; i < companyNewModels.size(); i++) {
                            if (companyNewModels != null) {


                                CompanyDetailsModel model = new CompanyDetailsModel();
                                model.setCompanyname(companyNewModels.get(0).getCompanyname());
                                 CompanyDetailsAdapter companyDetailsAdapter =new CompanyDetailsAdapter(HomePageActivity.this, companyNewModels,activity,act_session);
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
                 startActivity(new Intent(HomePageActivity.this,CustomerPersonalProfileActivity.class));
                break;
            case R.id.nav_history:
                //startActivity(new Intent(HomePageActivity.this,SearchServices.class));
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomePageActivity.this);
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
