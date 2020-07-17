package com.example.cadappforuser.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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
import com.example.cadappforuser.SqliteDatabase.Myhelper;
import com.example.cadappforuser.model.CartModel;
import com.example.cadappforuser.model.ServicesListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServicesListAdapterForShow extends RecyclerView.Adapter<ServicesListAdapterForShow.ViewHolder> {

    Context context;
    List<ServicesListModel> servicesListModelList;
    private boolean[] favorites;
    private FavDB favDB;
    String id;
    String Pid;

    public ServicesListAdapterForShow(Context context, List<ServicesListModel> servicesListModelList) {
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

        final ServicesListModel servicesListModel=servicesListModelList.get(position);
        final Myhelper myhelper = new Myhelper(context);
        final SQLiteDatabase database = myhelper.getWritableDatabase();
        final SQLiteDatabase db=myhelper.getReadableDatabase();

        final int[] number = {1};
        readCursorData(servicesListModel,holder);
        holder.sample.setText(servicesListModel.getSample());
        holder.name.setText(servicesListModel.getName());
        holder.price.setText("Rs."+servicesListModel.getPrice());
        id=servicesListModel.getProviderId();
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load(servicesListModel.getImage()).resize(400, 400).centerCrop().into(holder.imageView);

        holder.btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.btn_addtocart.getText() == "Added") {
                    Toast.makeText(context, "Item Already Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Myhelper myhelper=new Myhelper(context);
                    SQLiteDatabase database = myhelper.getReadableDatabase();
                    String sql = "select * from CART";
                    Cursor c = database.rawQuery(sql,null);
                    if(c.moveToFirst())
                    {
                        String Pid=c.getString(6);
                        if(Pid.equals(id))
                        {
                            ContentValues values = new ContentValues();
                            values.put("SERVICE_NAME", servicesListModel.getName());
                            values.put("SERVICE_PRICE", servicesListModel.getPrice());
                            values.put("SERVICE_DESC", servicesListModel.getSample());
                            values.put("SERVICE_IMAGE", servicesListModel.getImage());
                            values.put("QTY", number[0]);
                            values.put("PROVIDERID",id);
                            database.insert("CART", null, values);
                            holder.btn_addtocart.setText("Added");
                            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
// cart empty code then add item
                            String Pi=c.getString(6);
                            if(!Pi.equals(id)){
                                database=myhelper.getWritableDatabase();
                                database.execSQL("delete from CART");

                            }
                            ContentValues values = new ContentValues();
                            values.put("SERVICE_NAME", servicesListModel.getName());
                            values.put("SERVICE_PRICE", servicesListModel.getPrice());
                            values.put("SERVICE_DESC", servicesListModel.getSample());
                            values.put("SERVICE_IMAGE", servicesListModel.getImage());
                            values.put("QTY", number[0]);
                            values.put("PROVIDERID",id);
                            database.insert("CART", null, values);
                            holder.btn_addtocart.setText("Added");
                            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        ContentValues values = new ContentValues();
                        values.put("SERVICE_NAME", servicesListModel.getName());
                        values.put("SERVICE_PRICE", servicesListModel.getPrice());
                        values.put("SERVICE_DESC", servicesListModel.getSample());
                        values.put("SERVICE_IMAGE", servicesListModel.getImage());
                        values.put("QTY", number[0]);
                        values.put("PROVIDERID",id);
                        database.insert("CART", null, values);
                        holder.btn_addtocart.setText("Added");
                        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

     /*   holder.btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.btn_addtocart.getText() == "Added") {
                    Toast.makeText(context, "Item Already Added", Toast.LENGTH_SHORT).show();
                }
                else {

                    Myhelper myhelper=new Myhelper(context);
                    SQLiteDatabase database = myhelper.getReadableDatabase();
                    String sql = "select * from CART";
                    Cursor c = database.rawQuery(sql,null);

                    if(c.moveToFirst()) {
                        while (c.moveToNext()) {
                            Pid=c.getString(6);
                            Log.d("PID","PID"+Pid);
                        }
                    }
                    Log.d("PID1","PID1"+Pid);
                            ContentValues values = new ContentValues();
                            values.put("SERVICE_NAME", servicesListModel.getName());
                            values.put("SERVICE_PRICE", servicesListModel.getPrice());
                            values.put("SERVICE_DESC", servicesListModel.getSample());
                            values.put("SERVICE_IMAGE", servicesListModel.getImage());
                            values.put("QTY", number[0]);
                            values.put("PROVIDERID",id);
                            database.insert("CART", null, values);
                            holder.btn_addtocart.setText("Added");
                            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                        }
            }
        });*/

    }

    private void readCursorData(ServicesListModel servicesListModel, ViewHolder holder) {
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
    }

    @Override
    public int getItemCount() {
        return servicesListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
            ImageView fevicon;
        TextView price,name,sample;
        Button btn_addtocart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.serviceListServiceImage);
            price=itemView.findViewById(R.id.serviceListServicePrice);
            name=itemView.findViewById(R.id.serviceListServiceName);
            sample=itemView.findViewById(R.id.serviceListServiceSample);
            fevicon = itemView.findViewById(R.id.fevicon);
            btn_addtocart = itemView.findViewById(R.id.btn_addtocart);

            fevicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int postion=getAdapterPosition();
                    ServicesListModel servicesListModel=servicesListModelList.get(postion);
                    if(servicesListModel.getFav_status().equals("0")){
                        servicesListModel.setFav_status("1");

                        favDB.insertIntoDatabase(servicesListModel.getName(),servicesListModel.getPrice(),servicesListModel.getSample(), servicesListModel.getImage(),servicesListModel.getKey_id(),servicesListModel.getFav_status());
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
            });


//

        }

    }
}
