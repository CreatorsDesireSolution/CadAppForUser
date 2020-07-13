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
import com.example.cadappforuser.model.ServicesListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddFevAdapter extends RecyclerView.Adapter<AddFevAdapter.AddFevAdapterViewHolder> {

    Context context;
    List<FevListModel> fevListModels;
    private FavDB favDB;

    public AddFevAdapter(Context context, List<FevListModel> fevListModels) {
        this.context = context;
        this.fevListModels = fevListModels;
    }

    @NonNull
    @Override
    public AddFevAdapter.AddFevAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_fev,parent,false);
        AddFevAdapter.AddFevAdapterViewHolder viewHolder=new AddFevAdapter.AddFevAdapterViewHolder(view);
        favDB = new FavDB(context);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddFevAdapter.AddFevAdapterViewHolder holder, int position) {

        FevListModel fevListModel=fevListModels.get(position);
        holder.sample.setText(fevListModel.getSample());
        holder.name.setText(fevListModel.getName());
        holder.price.setText("Rs."+fevListModel.getPrice());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+fevListModel.getImage()).
                resize(400, 400).centerCrop().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return fevListModels.size();
    }

    public class AddFevAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView,fevicon;
        TextView price,name,sample;
        public AddFevAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.serviceListServiceImage);
            price=itemView.findViewById(R.id.serviceListServicePrice);
            name=itemView.findViewById(R.id.serviceListServiceName);
            fevicon=itemView.findViewById(R.id.fevicon);
            sample=itemView.findViewById(R.id.serviceListServiceSample);

            fevicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    FevListModel fevListModel=fevListModels.get(position);
                    favDB.remove_fav(fevListModel.getKey_id());
                    removeItem(position);
                }
            });

        }
    }

    private void removeItem(int position) {
        fevListModels.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,fevListModels.size());
    }
}
