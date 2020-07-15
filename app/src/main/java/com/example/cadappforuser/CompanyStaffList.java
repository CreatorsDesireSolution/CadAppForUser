package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cadappforuser.adapter.StaffDetailAdapter;
import com.example.cadappforuser.model.StaffDetailsModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompanyStaffList extends AppCompatActivity {

    ArrayList<StaffDetailsModel> staffDetailsModels;
    RecyclerView recyclerView;
    Act_Session act_session ;
    Activity activity;
    Context context;
    BaseRequest baseRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_staff_list);

        recyclerView=findViewById(R.id.recycle_staff_list);
        staffDetailsModels=new ArrayList<>();
        act_session = new Act_Session(getApplicationContext());
        activity = this;
        context = this;

//        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
//        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
//        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
//        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
//        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
//        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
//        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
//        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
//
//        CompanyStaffListAdapter companyStaffListAdapter=new CompanyStaffListAdapter(this,companyStaffListModelArrayList);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(companyStaffListAdapter);

        ApiGetStaff();
    }


    private void ApiGetStaff() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);

                    if (!jsonObject.getString("message").equals("Failed")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        staffDetailsModels = baseRequest.getDataList(jsonArray, StaffDetailsModel.class);

                        for (int i = 0; i < staffDetailsModels.size(); i++) {
                            if (staffDetailsModels != null) {


                                StaffDetailsModel model = new StaffDetailsModel();
                                model.setFirstname(staffDetailsModels.get(0).getFirstname());

                                // companyAddServiceModels2.add(model);
                                StaffDetailAdapter companyDetailsAdapter =new StaffDetailAdapter(CompanyStaffList.this, staffDetailsModels);
                                LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true);
                                recyclerView.setLayoutManager(layoutManager1);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(companyDetailsAdapter);



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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/company_staffdata_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }
}
