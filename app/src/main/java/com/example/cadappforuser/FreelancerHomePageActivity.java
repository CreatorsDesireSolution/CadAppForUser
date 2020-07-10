package com.example.cadappforuser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadappforuser.SeeAll.SeeAllCompany;
import com.example.cadappforuser.SeeAll.SeeAllFreelancer;
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
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
   TextView tv_headername,tv_headernumber;
   String name,mobile,fullname,lastname;
   Act_Session act_session;
   ImageView nav_image;
   Uri file1;
   Bitmap bitmap;
   String url="https://aoneservice.net.in/salon/get-apis/freelancer_data_api.php";


    SearchView searchView;
    TextView seeAllFreelancer,seeAllCompany;
    ImageView serchicon;
    SearchView et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_nav_drawable_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("home");

        act_session = new Act_Session(getApplicationContext());


        seeAllFreelancer=findViewById(R.id.seeAll);
        seeAllCompany=findViewById(R.id.seeAllFree);
        serchicon= findViewById(R.id.serchicon);
        et_search= findViewById(R.id.et_search);

        seeAllFreelancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerHomePageActivity.this, SeeAllFreelancer.class);
                startActivity(intent);
            }
        });
        seeAllCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FreelancerHomePageActivity.this, SeeAllCompany.class);
                startActivity(intent);
            }
        });


       // searchView=findViewById(R.id.freelancerSearchView);
        act_session = new Act_Session(getApplicationContext());


        tv_headername = findViewById(R.id.tv_headername);
        tv_headernumber = findViewById(R.id.tv_headenumber);

     //   retrivesharedPreferences();


//        file1 = act_session.profile_pic;
//        imageUserLogo.setImageURI(file1);


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.tv_headername);
        TextView nav_mobile =(TextView)hView.findViewById(R.id.tv_headenumber);
         nav_image =hView.findViewById(R.id.UserImageProfile);
        name = act_session.firstname;
        lastname = act_session.lastname;
        mobile = act_session.mobilenumber;
        fullname = name + "  " + lastname;
        nav_user.setText(fullname);
        nav_mobile .setText(mobile);

        try{
            String img_str=act_session.profile_pic;
            Log.d("prof","prof "+img_str);
            if (!img_str.equals("")){
                Log.d("enco","nco"+img_str);
                Log.d("prof","prof "+"http://aoneservice.net.in/salon/documents/"+img_str);
                Picasso.get().load("http://aoneservice.net.in/salon/documents/"+img_str).
                        resize(100, 100).centerCrop().into(nav_image);
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
        }

        et_search.setQueryHint(Html.fromHtml("<font color = #000000>" + getResources().getString(R.string.search) + "</font>"));
        LinearLayout ll = (LinearLayout)et_search.getChildAt(0);
        LinearLayout ll2 = (LinearLayout)ll.getChildAt(2);
        LinearLayout ll3 = (LinearLayout)ll2.getChildAt(1);
        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete)ll3.getChildAt(0);
// set the hint text color
        autoComplete.setHintTextColor(getResources().getColor(R.color.black));
// set the text color
        autoComplete.setTextColor(getResources().getColor(R.color.black));


//        et_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////              newAdapter.getFilter().filter(s);
////              companyNewAdapter.getFilter().filter(s);
//
//            }
//        });




        recyclerView=findViewById(R.id.recycleView);
        recyclerView1
                =findViewById(R.id.recycleView1);


        ad_freelancermodels = new ArrayList<>();

        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager3);


        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (sucess.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("firstname");
                            String image=object.getString("profile_pic");

                            String u = "http://aoneservice.net.in/salon/documents/" + image;

                            //Toast.makeText(FreelancerHomePageActivity.this, ""+id, Toast.LENGTH_SHORT).show();

                           //String u="222004744_1593440811.jpeg";
                            //   String lastname = object.getString("lastname");
                           // String email = object.getString("email");
                            //String mobilenumber = object.getString("mobilenumber");
                            //String experinace=object.getString("experience");
                            //String gender = object.getString("gender");
                            //String address = object.getString("address");
                            //String aboutus=object.getString("about_yourself");
                            //Toast.makeText(context, ""+aboutus, Toast.LENGTH_SHORT).show();
                            //String item_image = object.getString("item_image");
                            //String u = "https://inventivepartner.com/petmart/images/" + item_image;
                           // newModels.add(new NewModel(R.drawable.womanfacial,name,5,email,mobilenumber,lastname,address,experinace,aboutus));
                            //newAdapter=new NewAdapter(FreelancerHomePageActivity.this,newModels);
                            //recyclerView.setHasFixedSize(true);
                            //recyclerView.setAdapter(newAdapter);

                           // newModels.add(new NewModel(R.drawable.womanfacial,name,5,email,mobilenumber,lastname,address,experinace,aboutus));
                            ad_freelancermodels.add(new Ad_freelancermodel(id,u,name,5));
                            ad_freelancer=new Ad_Freelancer(FreelancerHomePageActivity.this,ad_freelancermodels);
                            // ad_freelancer=new Ad_Freelancer(FreelancerHomePageActivity.this,ad_freelancermodels);

                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(ad_freelancer);

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
                Toast.makeText(FreelancerHomePageActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

        ad_companymodels = new ArrayList<>();

        StringRequest request1=new StringRequest(Request.Method.POST, "https://aoneservice.net.in/salon/get-apis/company_data_api.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                   // Toast.makeText(FreelancerHomePageActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    if (sucess.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("companyname");
                            String image=object.getString("profile_pic");

                            String u = "http://aoneservice.net.in/salon/documents/" + image;

                            //Toast.makeText(FreelancerHomePageActivity.this, ""+id, Toast.LENGTH_SHORT).show();

                            //String u="222004744_1593440811.jpeg";
                            //   String lastname = object.getString("lastname");
                            // String email = object.getString("email");
                            //String mobilenumber = object.getString("mobilenumber");
                            //String experinace=object.getString("experience");
                            //String gender = object.getString("gender");
                            //String address = object.getString("address");
                            //String aboutus=object.getString("about_yourself");
                            //Toast.makeText(context, ""+aboutus, Toast.LENGTH_SHORT).show();
                            //String item_image = object.getString("item_image");
                            //String u = "https://inventivepartner.com/petmart/images/" + item_image;
                            // newModels.add(new NewModel(R.drawable.womanfacial,name,5,email,mobilenumber,lastname,address,experinace,aboutus));
                            //newAdapter=new NewAdapter(FreelancerHomePageActivity.this,newModels);
                            //recyclerView.setHasFixedSize(true);
                            //recyclerView.setAdapter(newAdapter);

                            // newModels.add(new NewModel(R.drawable.womanfacial,name,5,email,mobilenumber,lastname,address,experinace,aboutus));
                            ad_companymodels.add(new Ad_Companymodel(id,u,name,5));
                            ad_company=new Ad_Company(FreelancerHomePageActivity.this,ad_companymodels);

                            LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(FreelancerHomePageActivity.this,LinearLayoutManager.HORIZONTAL,false);
                            recyclerView1.setLayoutManager(linearLayoutManager4);
                            recyclerView1.setHasFixedSize(true);
                            recyclerView1.setAdapter(ad_company);

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
                Toast.makeText(FreelancerHomePageActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue1= Volley.newRequestQueue(this);
        requestQueue1.add(request1);




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
   /* private void retrivesharedPreferences()
    {
        SharedPreferences shared = getSharedPreferences("MyPref", MODE_PRIVATE);
        String photo = shared.getString("profile_pic", "photo");
        assert photo != null;
        if(!photo.equals("photo"))
        {
            byte[] b = Base64.decode(photo, Base64.DEFAULT);
            InputStream is = new ByteArrayInputStream(b);
            bitmap = BitmapFactory.decodeStream(is);
            //nav_image.setImageBitmap(bitmap);
            Picasso.get().load("http://aoneservice.net.in/salon/documents/"+photo)
                    .resize(400, 400).centerCrop().into(nav_image);
        }

    }*/

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
            case R.id.nav_add_services:
                startActivity(new Intent(FreelancerHomePageActivity.this, AddServiceSelectGender.class));
                break;
            case R.id.nav_accepted_location:
                startActivity(new Intent(FreelancerHomePageActivity.this,FreelancerServicesProvide.class));
                break;
            case R.id.nav_logout:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FreelancerHomePageActivity.this);
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
            case  R.id.nav_profile:
                startActivity(new Intent(FreelancerHomePageActivity.this,FreelancerPersonalProfileActivity.class));
                break;

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
