package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.BarberInfo;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceDescription;
import com.example.cadappforuser.ServicesListActivity;
import com.example.cadappforuser.model.ServiceSearchModel;
import com.example.cadappforuser.model.ServicesModel;

import java.util.List;

public class ServicesSearchAdapter extends RecyclerView.Adapter<ServicesSearchAdapter.SViewHolder> {
    Context context;
    List<ServiceSearchModel> serviceSearchModelList;

    public ServicesSearchAdapter(Context context, List<ServiceSearchModel> serviceSearchModelList) {
        this.context = context;
        this.serviceSearchModelList = serviceSearchModelList;
    }

    @NonNull
    @Override
    public SViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_search_services,parent,false);
        SViewHolder sViewHolder=new SViewHolder(view);
        return sViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SViewHolder holder, int position) {

        ServiceSearchModel serviceSearchModel=serviceSearchModelList.get(position);
        holder.imageView.setImageResource(serviceSearchModel.getImageServices());
        holder.place.setText(serviceSearchModel.getPlaceServices());
        holder.name.setText(serviceSearchModel.getNameServices());

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                context.startActivity(new Intent(context, BarberInfo.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceSearchModelList.size();
    }

    public class SViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView name,place;
        ItemClickListner itemClickListner;
        public SViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageS);
            name=itemView.findViewById(R.id.nameS);
            place=itemView.findViewById(R.id.placeName);
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
