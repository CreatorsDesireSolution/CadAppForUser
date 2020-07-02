package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cadappforuser.adapter.CompanyAddServiceAdapater;
import com.example.cadappforuser.adapter.ServicesListAdapterForShow;
import com.example.cadappforuser.model.CompanyAddServiceModel;
import com.example.cadappforuser.model.CompanyProfileDataModel;
import com.example.cadappforuser.model.ServicesListModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CompanyShowServices extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CompanyAddServiceModel> companyAddServiceModels;
    ArrayList<CompanyAddServiceModel>companyAddServiceModels2=new ArrayList<>();

    CompanyAddServiceAdapater addServiceAdapater;
    BaseRequest baseRequest;
     Act_Session act_session;
     Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_show_services);

        context = this;
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services List");

        recyclerView=findViewById(R.id.recycle);
        companyAddServiceModels=new ArrayList<>();

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        act_session =new Act_Session(getApplicationContext());


        ApiGetList();
      //  ApiAddService();
    }


    private void ApiGetList() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);

                    if (!jsonObject.getString("message").equals("Failed")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        companyAddServiceModels = baseRequest.getDataList(jsonArray, CompanyAddServiceModel.class);

                        companyAddServiceModels = baseRequest.getDataList(jsonArray, CompanyAddServiceModel.class);
                        for (int i = 0; i < companyAddServiceModels.size(); i++) {
                            if (companyAddServiceModels != null) {


                                CompanyAddServiceModel model = new CompanyAddServiceModel();
                                model.setDescription(companyAddServiceModels.get(0).getDescription());
                                model.setDuration(companyAddServiceModels.get(0).getDuration());
                                model.setServiceImage(companyAddServiceModels.get(0).getServiceImage());
                                model.setServiceName(companyAddServiceModels.get(0).getServiceName());
                                companyAddServiceModels2.add(model);

                                addServiceAdapater = new CompanyAddServiceAdapater(CompanyShowServices.this, companyAddServiceModels);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(addServiceAdapater);


                            } else {
                                Toast.makeText(CompanyShowServices.this, "No Data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(CompanyShowServices.this, "No Data", Toast.LENGTH_SHORT).show();

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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/company_servicedata_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }


    private void ApiAddService() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                // act_session.loginSession(context);
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                     //JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                  //  companyAddServiceModels = baseRequest.getDataList(jsonArray, CompanyAddServiceModel.class);
                    addServiceAdapater=new CompanyAddServiceAdapater(CompanyShowServices.this,companyAddServiceModels);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(addServiceAdapater);



                    Toast.makeText(getApplicationContext(), "Add Successfully", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(RegisterAsFreelancerActivity.this, FreelancerMobileNumberRegisterActivity.class));
//                    Intent intent = new Intent(getApplicationContext(),CompanyHomePageActivity.class);
//                    startActivity(intent);

                    finish();




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onNetworkFailure(int requestCode, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        });
        RequestBody userid_ = RequestBody.create(MediaType.parse("text/plain"),act_session.userId );



        baseRequest.callgetservice(1,"https://aoneservice.net.in/" , userid_
                );

    }

}
