package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.cadappforuser.companyadapter.CompanyStaffListAdapter;
import com.example.cadappforuser.companymodel.CompanyStaffListModel;

import java.util.ArrayList;

public class CompanyStaffList extends AppCompatActivity {

    ArrayList<CompanyStaffListModel> companyStaffListModelArrayList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_staff_list);

        recyclerView=findViewById(R.id.recycle_staff_list);
        companyStaffListModelArrayList=new ArrayList<>();

        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));
        companyStaffListModelArrayList.add(new CompanyStaffListModel("abc","6250289820","abc@gmail.com"));

        CompanyStaffListAdapter companyStaffListAdapter=new CompanyStaffListAdapter(this,companyStaffListModelArrayList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(companyStaffListAdapter);
    }
}
