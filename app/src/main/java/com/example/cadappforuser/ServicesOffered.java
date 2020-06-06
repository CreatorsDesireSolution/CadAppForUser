package com.example.cadappforuser;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServicesOffered extends AppCompatActivity {

    ExpandableListAdapter1 listAdapter;
    ExpandableListView expListView;


    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__list);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Service Offered");

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter1(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        // setting list adapter

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Man");
        listDataHeader.add("Macro");
        listDataHeader.add("Massage");
        listDataHeader.add("Manicure");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Face Massage ,80 minutes");
        top250.add("Face Massage ,50 minutes");
        top250.add("Therapeutic Massage ,80 minutes");
        top250.add("Classic Massage ,80 minutes");


        List<String> manicure = new ArrayList<String>();
        manicure.add("Face Massage ,80 minutes");
        manicure.add("Face Massage ,50 minutes");
        manicure.add("Therapeutic Massage ,80 minutes");
        manicure.add("Classic Massage ,80 minutes");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3), manicure);
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
}

