package com.example.cadappforuser.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.model.CompanyAddServiceModel;
import com.example.cadappforuser.model.ServicesListModel;

import java.util.List;

public class CompanyAddServiceAdapater extends RecyclerView.Adapter<CompanyAddServiceAdapater.ViewHolder> {

    Context context;
    List<CompanyAddServiceModel> companyAddServiceModels;
    @NonNull
    @Override
    public CompanyAddServiceAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAddServiceAdapater.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
