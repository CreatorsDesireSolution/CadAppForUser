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
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.example.cadappforuser.model.FreelancerDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeeAllCompanyAdapter extends RecyclerView.Adapter<SeeAllCompanyAdapter.ViewHolder> {


    Context context;
    List<CompanyDetailsModel> companyDetailsModels;

    public SeeAllCompanyAdapter(Context context, List<CompanyDetailsModel> companyDetailsModels) {
        this.context = context;
        this.companyDetailsModels = companyDetailsModels;
    }

    @NonNull
    @Override
    public SeeAllCompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.companyall, parent, false);
        SeeAllCompanyAdapter.ViewHolder viewHolder = new SeeAllCompanyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeeAllCompanyAdapter.ViewHolder holder, int position) {

        CompanyDetailsModel companyDetailsModel = companyDetailsModels.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.tv_companyname.setText(companyDetailsModel.getCompanyname());
     //   holder.tv_lastname.setText(companyDetailsModel.getLastname());
        //holder.price.setText("Rs."+companyDetailsModel.getAboutCompany());
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/" + companyDetailsModel.getProfilePic()).
                resize(400, 400).centerCrop().into(holder.companyimageview);


    }

    @Override
    public int getItemCount() {
        return companyDetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_companyname, tv_lastname, userrating;
        ImageView ratingBar;
        ImageView companyimageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_companyname = itemView.findViewById(R.id.firstname);
           // tv_lastname = itemView.findViewById(R.id.lastname);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            companyimageview = itemView.findViewById(R.id.companyimageview);
            userrating = itemView.findViewById(R.id.userrating);
        }
    }
}
