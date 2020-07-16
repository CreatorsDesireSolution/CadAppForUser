package com.example.cadappforuser.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceDescription;
import com.example.cadappforuser.SqliteDatabase.FavDB;
import com.example.cadappforuser.model.ServicesListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.ViewHolder> {

    Context context;
    List<ServicesListModel> servicesListModelList;
    private boolean[] favorites;
    private FavDB favDB;

    public ServicesListAdapter(Context context, List<ServicesListModel> servicesListModelList) {
        this.context = context;
        this.servicesListModelList = servicesListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB=new FavDB(context);
        SharedPreferences sharedPreferences=context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
        boolean firstStart=sharedPreferences.getBoolean("firstStart",true);
        if(firstStart){
            createTableOnFirstStart();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_services_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();
        SharedPreferences preferences=context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        ServicesListModel servicesListModel=servicesListModelList.get(position);

       // readCursorData(servicesListModel,holder);
        holder.sample.setText(servicesListModel.getSample());
        holder.name.setText(servicesListModel.getName());
        holder.price.setText("Rs."+servicesListModel.getPrice());

        Picasso.get().load(servicesListModel.getImage()).into(holder.imageView);


      /*  holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent=new Intent(context, ServiceDescription.class);
                context.startActivity(intent);
            }
        });*/

        holder.btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.btn_addtocart.getText() == "Added") {
                    Toast.makeText(context, "Item Already Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    holder.btn_addtocart.setText("Added");
                    Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*holder.fevicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fevicon.setImageResource(R.drawable.ic_star_black_24dp);
            }
        });*/

    }

  /*  private void readCursorData(ServicesListModel servicesListModel, ViewHolder holder) {
        Cursor cursor=favDB.real_all_data(servicesListModel.getKey_id());
        SQLiteDatabase db=favDB.getReadableDatabase();
        try{
            while (cursor.moveToNext()){
                String item_fav_status=cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                servicesListModel.setFav_status(item_fav_status);

                //check_fav_stauts
                if(item_fav_status!=null && item_fav_status.equals("1")){
                    holder.fevicon.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                }
                else if(item_fav_status!=null && item_fav_status.equals("0")){
                    holder.fevicon.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                }
            }
        }
        finally {
            if(cursor!=null && cursor.isClosed()){
                cursor.close();
                db.close();
            }
        }
    }*/

    @Override
    public int getItemCount() {
        return servicesListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView,fevicon;
        TextView price,name,sample;
        ItemClickListner itemClickListner;
        Button btn_addtocart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.serviceListServiceImage);
            price=itemView.findViewById(R.id.serviceListServicePrice);
            name=itemView.findViewById(R.id.serviceListServiceName);
            sample=itemView.findViewById(R.id.serviceListServiceSample);
            fevicon = itemView.findViewById(R.id.fevicon);
            btn_addtocart=itemView.findViewById(R.id.btn_addtocart);

           /* fevicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int postion=getAdapterPosition();
                    ServicesListModel servicesListModel=servicesListModelList.get(postion);
                    if(servicesListModel.getFav_status().equals("0")){
                        servicesListModel.setFav_status("1");
                        favDB.insertIntoDatabase(servicesListModel.getName(),servicesListModel.getPrice(),servicesListModel.getSample(),servicesListModel.getImage(),servicesListModel.getKey_id(),servicesListModel.getFav_status());
                        fevicon.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                        fevicon.setSelected(true);

                    }
                    else if(servicesListModel.getFav_status().equals("1")){
                        servicesListModel.setFav_status("0");
                        favDB.remove_fav(servicesListModel.getKey_id());
                        fevicon.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                       fevicon.setSelected(false);
                    }
                }
            });*/
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            this.itemClickListner.onItemClickListner(view,getLayoutPosition());
        }
        public void setItemClickListner(ItemClickListner ic){
            this.itemClickListner=ic;
        }

    }
}
