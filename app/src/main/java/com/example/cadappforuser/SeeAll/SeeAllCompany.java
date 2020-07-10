package com.example.cadappforuser.SeeAll;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.CompanyHomePageActivity;
import com.example.cadappforuser.R;
import com.example.cadappforuser.adapter.CompanyDetailsAdapter;
import com.example.cadappforuser.adapter.SeeAllCompanyAdapter;
import com.example.cadappforuser.adapter.SeeAllFreelancerAdapter;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.example.cadappforuser.model.FreelancerDetailsModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SeeAllCompany extends AppCompatActivity {
    RecyclerView recycleAll;
  //  SeeAllFreelancerAdapter seeAllFreelancerAdapter;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    ArrayList<CompanyDetailsModel> companyDetailsModels = new ArrayList<>();
    SeeAllCompanyAdapter seeAllCompanyAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_company);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("See All Company");

        recycleAll = findViewById(R.id.recycle_all);
        act_session = new Act_Session(getApplicationContext());
        context = this;
        Apigetdetail();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                                seeAllCompanyAdapter =new SeeAllCompanyAdapter(context, companyDetailsModels);
//                                LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
//                                recyclerView1.setLayoutManager(layoutManager1);
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
                                recycleAll.setLayoutManager(gridLayoutManager);
                                recycleAll.setHasFixedSize(true);
                                recycleAll.setAdapter(seeAllCompanyAdapter);




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

}
