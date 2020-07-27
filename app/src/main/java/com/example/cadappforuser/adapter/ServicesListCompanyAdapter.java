package com.example.cadappforuser.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.R;
import com.example.cadappforuser.SqliteDatabase.FavDB;
import com.example.cadappforuser.SqliteDatabase.Myhelper;
import com.example.cadappforuser.model.ServicesListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServicesListCompanyAdapter extends RecyclerView.Adapter<ServicesListCompanyAdapter.ViewHolder> {

    Context context;
    List<ServicesListModel> servicesListModelList;

    private FavDB favDB;
    String id,status;
    String Pid,serviceId;

    public ServicesListCompanyAdapter(Context context, List<ServicesListModel> servicesListModelList) {
        this.context = context;
        this.servicesListModelList = servicesListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_freelancer_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final ServicesListModel servicesListModel=servicesListModelList.get(position);
        holder.sample.setText(servicesListModel.getSample());
        holder.name.setText(servicesListModel.getName());
        holder.price.setText("Rs."+servicesListModel.getPrice());
        Picasso.get().load(servicesListModel.getImage()).resize(400, 400).centerCrop().into(holder.imageView);


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
        }

    }
}
