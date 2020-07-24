package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.FreelancerViewOrder;
import com.example.cadappforuser.PreviousBookingDetail;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.FreelancerViewOrderModel;
import com.example.cadappforuser.model.PreviousBookingMOdel;

import java.util.List;

public class PreviousBookingAdapter extends RecyclerView.Adapter<PreviousBookingAdapter.ViewHolder> {
  Context context;
  List<PreviousBookingMOdel> models;

    public PreviousBookingAdapter(Context context, List<PreviousBookingMOdel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public PreviousBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.previous_booking,parent,false);
        PreviousBookingAdapter.ViewHolder viewHolder=new PreviousBookingAdapter.ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousBookingAdapter.ViewHolder holder, int position) {

        final PreviousBookingMOdel previousBookingMOdel = models.get(position);
        holder.tv_firstname.setText(previousBookingMOdel.getCustomerFname());
        holder.lastname.setText(previousBookingMOdel.getCustomerLname());
        holder.service_name.setText(previousBookingMOdel.getServiceName());
        holder.price.setText(previousBookingMOdel.getPrice());


        holder.seemoredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PreviousBookingDetail.class);
                intent.putExtra("order_id",previousBookingMOdel.getOrderId());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_firstname,lastname,service_name,price,seemoredetails;
        ImageView companyimageview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_firstname = itemView.findViewById(R.id.firstname);
            lastname=itemView.findViewById(R.id.lastname);
            service_name = itemView.findViewById(R.id.service_name);
            price= itemView.findViewById(R.id.service_price);
            companyimageview=itemView.findViewById(R.id.companyimageview);
            seemoredetails= itemView.findViewById(R.id.seemoredetails);
        }
    }
}
