package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_CompanyNewProfile;
import com.example.cadappforuser.Act_FreelancerProfileOnlyShow;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.companymodel.CompanyNewModel;
import com.example.cadappforuser.model.Ad_freelancermodel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Ad_Freelancer extends RecyclerView.Adapter<Ad_Freelancer.ViewHolder> implements Filterable {

    Context context;
    List<Ad_freelancermodel> ad_freelancermodels;
    String id;
    List<Ad_freelancermodel> Allad_freelancermodels;

    public Ad_Freelancer(Context context, List<Ad_freelancermodel> ad_freelancermodels) {
        this.context = context;
        this.ad_freelancermodels = ad_freelancermodels;
        this.Allad_freelancermodels=new ArrayList<>(ad_freelancermodels);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_freelancer,parent,false);
        Ad_Freelancer.ViewHolder companyNewVIewHolder=new Ad_Freelancer.ViewHolder(view);
        return  companyNewVIewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       final Ad_freelancermodel ad_freelancermodel=ad_freelancermodels.get(position);
        holder.tv_freelancername.setText(ad_freelancermodel.getName());
        Picasso.get().load(ad_freelancermodel.getImage()).resize(400, 400).centerCrop().into(holder.facialImageFreelancer);

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String name=ad_freelancermodel.getName();
                Toast.makeText(context, ""+name, Toast.LENGTH_SHORT).show();
                String id=ad_freelancermodel.getId();
                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, Act_FreelancerProfileOnlyShow.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ad_freelancermodels.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Ad_freelancermodel> filterList = new ArrayList<>();
            if (charSequence.toString() == null) {
                filterList.addAll(ad_freelancermodels);
            } else {
                String serachStr = charSequence.toString().toUpperCase();
                for (Ad_freelancermodel servicesS : Allad_freelancermodels) {
                    if (servicesS.getName().toUpperCase().contains(serachStr)) {
                        filterList.add(servicesS);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            ad_freelancermodels.clear();
            ad_freelancermodels.addAll((List<Ad_freelancermodel>)results.values);
            notifyDataSetChanged();

        }
    };

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ImageView facialImageFreelancer;
            TextView tv_freelancername,userrating;
            ImageView ratingBar;
            private ItemClickListner itemClickListner;
           public ViewHolder(@NonNull View itemView) {
            super(itemView);

            facialImageFreelancer = itemView.findViewById(R.id.facialImageFreelancer);
            tv_freelancername = itemView.findViewById(R.id.freelancer);
            ratingBar = itemView.findViewById(R.id.ratingbar);
               userrating =itemView.findViewById(R.id.userrating);
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
