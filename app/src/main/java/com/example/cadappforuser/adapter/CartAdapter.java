package com.example.cadappforuser.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.CartActivity;
import com.example.cadappforuser.R;
import com.example.cadappforuser.SqliteDatabase.Myhelper;
import com.example.cadappforuser.model.CartModel;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(@NonNull final CartViewHolder holder,final int position) {

        CartModel cartModel=cartModelList.get(position);
        final int item_id=cartModel.getSerId();
        final Myhelper myhelper=new Myhelper(context);
        final int[] number = {cartModel.getQty()};
        final SQLiteDatabase database=myhelper.getWritableDatabase();
        Picasso.get().load(cartModel.getSerImage()).resize(400, 400).centerCrop().into(holder.serImage);
        holder.txtName.setText(cartModel.getSerName());
        holder.txtSample.setText(cartModel.getSerSample());
        holder.txtPrice.setText("Rs."+cartModel.getSerPrice());
        holder.qty.setText(""+cartModel.getQty());
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number[0] == 1) {
                    holder.qty.setText(""+number[0]);
                }
                if (number[0] > 1) {
                    number[0] = number[0] - 1;
                    database.execSQL("UPDATE CART SET QTY = "+number[0]+" WHERE _id="+item_id);
                    ((CartActivity)context).resetGraph(context);
                    holder.qty.setText(""+number[0]);
                }
            }
        });
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 number[0] = number[0] + 1;
                 database.execSQL("UPDATE CART SET QTY = "+number[0]+" WHERE _id="+item_id);
                 holder.qty.setText(""+number[0]);
                Toast.makeText(context, ""+number[0], Toast.LENGTH_SHORT).show();
                ((CartActivity)context).resetGraph(context);
            }
        });

        holder.serDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.execSQL("delete from CART where _id="+item_id);
                database.close();
                cartModelList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartModelList.size());
                ((CartActivity)context).resetGraph(context);
                Toast.makeText(context, "Item Succesfully Removed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView serImage,serDelete,btnMinus,btnAdd;
        TextView txtPrice,txtName,txtSample,qty;

        public CartViewHolder(@NonNull View itemView)
        {
            super(itemView);
            serImage=itemView.findViewById(R.id.serImage);
            txtName=itemView.findViewById(R.id.serName);
            txtPrice=itemView.findViewById(R.id.serPrice);
            txtSample=itemView.findViewById(R.id.serSample);
            serDelete=itemView.findViewById(R.id.icDelete);
            btnAdd=itemView.findViewById(R.id.btnAdd);
            btnMinus=itemView.findViewById(R.id.btnMinus);
            qty=itemView.findViewById(R.id.serQuantity);
        }
    }
}
