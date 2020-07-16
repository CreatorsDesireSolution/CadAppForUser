package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cadappforuser.adapter.FreelancerAddServiceAdapter;
import com.example.cadappforuser.adapter.FreelancerServiceofferedfinaladapter;
import com.example.cadappforuser.model.FreelancerServiceListModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FreelancerServiceOfferedFinal extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<FreelancerServiceListModel> freelancerServiceLists;
    ArrayList<FreelancerServiceListModel>freelancerServiceLists2=new ArrayList<>();

    FreelancerServiceofferedfinaladapter freelancerServiceofferedfinaladapter;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    Activity activity;
    String freelancer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_service_offered_final);

        context = this;
        activity = this;
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services List");

        recyclerView=findViewById(R.id.recycleoffered);
        freelancerServiceLists=new ArrayList<>();

//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        act_session =new Act_Session(getApplicationContext());

        ApiGetList();
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
                        freelancerServiceLists = baseRequest.getDataList(jsonArray, FreelancerServiceListModel.class);

                        for (int i = 0; i < freelancerServiceLists.size(); i++) {
                            if (freelancerServiceLists != null) {


//                                FreelancerServiceListModel model = new FreelancerServiceListModel();
//                                model.setDescription(freelancerServiceLists.get(0).getDescription());
//                                model.setDuration(freelancerServiceLists.get(0).getDuration());
//                                model.setServiceImage(freelancerServiceLists.get(0).getServiceImage());
//                                model.setServiceName(freelancerServiceLists.get(0).getServiceName());
//                                freelancerServiceLists2.add(model);

                                freelancerServiceofferedfinaladapter = new FreelancerServiceofferedfinaladapter(context,
                                        freelancerServiceLists);
                                recyclerView.setHasFixedSize(true);
                                LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setAdapter(freelancerServiceofferedfinaladapter);


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
        String remainingUrl2 = "https://aoneservice.net.in/salon/get-apis/company_freelancerservices_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }

}
