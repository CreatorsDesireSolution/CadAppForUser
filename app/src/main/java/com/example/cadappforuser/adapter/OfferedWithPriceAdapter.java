package com.example.cadappforuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.R;
import com.example.cadappforuser.model.OfferedWithPriceModel;

import java.util.List;

public class OfferedWithPriceAdapter extends RecyclerView.Adapter<OfferedWithPriceAdapter.ViewHolder> {

    Context context;
    List<OfferedWithPriceModel> offeredWithPriceModelList;

    public OfferedWithPriceAdapter(Context context, List<OfferedWithPriceModel> offeredWithPriceModelList) {
        this.context = context;
        this.offeredWithPriceModelList = offeredWithPriceModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_for_offered,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OfferedWithPriceModel offeredWithPriceModel=offeredWithPriceModelList.get(position);
        String priceo=holder.price.getText().toString();
        holder.price.setText(priceo);
        holder.policy.setText(offeredWithPriceModel.getPolicy());
        holder.name.setText(offeredWithPriceModel.getServiceName());
    }

    @Override
    public int getItemCount() {
        return offeredWithPriceModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,policy;
        EditText price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.serviceNameAdd);
            policy=itemView.findViewById(R.id.policy);
            price=itemView.findViewById(R.id.etPrice);
        }
    }
}
