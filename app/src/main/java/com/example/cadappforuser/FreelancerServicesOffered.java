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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__list);

        initializeDB();

        btnNextoffered = findViewById(R.id.btnNextoffered);

        cb = findViewById(R.id.check);

        expandableListView =  findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(this, expandableListTitle, checkBoxModels, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

       // checked = checkBoxModels.get(0).getChecked();




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

                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)

                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition) + " clicked on check ").get(
                                childPosition), Toast.LENGTH_SHORT
                )
                        .show();
                return false;
            }
        });

        btnNextoffered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateTable(checked);
                Toast.makeText(getApplicationContext(), "Saved '"+ checked + "' in DB", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ServicesOfferedWithprice.class);

//                String str = (String) checkbox.getText();
//                intent.putExtra("variable", str);
                startActivity(intent);
            }
        });

//        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    checked = 1;
//                }else{
//                    checked = 0;
//                }
//            }
//        });
    }

    public  void initializeDB(){
        MyTable user = new MyTable();
        String[] tableCreateArray = { user.getDatabaseCreateQuery() };
        dbOperation operation = new dbOperation(this,tableCreateArray);
        operation.open();
        operation.close();
    }

    /*** SAVE THE DATA IN DB - GIVE FILENAME AND DATA ***/
    public  void saveData(int data){
        dbOperation operationObj = new dbOperation(this);
        operationObj.open();
        MyTable Fields = new MyTable();
        ContentValues initialValues = new ContentValues();
        initialValues.put(Fields.getScore(), data);
        operationObj.insertTableData(Fields.getTableName(),initialValues);
        operationObj.close();
    }

    /*** GET THE DATA FROM DB ,PARAMS - FILENAME -> GET THE DATA ***/
    public String getData(int id){
        String _data = "";
        dbOperation operationObj = new dbOperation(this);
        operationObj.open();
        MyTable fields = new MyTable();
        String  condition2 = fields.getID() + " ='" + id + "'";
        String[] dbFields4 = {fields.getScore()};
        Cursor cursor2 =  operationObj.getTableRow(fields.getTableName(),dbFields4,condition2,fields.getID() + " ASC ","1");
        if(cursor2.getCount() > 0)
        {
            cursor2.moveToFirst();
            do{
                _data = cursor2.getString(0);
            }while(cursor2.moveToNext());
        }else{
            _data = "error";
        }
        cursor2.close();
        cursor2.deactivate();
        operationObj.close();
        return _data;
    }

    /*** SAVE OR UPDATE DB -> GIVE THE FILENAME AND DATA ***/
    public void updateTable(int updt_data){
        dbOperation operationObj = new dbOperation(this);
        operationObj.open();
        MyTable Fields = new MyTable();
        //check for the value to update if no value then insert.
        String file_ = getData(1);
        if(file_.equals("error")){
            saveData(updt_data);
        }else{
            String  condition = Fields.getID() +" = '1'";
            ContentValues initialValues = new ContentValues();
            initialValues.put(Fields.getScore(), updt_data);
            operationObj.updateTable(Fields.getTableName(),initialValues,condition);
        }
        operationObj.close();
    }
}

