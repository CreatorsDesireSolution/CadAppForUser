package com.example.cadappforuser.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;
    private List<Order> personUtils;

    public OrderAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_to_order, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Order pu = personUtils.get(position);
        Picasso.get().load(pu.getImageid()).resize(400,400).centerCrop().into(holder.itemimage);
        holder.product_name.setText(pu.getProductname());
        holder.brand_name.setText(pu.getBrandname());
        holder.sprice.setText(pu.getSellingPrice()+"  ");
        holder.item_qty.setText(""+pu.getQty());
    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemimage;
        TextView product_name,brand_name,pprice,sprice,item_qty;

        public ViewHolder(View itemView) {
            super(itemView);
            itemimage=itemView.findViewById(R.id.product_img);
            product_name =  itemView.findViewById(R.id.product_name);
            brand_name=itemView.findViewById(R.id.brand_name);
            sprice=itemView.findViewById(R.id.selling_price);
            item_qty=itemView.findViewById(R.id.product_qty);
        }
    }

}
