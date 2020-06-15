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

import com.example.cadappforuser.Act_FreelancerProfile;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceDescription;
import com.example.cadappforuser.ServicesListActivity;
import com.example.cadappforuser.model.ServicesFeatureAndCategoriesHomeModel;

import java.util.List;

public class ServicesFeturesAndCategoriesHomeAdapter extends RecyclerView.Adapter<ServicesFeturesAndCategoriesHomeAdapter.FeatureViewHolder> {

    Context context;
    List<ServicesFeatureAndCategoriesHomeModel> servicesFeatureAndCategoriesHomeModelList;

    public ServicesFeturesAndCategoriesHomeAdapter(Context context, List<ServicesFeatureAndCategoriesHomeModel> servicesFeatureAndCategoriesHomeModelList) {
        this.context = context;
        this.servicesFeatureAndCategoriesHomeModelList = servicesFeatureAndCategoriesHomeModelList;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_feature,parent,false);
        FeatureViewHolder featureViewHolder=new FeatureViewHolder(view);
        return  featureViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {

        ServicesFeatureAndCategoriesHomeModel servicesFeatureAndCategoriesHomeModel=servicesFeatureAndCategoriesHomeModelList.get(position);
        holder.txtSample.setText(servicesFeatureAndCategoriesHomeModel.getSample());
        holder.txtPrice.setText("Rs."+servicesFeatureAndCategoriesHomeModel.getServicePrice());
        holder.txtName.setText(servicesFeatureAndCategoriesHomeModel.getServiceName());
        holder.imageView.setImageResource(servicesFeatureAndCategoriesHomeModel.getImage());

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                context.startActivity(new Intent(context, Act_FreelancerProfile.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return servicesFeatureAndCategoriesHomeModelList.size();
    }

    public class FeatureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        TextView txtPrice,txtName,txtSample;
        ItemClickListner itemClickListner;
        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.facialImage);
            txtName=itemView.findViewById(R.id.serviceName);
            txtPrice=itemView.findViewById(R.id.servicePrice);
            txtSample=itemView.findViewById(R.id.sample);
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
