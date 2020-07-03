package com.example.cadappforuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.CompanyAddServiceModel;
import com.example.cadappforuser.model.FreelancerServiceListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FreelancerAddServiceAdapter extends RecyclerView.Adapter<FreelancerAddServiceAdapter.ViewHolder> {

    Context context;
    List<FreelancerServiceListModel> companyAddServiceModels;
    Activity activity;
    int count;
    Act_Session act_session;

    public FreelancerAddServiceAdapter(Context context, List<FreelancerServiceListModel> companyAddServiceModels, Activity activity, Act_Session act_session) {
        this.context = context;
        this.companyAddServiceModels = companyAddServiceModels;
        this.activity = activity;
        this.act_session = act_session;
    }

    @NonNull
    @Override
    public FreelancerAddServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.freelanceraddservice,parent,false);
        FreelancerAddServiceAdapter.ViewHolder viewHolder=new FreelancerAddServiceAdapter.ViewHolder(view);
        return  viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull FreelancerAddServiceAdapter.ViewHolder holder, int position) {
        FreelancerServiceListModel companyAddServiceModel=companyAddServiceModels.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.sample.setText(companyAddServiceModels.get(position).getDescription());
        holder.name.setText(companyAddServiceModel.getServiceName());
        holder.price.setText("Rs."+companyAddServiceModel.getSetPrice());
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyAddServiceModel.getServiceImage()).resize(400, 400).centerCrop().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return companyAddServiceModels.size();
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
            fevicon = itemView.findViewById(R.id.fevicon);

        }
    }
}
