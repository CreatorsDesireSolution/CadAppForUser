package com.example.cadappforuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.cadappforuser.SqliteDatabase.FavDB;
import com.example.cadappforuser.adapter.AddFevAdapter;
import com.example.cadappforuser.adapter.FevListModel;
import com.example.cadappforuser.adapter.ServicesListAdapter;
import com.example.cadappforuser.model.ServicesListModel;

import java.util.ArrayList;

public class Act_AddToFev extends AppCompatActivity {

    AddFevAdapter favAdapter;
    RecyclerView recyclerviewfev;
    ArrayList<FevListModel> favItemList;
    private FavDB favDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__add_to_fev);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Fevorite List");

        recyclerviewfev = findViewById(R.id.recyclerviewfev);
        favItemList = new ArrayList<>();
        favDB = new FavDB(Act_AddToFev.this);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerviewfev); // set swipe to recyclerview


        // fevListModels.add(new FevListModel(R.drawable.hairwash,"450","Lorem Ipsum","Facial"));

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        AddFevAdapter addFevAdapter=new AddFevAdapter(this,favItemList);

        recyclerviewfev.setLayoutManager(layoutManager);
        recyclerviewfev.setHasFixedSize(true);
        recyclerviewfev.setAdapter(addFevAdapter);

loadData();
    }

    private void loadData() {
        if (favItemList != null) {
            favItemList.clear();
        }
        SQLiteDatabase db = favDB.getReadableDatabase();
        Cursor cursor = favDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(FavDB.SERVICE_NAME));
                String id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID));
                String price= cursor.getString(cursor.getColumnIndex(FavDB.SERVICE_PRICE));
                String favorite= cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));

                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavDB.SERVICE_IMAGE)));
                FevListModel favItem = new FevListModel(image,price,"Lorem",title,id,favorite);
                favItemList.add(favItem);
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

      favAdapter = new AddFevAdapter(Act_AddToFev.this, favItemList);

        recyclerviewfev.setAdapter(favAdapter);

    }

    // remove item after swipe
    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); // get position which is swipe
            final FevListModel favItem = favItemList.get(position);
            if (direction == ItemTouchHelper.LEFT) { //if swipe left
                favAdapter.notifyItemRemoved(position); // item removed from recyclerview
                favItemList.remove(position); //then remove item
                favDB.remove_fav(favItem.getKey_id()); // remove item from database
            }
        }
    };


}
