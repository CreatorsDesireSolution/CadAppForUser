package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;
import java.util.List;

public class CompanyNewAdapter extends RecyclerView.Adapter<CompanyNewAdapter.CompanyNewVIewHolder> implements Filterable {

    Context context;
    List<CompanyNewModel> companyNewModels;
    List<CompanyNewModel> companyNewModelsAll;

    public CompanyNewAdapter(Context context, List<CompanyNewModel> companyNewModels) {
        this.context = context;
        this.companyNewModels = companyNewModels;
        this.companyNewModelsAll=new ArrayList<>(companyNewModels);
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
    public int getItemCount()
    {
        return companyNewModels.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    Filter filter=new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<CompanyNewModel> filterList=new ArrayList<>();
            if(charSequence.toString()==null){
                filterList.addAll(companyNewModelsAll);
            }else{
                String serachStr=charSequence.toString().toUpperCase();
                for (CompanyNewModel servicesS: companyNewModelsAll){
                    if(servicesS.getName().toUpperCase().contains(serachStr)){
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
            companyNewModels.clear();
            companyNewModels.addAll((List<CompanyNewModel>)filterResults.values);
            notifyDataSetChanged();
        }
    };


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

