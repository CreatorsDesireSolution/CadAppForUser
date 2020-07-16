package com.example.cadappforuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.cadappforuser.adapter.CompanyNewAdapter;
import com.example.cadappforuser.adapter.NewAdapter;
import com.example.cadappforuser.companymodel.CompanyNewModel;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Act_HomeForCustomerShop extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    RecyclerView recycleViewshopfree,recycleViewshopcompany,recyclerView2;
    Context context;
    androidx.appcompat.widget.SearchView  searchView;
    AllServicesAdapter allServicesAdapter;
    ArrayList<AllServiceModel> allServiceModels;
    ArrayList<NewModel> newModels;
    ArrayList<CompanyNewModel> companyNewModels;

    NewAdapter newAdapter;
    CompanyNewAdapter companyNewAdapter;
    TextView txtCurrentLocation;
    Act_Session act_session ;
    ImageView nav_image;
    String name,lastname,fullname,mobile;

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Timer timer;
    int dotscount;
    double lat,lng;
    private ImageView[] dots;
    Activity activity;
    TextView seeallfreelancer,seeallcompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__home_for_customer_shop);

        toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        context = this;
        activity = this;
        act_session = new Act_Session(getApplicationContext());

        recycleViewshopfree=findViewById(R.id.recycleViewshopfree);
        recycleViewshopcompany=findViewById(R.id.recycleViewshopcompany);
        seeallcompany = findViewById(R.id.seeAllcompany);
        seeallfreelancer = findViewById(R.id.seeAllfreelancer);


        searchView = findViewById(R.id.searchview);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.navigation_view);



        viewPager = (ViewPager)findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout)findViewById(R.id.SliderDots);

        ViewPagerCustomerAdapter viewPagerAdapter = new ViewPagerCustomerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);
        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 3000, 4000);


        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 4, 8, 4);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        seeallfreelancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SeeAllFreelancer.class);
                startActivity(intent);
            }
        });

        seeallcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SeeAllCompany.class);
                startActivity(intent);
            }
        });



        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(Html.fromHtml("<font color = #000000>" + getResources().getString(R.string.search) + "</font>"));
        LinearLayout ll = (LinearLayout)searchView.getChildAt(0);
        LinearLayout ll2 = (LinearLayout)ll.getChildAt(2);
        LinearLayout ll3 = (LinearLayout)ll2.getChildAt(1);
        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete)ll3.getChildAt(0);
        autoComplete.setHintTextColor(getResources().getColor(R.color.black));
        autoComplete.setTextColor(getResources().getColor(R.color.black));



        NavigationView navigationView1 =  findViewById(R.id.navigation_view);
        View hView =  navigationView1.getHeaderView(0);
        TextView nav_user = hView.findViewById(R.id.tv_headername);
        TextView nav_mobile =hView.findViewById(R.id.tv_headenumber);
        nav_image=hView.findViewById(R.id.UserImageProfile);
        name = act_session.firstname;
        lastname = act_session.lastname;
        mobile = act_session.mobilenumber;

        fullname = name + lastname;

        nav_user.setText(fullname);
        nav_mobile .setText(mobile);

        try{
            String img_str=act_session.profile_pic;
            Log.e("prof1","prof "+img_str);
            if (!img_str.equals("")){
                Log.d("enco1","nco"+img_str);
                Log.e("prof1","prof "+"http://aoneservice.net.in/salon/documents/"+img_str);
                Picasso.get().load(Const.URL.image_url+img_str).
                        into(nav_image);
            }
        }catch (Exception e){
            Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show();
        }



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


        Bundle b = getIntent().getExtras();
        if(b!=null){
            lat= b.getDouble("lat");
            lng=b.getDouble("lng");


            Toast.makeText(context, lat+" "+lng, Toast.LENGTH_SHORT).show();

            Log.d("lat","lat"+(lat));
            Log.d("lng","lng"+(lng));

        }





        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recycleViewshopfree.setLayoutManager(linearLayoutManager3);

        final StringRequest request=new StringRequest(Request.Method.POST, Const.URL.distance_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.e("homerespone","::"+response);
                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

                    if (sucess.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("firstname");
                            String lastname = object.getString("lastname");
                            String email = object.getString("email");
                            String mobilenumber = object.getString("mobilenumber");
                            String experinace=object.getString("experience");
                            //String gender = object.getString("gender");
                            String km=object.getString("km");
                            //   Toast.makeText(activity, ""+km, Toast.LENGTH_SHORT).show();
                            String address = object.getString("address");
                            String aboutus=object.getString("about_yourself");
                            String item_image = object.getString("profile_pic");
                            String u = "http://aoneservice.net.in/salon/documents/" + item_image;
                            newModels.add(new NewModel(u,name,5,email,mobilenumber,lastname,address,experinace,aboutus,km));
                            newAdapter=new NewAdapter(Act_HomeForCustomerShop.this,newModels);
                            recycleViewshopfree.setHasFixedSize(true);
                            recycleViewshopfree.setAdapter(newAdapter);

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
                Toast.makeText(Act_HomeForCustomerShop.this, ""+error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(Act_HomeForCustomerShop.this);
        requestQueue.add(request);

        companyNewModels = new ArrayList<>();

        final StringRequest request1=new StringRequest(Request.Method.POST, Const.URL.company_distance_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.e("seeCompany","::"+response);
                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    Log.d("sagar","sagar"+response);
                    if (sucess.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            //String category=object.getString("item_category");
                            String id = object.getString("id");
                            String name = object.getString("company_name");
                            String regnumber = object.getString("regnumber");
                            String email = object.getString("email");
                            String mobilenumber = object.getString("mobilenumber");
                            String experinace=object.getString("total_year_establishment");
                            String km=object.getString("km");
                            String lastname_=object.getString("last_name");
                            // Toast.makeText(activity, ""+km, Toast.LENGTH_SHORT).show();
                            String address = object.getString("address");
                            String aboutus=object.getString("about_company");
                            String no_of_staff=object.getString("no_of_staff");

                            String item_image = object.getString("profile_pic");
                            companyNewModels.add(new CompanyNewModel(item_image,name,5,email,mobilenumber,lastname_,address,experinace,aboutus,no_of_staff,id,km));
                            companyNewAdapter=new CompanyNewAdapter(Act_HomeForCustomerShop.this,companyNewModels);
                            LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(Act_HomeForCustomerShop.this,LinearLayoutManager.HORIZONTAL,false);
                            recycleViewshopcompany.setLayoutManager(linearLayoutManager4);
                            recycleViewshopcompany.setHasFixedSize(true);
                            recycleViewshopcompany.setAdapter(companyNewAdapter);

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
                Toast.makeText(Act_HomeForCustomerShop.this, ""+error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue1 = Volley.newRequestQueue(Act_HomeForCustomerShop.this);
        requestQueue1.add(request1);

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


    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < dotscount - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
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
