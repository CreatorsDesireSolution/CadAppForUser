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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_CompanyNewProfile;
import com.example.cadappforuser.Act_CompanyNewProfileOnlyShow;
import com.example.cadappforuser.Act_FreelancerProfileOnlyShow;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.Ad_Companymodel;
import com.example.cadappforuser.model.Ad_freelancermodel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Ad_Company extends RecyclerView.Adapter<Ad_Company.ViewHolder> implements Filterable {
    Context context;
    List<Ad_Companymodel> ad_companymodels;
    List<Ad_Companymodel> Allad_companymodels;

    public Ad_Company(Context context, List<Ad_Companymodel> ad_companymodels) {
        this.context = context;
        this.ad_companymodels = ad_companymodels;
        this.Allad_companymodels=new ArrayList<>(ad_companymodels);

    }

    @NonNull
    @Override
    public Ad_Company.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_company,parent,false);
        Ad_Company.ViewHolder companyNewVIewHolder=new Ad_Company.ViewHolder(view);
        return  companyNewVIewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull Ad_Company.ViewHolder holder, int position) {

       final Ad_Companymodel ad_companymodel=ad_companymodels.get(position);
        holder.tv_freelancername.setText(ad_companymodel.getName());
       // holder.ratingBar.setRating(ad_companymodel.getRating());

        //holder.facialImageFreelancer.setImageResource(ad_companymodel.getImage());

        Picasso.get().load(ad_companymodel.getImage()).resize(400, 400).centerCrop().into(holder.facialImageFreelancer);

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

                String name=ad_companymodel.getName();
                Toast.makeText(context, ""+name, Toast.LENGTH_SHORT).show();
                String id=ad_companymodel.getId();
                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, Act_CompanyNewProfileOnlyShow.class);
                intent.putExtra("id",id);

                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return ad_companymodels.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    Filter filter=new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Ad_Companymodel> filterList = new ArrayList<>();
            if (charSequence.toString() == null) {
                filterList.addAll(ad_companymodels);
            } else {
                String serachStr = charSequence.toString().toUpperCase();
                for (Ad_Companymodel servicesS : Allad_companymodels) {
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

            ad_companymodels.clear();
            ad_companymodels.addAll((List<Ad_Companymodel>)results.values);
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
            userrating = itemView.findViewById(R.id.userrating);
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
