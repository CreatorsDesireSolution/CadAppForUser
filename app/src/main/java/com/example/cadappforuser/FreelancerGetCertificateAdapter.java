package com.example.cadappforuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.adapter.CompanyGetWorkperformedAdapter;
import com.example.cadappforuser.model.FreelancerGetCertificateModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FreelancerGetCertificateAdapter extends RecyclerView.Adapter<FreelancerGetCertificateAdapter.ViewHolder> {

    Context context;
    List<FreelancerGetCertificateModel> freelancerGetCertificateModels;

    public FreelancerGetCertificateAdapter(Context context, List<FreelancerGetCertificateModel> freelancerGetCertificateModels) {
        this.context = context;
        this.freelancerGetCertificateModels = freelancerGetCertificateModels;
    }

    @NonNull
    @Override
    public FreelancerGetCertificateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.getfreelancercertificate,parent,false);
        FreelancerGetCertificateAdapter.ViewHolder viewHolder = new FreelancerGetCertificateAdapter.ViewHolder(view);
        return  viewHolder;        }


    @Override
    public void onBindViewHolder(@NonNull FreelancerGetCertificateAdapter.ViewHolder holder, int position) {

        FreelancerGetCertificateModel freelancerGetCertificateModel = freelancerGetCertificateModels.get(position);
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+freelancerGetCertificateModel.getCertificate())
                .resize(400, 400).centerCrop().into(holder.iv_certificate);

    }

    @Override
    public int getItemCount() {
        return freelancerGetCertificateModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_certificate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_certificate = itemView.findViewById(R.id.iv_certificate);
        }
    }
}
