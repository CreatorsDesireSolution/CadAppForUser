package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceDescription;
import com.example.cadappforuser.model.ServicesListModel;

import java.util.List;

public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.ViewHolder> {

    Context context;
    List<ServicesListModel> servicesListModelList;

    public ServicesListAdapter(Context context, List<ServicesListModel> servicesListModelList) {
        this.context = context;
        this.servicesListModelList = servicesListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_services_list,parent,false);
      ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ServicesListModel servicesListModel=servicesListModelList.get(position);
        holder.sample.setText(servicesListModel.getSample());
        holder.name.setText(servicesListModel.getName());
        holder.price.setText("Rs."+servicesListModel.getPrice());
        holder.imageView.setImageResource(servicesListModel.getImage());
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent=new Intent(context, ServiceDescription.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return servicesListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView price,name,sample;
        ItemClickListner itemClickListner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.serviceListServiceImage);
            price=itemView.findViewById(R.id.serviceListServicePrice);
            name=itemView.findViewById(R.id.serviceListServiceName);
            sample=itemView.findViewById(R.id.serviceListServiceSample);

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
