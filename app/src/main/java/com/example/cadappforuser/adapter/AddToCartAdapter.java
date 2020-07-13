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
import com.example.cadappforuser.SqliteDatabase.FavDB;
import com.example.cadappforuser.model.AddToCartModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {

    Context context;
    List<AddToCartModel> addToCartModels;
    private FavDB favDB;


    public AddToCartAdapter(Context context, List<AddToCartModel> addToCartModels) {
        this.context = context;
        this.addToCartModels = addToCartModels;

    }

    @NonNull
    @Override
    public AddToCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart,parent,false);
        AddToCartAdapter.ViewHolder viewHolder=new AddToCartAdapter.ViewHolder(view);
        favDB = new FavDB(context);
        return  viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull AddToCartAdapter.ViewHolder holder, int position) {
        AddToCartModel fevListModel=addToCartModels.get(position);
        holder.sample.setText(fevListModel.getSample());
        holder.name.setText(fevListModel.getName());
        holder.price.setText("Rs."+fevListModel.getPrice());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+fevListModel.getImage()).
                resize(400, 400).centerCrop().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return addToCartModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView,fevicon;
        TextView price,name,sample;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
