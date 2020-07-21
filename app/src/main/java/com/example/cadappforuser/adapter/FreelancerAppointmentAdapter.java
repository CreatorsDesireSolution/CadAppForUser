package com.example.cadappforuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.R;
import com.example.cadappforuser.model.FreelancerDetailsModel;

import java.util.List;

public class FreelancerAppointmentAdapter extends RecyclerView.Adapter<FreelancerAppointmentAdapter.ViewHolder> {

    Context context;
    List<FreelancerDetailsModel> freelancerDetailsModelList;

    public FreelancerAppointmentAdapter(Context context, List<FreelancerDetailsModel> freelancerDetailsModelList) {
        this.context = context;
        this.freelancerDetailsModelList = freelancerDetailsModelList;
    }

    @NonNull
    @Override
    public FreelancerAppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.freelancerappointmnet,parent,false);
        FreelancerAppointmentAdapter.ViewHolder viewHolder=new FreelancerAppointmentAdapter.ViewHolder(view);
        return  viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull FreelancerAppointmentAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_location;
        ImageView imageView;
        Button btn_approve,btn_remove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_location = itemView.findViewById(R.id.location_address);
            btn_approve = itemView.findViewById(R.id.btn_approve);
            btn_remove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
