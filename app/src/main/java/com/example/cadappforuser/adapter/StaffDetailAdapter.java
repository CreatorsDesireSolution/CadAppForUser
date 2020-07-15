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

import com.example.cadappforuser.R;
import com.example.cadappforuser.model.FreelancerDetailsModel;
import com.example.cadappforuser.model.StaffDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StaffDetailAdapter extends RecyclerView.Adapter<StaffDetailAdapter.ViewHolder> {

    Context context;
    List<StaffDetailsModel> staffDetailsModels;
//    Activity activity;
//    int count;


    public StaffDetailAdapter(Context context, List<StaffDetailsModel> staffDetailsModels) {
        this.context = context;
        this.staffDetailsModels = staffDetailsModels;
    }

    @NonNull
    @Override
    public StaffDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_details,parent,false);
        StaffDetailAdapter.ViewHolder viewHolder=new StaffDetailAdapter.ViewHolder(view);
        return  viewHolder;     }

    @Override
    public void onBindViewHolder(@NonNull StaffDetailAdapter.ViewHolder holder, int position) {

        StaffDetailsModel staffDetailsModel = staffDetailsModels.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.tv_firstname.setText(staffDetailsModel.getFirstname());
        holder.tv_lastname.setText(staffDetailsModel.getLastname());
        holder.tv_staffemail.setText(staffDetailsModel.getEmail());
        holder.tv_staffnumber.setText(staffDetailsModel.getMobilenumber());
       // holder.tv_lastname.setText(staffDetailsModel.getLastname());
        //holder.price.setText("Rs."+companyDetailsModel.getAboutCompany());
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+staffDetailsModel.getProfilePic()).
                resize(400, 400).centerCrop().into(holder.staffimage);
    }

    @Override
    public int getItemCount() {
        return staffDetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView staffimage;
        TextView tv_firstname,tv_lastname,tv_staffemail,tv_staffnumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            staffimage = itemView.findViewById(R.id.staffimage);
            tv_firstname = itemView.findViewById(R.id.firstname);
            tv_lastname = itemView.findViewById(R.id.lastname);
            tv_staffemail = itemView.findViewById(R.id.staffemail);
            tv_staffnumber = itemView.findViewById(R.id.staffnumber);
        }
    }
}
