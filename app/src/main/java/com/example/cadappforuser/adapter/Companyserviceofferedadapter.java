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
import com.example.cadappforuser.model.CompanyAddServiceModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Companyserviceofferedadapter extends RecyclerView.Adapter<Companyserviceofferedadapter.ViewHolder> {

    Context context;
    List<CompanyAddServiceModel> companyAddServiceModels;

    public Companyserviceofferedadapter(Context context, List<CompanyAddServiceModel> companyAddServiceModels) {
        this.context = context;
        this.companyAddServiceModels = companyAddServiceModels;
    }

    @NonNull
    @Override
    public Companyserviceofferedadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.compnayoffered, parent, false);
        Companyserviceofferedadapter.ViewHolder viewHolder = new Companyserviceofferedadapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Companyserviceofferedadapter.ViewHolder holder, int position) {

        CompanyAddServiceModel companyAddServiceModel = companyAddServiceModels.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.sample.setText(companyAddServiceModels.get(position).getDescription());
        holder.name.setText(companyAddServiceModel.getServiceName());
        holder.price.setText("Rs." + companyAddServiceModel.getSetPrice());
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/" + companyAddServiceModel.getServiceImage()).resize(400, 400).centerCrop().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return companyAddServiceModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, fevicon;
        TextView price, name, sample;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.serviceListServiceImage);
            price = itemView.findViewById(R.id.serviceListServicePrice);
            name = itemView.findViewById(R.id.serviceListServiceName);
            sample = itemView.findViewById(R.id.serviceListServiceSample);
        }
    }
}
