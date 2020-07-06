package com.example.cadappforuser.SeeAll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cadappforuser.R;

public class SeeAllFreelancer extends AppCompatActivity {

    RecyclerView recycleAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_freelancer);

        recycleAll = findViewById(R.id.recycle_all);


        // get the reference of RecyclerView
// set a GridLayoutManager with default vertical orientation and 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recycleAll.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
    }
}
