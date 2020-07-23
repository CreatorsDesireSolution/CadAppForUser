package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Appointment.FreelancerAppointment;
import com.example.cadappforuser.FreelancerViewOrder;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.example.cadappforuser.model.FreelancerDetailsModel;
import com.example.cadappforuser.model.FreelancerOrderModel;

import java.util.List;

public class FreelancerAppointmentAdapter extends RecyclerView.Adapter<FreelancerAppointmentAdapter.ViewHolder> {

    Context context;
    List<FreelancerOrderModel> freelancerOrderModels;

    public FreelancerAppointmentAdapter(Context context, List<FreelancerOrderModel> freelancerOrderModels) {
        this.context = context;
        this.freelancerOrderModels = freelancerOrderModels;
    }

    @NonNull
    @Override
    public FreelancerAppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.freelancerappointmnet,parent,false);
        FreelancerAppointmentAdapter.ViewHolder viewHolder=new FreelancerAppointmentAdapter.ViewHolder(view);
        return  viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull FreelancerAppointmentAdapter.ViewHolder holder, int position) {

        final FreelancerOrderModel freelancerOrderModel = freelancerOrderModels.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.tv_name.setText(freelancerOrderModel.getCustomer_fname());
        holder.tv_sprice.setText(freelancerOrderModel.getPrice()+"Rs.");
        holder.tv_lastname.setText(freelancerOrderModel.getCustomer_lname());

        holder.btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FreelancerViewOrder.class);
                intent.putExtra("order_id",freelancerOrderModel.getOrderId());
                intent.putExtra("customer_id",freelancerOrderModel.getCustomer_id());
                context.startActivity(intent);
            }
        });
//       // Log.e("companyname",freelancerOrderModel.getCompanyname());


    }

    @Override
    public int getItemCount() {
       return freelancerOrderModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_sprice,tv_location,tv_lastname;
        ImageView imageView;
        Button btn_approve,btn_remove,btn_details;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.firstname);
            tv_sprice = itemView.findViewById(R.id.sprice);
            btn_details = itemView.findViewById(R.id.btn_details);
            tv_lastname = itemView.findViewById(R.id.lastname);


        }
    }
}
