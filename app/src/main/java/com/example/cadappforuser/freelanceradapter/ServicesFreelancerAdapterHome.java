package com.example.cadappforuser.freelanceradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.R;
import com.example.cadappforuser.modelfreelancer.ServicesFreelancerHomeModel;

import java.util.List;

public class ServicesFreelancerAdapterHome extends RecyclerView.Adapter<ServicesFreelancerAdapterHome.FreelancerViewHolder> {
    Context context;
    List<ServicesFreelancerHomeModel> servicesFreelancerHomeModelList;

    public ServicesFreelancerAdapterHome(Context context, List<ServicesFreelancerHomeModel> servicesFreelancerHomeModelList) {
        this.context = context;
        this.servicesFreelancerHomeModelList = servicesFreelancerHomeModelList;
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
      ServicesFreelancerHomeModel servicesFreelancerHomeModel=servicesFreelancerHomeModelList.get(position);
        holder.txtSample.setText(servicesFreelancerHomeModel.getSampleFreelancer());
        holder.txtPrice.setText("Rs."+servicesFreelancerHomeModel.getServicePriceFreelancer());
        holder.txtName.setText(servicesFreelancerHomeModel.getServicePriceFreelancer());
        holder.imageView.setImageResource(servicesFreelancerHomeModel.getImageFreelancer());

    }

    @Override
    public int getItemCount() {
        return servicesFreelancerHomeModelList.size();
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
