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
import com.example.cadappforuser.model.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<CartModel> cartModelList;

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_for_cart,parent,false);
         CartViewHolder cartViewHolder=new CartViewHolder(view);
         return  cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        CartModel cartModel=cartModelList.get(position);
        holder.txtSample.setText(cartModel.getSerSample());
        holder.txtPrice.setText("Rs."+cartModel.getSerPrice());
        holder.txtName.setText(cartModel.getSerName());
        holder.serImage.setImageResource(cartModel.getSerImage());


    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView serImage;
        TextView txtPrice,txtName,txtSample;

        public CartViewHolder(@NonNull View itemView)
        {
            super(itemView);

            serImage=itemView.findViewById(R.id.serImage);
            txtName=itemView.findViewById(R.id.serName);
            txtPrice=itemView.findViewById(R.id.serPrice);
            txtSample=itemView.findViewById(R.id.serSample);


        }
    }
}
