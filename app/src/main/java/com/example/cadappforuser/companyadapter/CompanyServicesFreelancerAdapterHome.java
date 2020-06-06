package com.example.cadappforuser.companyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.R;
import com.example.cadappforuser.companymodel.CompanyServicesFreelancerHomeModel;

import java.util.List;

public class CompanyServicesFreelancerAdapterHome extends RecyclerView.Adapter<CompanyServicesFreelancerAdapterHome.FreelancerViewHolder> {
    Context context;
    List<CompanyServicesFreelancerHomeModel> companyServicesFreelancerHomeModelList;

    public CompanyServicesFreelancerAdapterHome(Context context, List<CompanyServicesFreelancerHomeModel> companyServicesFreelancerHomeModelList) {
        this.context = context;
        this.companyServicesFreelancerHomeModelList = companyServicesFreelancerHomeModelList;
    }

    @NonNull
    @Override
    public FreelancerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_freelancer,parent,false);
        FreelancerViewHolder freelancerViewHolder=new FreelancerViewHolder(view);
        return  freelancerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FreelancerViewHolder holder, int position) {
      CompanyServicesFreelancerHomeModel companyServicesFreelancerHomeModel = companyServicesFreelancerHomeModelList.get(position);
        holder.txtSample.setText(companyServicesFreelancerHomeModel.getSampleFreelancer());
        holder.txtPrice.setText("Rs."+ companyServicesFreelancerHomeModel.getServicePriceFreelancer());
        holder.txtName.setText(companyServicesFreelancerHomeModel.getServicePriceFreelancer());
        holder.imageView.setImageResource(companyServicesFreelancerHomeModel.getImageFreelancer());

    }

    @Override
    public int getItemCount() {
        return companyServicesFreelancerHomeModelList.size();
    }

    public class FreelancerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtPrice,txtName,txtSample;
        public FreelancerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.facialImageFreelancer);
            txtName=itemView.findViewById(R.id.serviceNameFreelancer);
            txtPrice=itemView.findViewById(R.id.servicePriceFreelancer);
            txtSample=itemView.findViewById(R.id.sampleFreelancer);

        }
    }
}
