package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceDescription;
import com.example.cadappforuser.ServiceModel.AllServiceModel;
import com.example.cadappforuser.companymodel.CompanyNewModel;
import com.example.cadappforuser.model.ServicesFreelancerHomeModel;

import java.util.ArrayList;
import java.util.List;

public class AllServicesAdapter extends RecyclerView.Adapter<AllServicesAdapter.AllServiceViewHolder> implements Filterable {

    Context context;
    List<AllServiceModel> allServiceModels;
    List<AllServiceModel> allServiceModelsAll;


    public AllServicesAdapter(Context context, List<AllServiceModel> allServiceModels) {
        this.context = context;
        this.allServiceModels = allServiceModels;
        this.allServiceModelsAll=new ArrayList<>(allServiceModels);
    }

    @NonNull
    @Override
    public AllServicesAdapter.AllServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customservice,parent,false);
        AllServicesAdapter.AllServiceViewHolder AllServiceViewHolder=new AllServicesAdapter.AllServiceViewHolder(view);
        return  AllServiceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllServicesAdapter.AllServiceViewHolder holder, int position) {

        AllServiceModel allServiceModel=allServiceModels.get(position);
        holder.txtSample.setText(allServiceModel.getSampleservice());
        holder.txtPrice.setText("Rs."+allServiceModel.getServiceprice());
        holder.txtName.setText(allServiceModel.getServicename());
        holder.imageView.setImageResource(allServiceModel.getImageservice());

       

    }

    @Override
    public int getItemCount() {
        return allServiceModels.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<AllServiceModel> filterList=new ArrayList<>();
            if(charSequence.toString()==null){
                filterList.addAll(allServiceModelsAll);
            }else{
                String serachStr=charSequence.toString().toUpperCase();
                for (AllServiceModel servicesS: allServiceModelsAll){
                    if(servicesS.getServicename().toUpperCase().contains(serachStr)){
                        filterList.add(servicesS);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filterList;
            return filterResults;
        }

        //run on ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            allServiceModels.clear();
            allServiceModels.addAll((List<AllServiceModel>)filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class AllServiceViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtPrice,txtName,txtSample;
        public AllServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.facialImageFreelancer);
            txtName=itemView.findViewById(R.id.serviceNameFreelancer);
            txtPrice=itemView.findViewById(R.id.servicePriceFreelancer);
            txtSample=itemView.findViewById(R.id.sampleFreelancer);

        }
    }
}
