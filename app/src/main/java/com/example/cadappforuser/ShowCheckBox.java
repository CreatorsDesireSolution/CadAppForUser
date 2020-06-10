package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cadappforuser.SqliteDatabase.DatabaseHelper;

import java.util.ArrayList;

public class ShowCheckBox extends AppCompatActivity {

    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    DatabaseHelper db;
    ListView serviceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_check_box);


        db=new DatabaseHelper(this);
        arrayList=new ArrayList<>();
        serviceList=findViewById(R.id.listview);

        serviceList.refreshDrawableState();
        arrayList.clear();
        viewData();

    }


    private void viewData() {

        Cursor cursor=db.ShowData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "not select", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                arrayList.add(cursor.getString(1));
            }
            arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
            serviceList.setAdapter(arrayAdapter);
            arrayList=null;
            serviceList.refreshDrawableState();
            arrayAdapter.notifyDataSetChanged();

        }
        cursor.close();
    }
}
