package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_CompanyNewProfile;
import com.example.cadappforuser.Act_FreelancerProfile;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceModel.NewModel;
import com.example.cadappforuser.companymodel.CompanyNewModel;

import java.util.List;

public class CompanyNewAdapter extends RecyclerView.Adapter<CompanyNewAdapter.CompanyNewVIewHolder> {

    Context context;
    List<CompanyNewModel> companyNewModels;

    public CompanyNewAdapter(Context context, List<CompanyNewModel> companyNewModels) {
        this.context = context;
        this.companyNewModels = companyNewModels;
    }

    @NonNull
    @Override
    public CompanyNewVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_newadapter,parent,false);
        CompanyNewAdapter.CompanyNewVIewHolder companyNewVIewHolder=new CompanyNewAdapter.CompanyNewVIewHolder(view);
        return  companyNewVIewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CompanyNewVIewHolder holder, int position) {

        CompanyNewModel companyNewModel=companyNewModels.get(position);
        holder.tv_freelancername.setText(companyNewModel.getName());
        holder.ratingBar.setRating(companyNewModel.getRating());
        holder.facialImageFreelancer.setImageResource(companyNewModel.getImage());

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

                context.startActivity(new Intent(context, Act_CompanyNewProfile.class));


            }
        });


    }

    @Override
    public int getItemCount() {
        return companyNewModels.size();
    }

    public class CompanyNewVIewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView facialImageFreelancer;
        TextView tv_freelancername;
        RatingBar ratingBar;
        private ItemClickListner itemClickListner;
        public CompanyNewVIewHolder(@NonNull View itemView) {
            super(itemView);

            facialImageFreelancer = itemView.findViewById(R.id.facialImageFreelancer);
            tv_freelancername = itemView.findViewById(R.id.freelancer);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.itemClickListner.onItemClickListner(v,getLayoutPosition());


        }

        public void setItemClickListner(ItemClickListner itemClickListner) {
            this.itemClickListner=itemClickListner;

        }
    }
}

