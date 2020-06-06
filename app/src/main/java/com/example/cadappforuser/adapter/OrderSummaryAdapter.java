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
import com.example.cadappforuser.model.OrderSummaryModel;

import java.util.List;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.OrderSViewHolder> {

    Context context;
    List<OrderSummaryModel> orderSummaryModelList;

    public OrderSummaryAdapter(Context context, List<OrderSummaryModel> orderSummaryModelList) {
        this.context = context;
        this.orderSummaryModelList = orderSummaryModelList;
    }

    @NonNull
    @Override
    public OrderSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_for_order_summary,parent,false);
        OrderSViewHolder orderSViewHolder=new OrderSViewHolder(view);
        return  orderSViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderSViewHolder holder, int position) {

        OrderSummaryModel orderSummaryModel=orderSummaryModelList.get(position);
        holder.txtSample.setText(orderSummaryModel.getOrdSample());
        holder.txtPrice.setText("Rs."+orderSummaryModel.getOrdPrice());
        holder.txtName.setText(orderSummaryModel.getOrdName());
    }

    @Override
    public int getItemCount() {
        return orderSummaryModelList.size();
    }

    public class OrderSViewHolder extends RecyclerView.ViewHolder{

        TextView txtPrice,txtName,txtSample;
        public OrderSViewHolder(@NonNull View itemView)
        {
            super(itemView);
           txtName=itemView.findViewById(R.id.ordName);
           txtPrice=itemView.findViewById(R.id.ordPrice);
           txtSample=itemView.findViewById(R.id.ordample);
        }
    }
}
