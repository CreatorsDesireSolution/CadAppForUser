package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.ServicesOffered;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.ServicesModel;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    Context context;
    List<ServicesModel> servicesModelList;

    public ServicesAdapter(Context context, List<ServicesModel> servicesModelList) {
        this.context = context;
        this.servicesModelList = servicesModelList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_services,parent,false);
        ServiceViewHolder serviceViewHolder=new ServiceViewHolder(view);
        return  serviceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {

        ServicesModel servicesModel=servicesModelList.get(position);
        holder.image.setImageResource(servicesModel.getImage());
        holder.txtSample.setText(servicesModel.getServiceSample());
        holder.txtName.setText(servicesModel.getServicesName());
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                context.startActivity(new Intent(context, ServicesOffered.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return servicesModelList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView txtName,txtSample;

        ItemClickListner itemClickListner;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.servicesImage);
            txtName=itemView.findViewById(R.id.textServiceName);
            txtSample=itemView.findViewById(R.id.txtLorem);
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
