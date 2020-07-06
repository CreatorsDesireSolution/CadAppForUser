package com.example.cadappforuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.CompanyGetCertificate;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CompanyGetAllCertificateAdapter extends RecyclerView.Adapter<CompanyGetAllCertificateAdapter.ViewHolder> {

    List<CompanyGetCertificate> companyGetCertificateList;
    Context context;
    Activity activity;
    Act_Session act_session;


    public CompanyGetAllCertificateAdapter(List<CompanyGetCertificate> companyGetCertificateList, Activity activity, Act_Session act_session) {
        this.companyGetCertificateList = companyGetCertificateList;
        this.context = context;
        this.activity = activity;
        this.act_session = act_session;
    }

    @NonNull
    @Override
    public CompanyGetAllCertificateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.getcertificate,parent,false);
        CompanyGetAllCertificateAdapter.ViewHolder viewHolder=new CompanyGetAllCertificateAdapter.ViewHolder(view);
        return  viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull CompanyGetAllCertificateAdapter.ViewHolder holder, int position) {

        CompanyGetCertificate companyGetCertificate = companyGetCertificateList.get(position);
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyGetCertificate.getCertificate())
                .resize(400, 400).centerCrop().into(holder.iv_certificate);


    }

    @Override
    public int getItemCount() {
        return companyGetCertificateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_certificate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_certificate = itemView.findViewById(R.id.iv_certificate);
        }
    }
}
