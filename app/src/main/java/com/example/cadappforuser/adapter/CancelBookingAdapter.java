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
import com.example.cadappforuser.model.CancelBookingModel;
import com.example.cadappforuser.model.FreelancerViewOrderModel;

import java.util.List;

public class CancelBookingAdapter extends RecyclerView.Adapter<CancelBookingAdapter.ViewHolder> {
    Context context;
    List<CancelBookingModel> models;

    public CancelBookingAdapter(Context context, List<CancelBookingModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public CancelBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cancel_booking,parent,false);
        CancelBookingAdapter.ViewHolder viewHolder=new CancelBookingAdapter.ViewHolder(view);
        return  viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull CancelBookingAdapter.ViewHolder holder, int position) {
        CancelBookingModel freelancerViewOrder = models.get(position);
        holder.tv_firstname.setText(freelancerViewOrder.getCustomerFname());
        holder.lastname.setText(freelancerViewOrder.getCustomerLname());
        holder.service_name.setText(freelancerViewOrder.getServiceName());
        holder.price.setText(freelancerViewOrder.getPrice());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_firstname,lastname,service_name,price;
        ImageView companyimageview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_firstname = itemView.findViewById(R.id.firstname);
            lastname=itemView.findViewById(R.id.lastname);
            service_name = itemView.findViewById(R.id.service_name);
            price= itemView.findViewById(R.id.service_price);
            companyimageview=itemView.findViewById(R.id.companyimageview);
        }
    }
}
