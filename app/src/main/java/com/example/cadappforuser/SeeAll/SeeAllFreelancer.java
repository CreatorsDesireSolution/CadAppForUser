package com.example.cadappforuser.SeeAll;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.Const;
import com.example.cadappforuser.R;
import com.example.cadappforuser.adapter.SeeAllFreelancerAdapter;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.example.cadappforuser.model.FreelancerDetailsModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SeeAllFreelancer extends AppCompatActivity {

    RecyclerView recycleAll;
    SeeAllFreelancerAdapter seeAllFreelancerAdapter;
    BaseRequest baseRequest;
    Act_Session act_session; Context context;
    ArrayList<FreelancerDetailsModel> freelancerDetailsModels = new ArrayList<>();
    String lat,lng,userimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_freelancer);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("See All Freelancer");

        recycleAll = findViewById(R.id.recycle_all);
        act_session = new Act_Session(getApplicationContext());
        context = this;

        act_session=new Act_Session(getApplicationContext());
        userimage=act_session.profile_pic;

        // get the reference of RecyclerView
// set a GridLayoutManager with default vertical orientation and 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recycleAll.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        ApiGetFreelancerDetail();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        lat = intent.getStringExtra("lat");
        lng = intent.getStringExtra("long");
        Toast.makeText(context, lat+" "+lng, Toast.LENGTH_SHORT).show();


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    private void ApiGetFreelancerDetail() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    Log.e("seeFrellancer","::"+Json);
                    JSONObject jsonObject = new JSONObject(Json);

                    if (!jsonObject.getString("message").equals("Failed")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        freelancerDetailsModels = baseRequest.getDataList(jsonArray, FreelancerDetailsModel.class);

                        for (int i = 0; i < freelancerDetailsModels.size(); i++) {
                            if (freelancerDetailsModels != null) {


                                CompanyDetailsModel model = new CompanyDetailsModel();
                                model.setCompanyname(freelancerDetailsModels.get(0).getFirstname());

                                // companyAddServiceModels2.add(model);
                                seeAllFreelancerAdapter =new SeeAllFreelancerAdapter(context,freelancerDetailsModels);
//                                LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
//                                recyclerView.setLayoutManager(layoutManager1);


                                LinearLayoutManager gridLayoutManager = new LinearLayoutManager(SeeAllFreelancer.this);
                                gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
                                recycleAll.setLayoutManager(gridLayoutManager);
                                recycleAll.setHasFixedSize(true);
                                recycleAll.setAdapter(seeAllFreelancerAdapter);

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
        String remainingUrl2 = "https://aoneservice.net.in/salon/get-apis/freelancer_dashboarddata_api.php?"  + "latif="  + lat + "&longif=" + lng;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }
}