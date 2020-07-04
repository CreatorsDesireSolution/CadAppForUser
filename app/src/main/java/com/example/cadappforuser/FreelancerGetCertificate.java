package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cadappforuser.adapter.CompanyGetWorkperformedAdapter;
import com.example.cadappforuser.model.CompanyGetWorkPerformedModel;
import com.example.cadappforuser.model.FreelancerGetCertificateModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FreelancerGetCertificate extends AppCompatActivity {

    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    Activity activity;
    String freelancer_id;
    RecyclerView recyclerView;
    ArrayList<FreelancerGetCertificateModel> freelancerGetCertificateModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_get_certificate);



        context = this;
        activity = this;
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services List");

        recyclerView=findViewById(R.id.recycle);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        act_session =new Act_Session(getApplicationContext());
        Intent intent = getIntent();
        freelancer_id = intent.getStringExtra("id");
        ApiGetWorkperformed();
    }


    private void ApiGetWorkperformed() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);

                    if (!jsonObject.getString("message").equals("Failed")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        freelancerGetCertificateModels = baseRequest.getDataList(jsonArray, FreelancerGetCertificateModel.class);

                        for (int i = 0; i < freelancerGetCertificateModels.size(); i++) {
                            if (freelancerGetCertificateModels != null)

                            {

                                companyGetWorkperformedAdapter = new CompanyGetWorkperformedAdapter(companyGetWorkPerformedModels,activity,act_session);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(companyGetWorkperformedAdapter);

                                //encodeImage =  "http://aoneservice.net.in/salon/documents/"+companyGetCertificates.get(0).getCertificate();



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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/freelancer_getcertificate_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }
}
