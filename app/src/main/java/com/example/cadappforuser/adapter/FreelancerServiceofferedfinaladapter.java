package com.example.cadappforuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.R;
import com.example.cadappforuser.model.FreelancerServiceListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FreelancerServiceofferedfinaladapter extends RecyclerView.Adapter<FreelancerServiceofferedfinaladapter.ViewHolder> {
    Context context;
    List<FreelancerServiceListModel> freelancerServiceListModelList;

    public FreelancerServiceofferedfinaladapter(Context context, List<FreelancerServiceListModel> freelancerServiceListModelList) {
        this.context = context;
        this.freelancerServiceListModelList = freelancerServiceListModelList;
    }

    @NonNull
    @Override
    public FreelancerServiceofferedfinaladapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.freelanceroffered,parent,false);
        FreelancerServiceofferedfinaladapter.ViewHolder viewHolder=new FreelancerServiceofferedfinaladapter.ViewHolder(view);
        return  viewHolder;     }

    @Override
    public void onBindViewHolder(@NonNull FreelancerServiceofferedfinaladapter.ViewHolder holder, int position) {

        FreelancerServiceListModel companyAddServiceModel=freelancerServiceListModelList.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.sample.setText(freelancerServiceListModelList.get(position).getDescription());
        holder.name.setText(companyAddServiceModel.getServiceName());
        holder.price.setText("Rs."+companyAddServiceModel.getSetPrice()+"/-");
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyAddServiceModel.getServiceImage()).resize(400, 400).centerCrop().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return freelancerServiceListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,fevicon;
        TextView price,name,sample;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.serviceListServiceImage);
            price=itemView.findViewById(R.id.serviceListServicePrice);
            name=itemView.findViewById(R.id.serviceListServiceName);
            sample=itemView.findViewById(R.id.serviceListServiceSample);
        }
    }
}
