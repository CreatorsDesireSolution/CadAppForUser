package com.example.cadappforuser.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.FreelancerViewOrder;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.example.cadappforuser.model.FreelancerViewOrderModel;

import java.util.ArrayList;
import java.util.List;

public class FreelancerViewOrderAdapter extends RecyclerView.Adapter<FreelancerViewOrderAdapter.ViewHolder> {
    Context context;
    ArrayList<FreelancerViewOrderModel> freelancerViewOrderModelList;

    public FreelancerViewOrderAdapter(Context context, ArrayList<FreelancerViewOrderModel> freelancerViewOrderModelList) {
        this.context = context;
        this.freelancerViewOrderModelList = freelancerViewOrderModelList;
    }

    @NonNull
    @Override
    public FreelancerViewOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.freelancerview,parent,false);
        FreelancerViewOrderAdapter.ViewHolder viewHolder=new FreelancerViewOrderAdapter.ViewHolder(view);
        return  viewHolder;      }

    @Override
    public void onBindViewHolder(@NonNull FreelancerViewOrderAdapter.ViewHolder holder, int position) {

        FreelancerViewOrderModel freelancerViewOrderModel = freelancerViewOrderModelList.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.tv_customerfname.setText(freelancerViewOrderModel.getCustomerFname());
        holder.tv_customerlastname.setText(freelancerViewOrderModel.getCustomerLname());
        holder.location.setText(freelancerViewOrderModel.getCustomerAddress());



    }

    @Override
    public int getItemCount() {
        return freelancerViewOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_customerfname, tv_customerlastname,location, userrating,kilometer;
        ImageView companyimageview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_customerfname = itemView.findViewById(R.id.firstname);
            tv_customerlastname = itemView.findViewById(R.id.lastname);
            location = itemView.findViewById(R.id.location_address);
        }
    }
}
