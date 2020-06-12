package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.SqliteDatabase.DatabaseHelper;
import com.example.cadappforuser.model.CheckBoxModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanyServicesOffered extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    ArrayList<CheckBoxModel> checkBoxModels = new ArrayList<>();

    ArrayList<String> selectedItems;
    DatabaseHelper db;

    Button btn_company_offered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comapny_activity_act__list);

        btn_company_offered = findViewById(R.id.btn_companyoffered);
        expandableListView =  findViewById(R.id.expandableListView);

        selectedItems=new ArrayList<String>();
        db=new DatabaseHelper(this);


        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(this, expandableListTitle,checkBoxModels, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                db.insertData(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition));
                v.setBackgroundResource(R.color.quantum_orange);

                // startActivity(new Intent(MainActivity.this,ShowData.class));
                Toast.makeText(getApplicationContext(), expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        btn_company_offered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showSelectedItems();
                //  Toast.makeText(getApplicationContext(), "Saved '"+ checked + "' in DB", Toast.LENGTH_SHORT).show();


            }
        });


    }


    public void showSelectedItems(){
        String selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                db.insertData(item);
            else
                db.insertData(item);
        }
        //db.insertData(selItems);
        startActivity(new Intent(CompanyServicesOffered.this,ServicesOfferedWithprice.class));
        Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
    }
    }

