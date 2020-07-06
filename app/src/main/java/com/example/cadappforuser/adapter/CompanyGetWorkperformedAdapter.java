package com.example.cadappforuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.CompanyGetCertificate;
import com.example.cadappforuser.model.CompanyGetWorkPerformedModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CompanyGetWorkperformedAdapter extends RecyclerView.Adapter<CompanyGetWorkperformedAdapter.ViewHolder> {
    List<CompanyGetWorkPerformedModel> companyGetWorkPerformedModels = new ArrayList<>();
    Context context;
    Activity activity;
    Act_Session act_session;


    public CompanyGetWorkperformedAdapter(List<CompanyGetWorkPerformedModel> companyGetWorkPerformedModels, Activity activity, Act_Session act_session) {
        this.companyGetWorkPerformedModels = companyGetWorkPerformedModels;
        this.activity = activity;
        this.act_session = act_session;
    }

    @NonNull
    @Override
    public CompanyGetWorkperformedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.getworkperformed,parent,false);
        CompanyGetWorkperformedAdapter.ViewHolder viewHolder = new CompanyGetWorkperformedAdapter.ViewHolder(view);
        return  viewHolder;        }

    @Override
    public void onBindViewHolder(@NonNull CompanyGetWorkperformedAdapter.ViewHolder holder, int position) {


        CompanyGetWorkPerformedModel companyGetWorkPerformedModel = companyGetWorkPerformedModels.get(position);
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyGetWorkPerformedModel.getPicWorkPerformed())
                .resize(400, 400).centerCrop().into(holder.iv_certificate);


    }

    @Override
    public int getItemCount() {
        return companyGetWorkPerformedModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_certificate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_certificate = itemView.findViewById(R.id.iv_certificate);

        }
    }
}
