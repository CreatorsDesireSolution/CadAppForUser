package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.CustomerViewOrder;
import com.example.cadappforuser.FreelancerViewOrder;
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

        final OrderSummaryModel freelancerOrderModel = orderSummaryModelList.get(position);

        holder.tv_name.setText(freelancerOrderModel.getServiceName());
        holder.tv_sprice.setText(freelancerOrderModel.getPrice()+"Rs.");
        holder.tv_statment.setText(freelancerOrderModel.getStatement());

        holder.btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomerViewOrder.class);
                intent.putExtra("order_id",freelancerOrderModel.getOrderId());
                intent.putExtra("flag",freelancerOrderModel.getFlag());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderSummaryModelList.size();
    }

    public class OrderSViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name,tv_sprice,tv_statment,tv_location,tv_lastname;
        ImageView imageView;
        Button btn_approve,btn_remove,btn_details;        public OrderSViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_name = itemView.findViewById(R.id.firstname);
            tv_sprice = itemView.findViewById(R.id.sprice);
            btn_details = itemView.findViewById(R.id.btn_details);
            tv_statment=itemView.findViewById(R.id.statement);

        }
    }
}
