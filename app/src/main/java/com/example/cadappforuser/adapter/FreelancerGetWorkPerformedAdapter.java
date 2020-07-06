package com.example.cadappforuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.R;
import com.example.cadappforuser.model.CompanyGetWorkPerformedModel;
import com.example.cadappforuser.model.FreelancerGetWorkPerformedModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FreelancerGetWorkPerformedAdapter extends RecyclerView.Adapter<FreelancerGetWorkPerformedAdapter.ViewHolder> {

    Context context;
    List<FreelancerGetWorkPerformedModel> freelancerGetWorkPerformedModels;

    public FreelancerGetWorkPerformedAdapter(Context context, List<FreelancerGetWorkPerformedModel> freelancerGetWorkPerformedModels) {
        this.context = context;
        this.freelancerGetWorkPerformedModels = freelancerGetWorkPerformedModels;
    }

    @NonNull
    @Override


    public FreelancerGetWorkPerformedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.freelancerworkperformed,parent,false);
        FreelancerGetWorkPerformedAdapter.ViewHolder viewHolder = new FreelancerGetWorkPerformedAdapter.ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FreelancerGetWorkPerformedAdapter.ViewHolder holder, int position) {

        FreelancerGetWorkPerformedModel companyGetWorkPerformedModel = freelancerGetWorkPerformedModels.get(position);
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyGetWorkPerformedModel.getPicWorkPerformed())
                .resize(400, 400).centerCrop().into(holder.iv_certificate);


    }

    @Override
    public int getItemCount() {
        return freelancerGetWorkPerformedModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_certificate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_certificate = itemView.findViewById(R.id.iv_certificate);
        }
    }
}
