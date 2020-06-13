package com.example.cadappforuser;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.SqliteDatabase.DatabaseHelper;
import com.example.cadappforuser.SqliteDatabase.MyTable;
import com.example.cadappforuser.SqliteDatabase.dbOperation;
import com.example.cadappforuser.model.CheckBoxModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FreelancerServicesOffered extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    ArrayList<CheckBoxModel> checkBoxModels = new ArrayList<>();
    HashMap<String, List<String>> expandableListDetail;
    Button btnNextoffered;
    CheckBox cb;
    CheckBox checkbox;
    int checked =0 ;
    ArrayList<String> selectedItems;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__list);


        btnNextoffered = findViewById(R.id.btnNextoffered);

      //  cb = findViewById(R.id.check);
        selectedItems=new ArrayList<String>();
        db=new DatabaseHelper(this);

        expandableListView =  findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(this, expandableListTitle, checkBoxModels, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);




       // checked = checkBoxModels.get(0).getChecked();




        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

//                int previousItem = -1;
//
//                if (groupPosition != previousItem)
//                    expandableListView.collapseGroup(previousItem);
//                previousItem = groupPosition;

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



        btnNextoffered.setOnClickListener(new View.OnClickListener() {
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
        startActivity(new Intent(FreelancerServicesOffered.this,ServicesOfferedWithprice.class));
        Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
    }



    /*** SAVE THE DATA IN DB - GIVE FILENAME AND DATA ***/


    /*** GET THE DATA FROM DB ,PARAMS - FILENAME -> GET THE DATA ***/

}

