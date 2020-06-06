package com.example.cadappforuser.companyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.companymodel.CompanyServicesFeatureAndCategoriesHomeModel;

import java.util.List;

public class CompanyServicesFeturesAndCategoriesHomeAdapter extends RecyclerView.Adapter<CompanyServicesFeturesAndCategoriesHomeAdapter.FeatureViewHolder> {

    Context context;
    List<CompanyServicesFeatureAndCategoriesHomeModel> companyServicesFeatureAndCategoriesHomeModelList;

    public CompanyServicesFeturesAndCategoriesHomeAdapter(Context context, List<CompanyServicesFeatureAndCategoriesHomeModel> companyServicesFeatureAndCategoriesHomeModelList) {
        this.context = context;
        this.companyServicesFeatureAndCategoriesHomeModelList = companyServicesFeatureAndCategoriesHomeModelList;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_feature,parent,false);
        FeatureViewHolder featureViewHolder=new FeatureViewHolder(view);
        return  featureViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {

        CompanyServicesFeatureAndCategoriesHomeModel companyServicesFeatureAndCategoriesHomeModel = companyServicesFeatureAndCategoriesHomeModelList.get(position);
        holder.txtSample.setText(companyServicesFeatureAndCategoriesHomeModel.getSample());
        holder.txtPrice.setText("Rs."+ companyServicesFeatureAndCategoriesHomeModel.getServicePrice());
        holder.txtName.setText(companyServicesFeatureAndCategoriesHomeModel.getServiceName());
        holder.imageView.setImageResource(companyServicesFeatureAndCategoriesHomeModel.getImage());

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
               // context.startActivity(new Intent(context, ServiceDescription.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return companyServicesFeatureAndCategoriesHomeModelList.size();
    }

    public class FeatureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        TextView txtPrice,txtName,txtSample;
        ItemClickListner itemClickListner;
        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.facialImage);
            txtName=itemView.findViewById(R.id.serviceName);
            txtPrice=itemView.findViewById(R.id.servicePrice);
            txtSample=itemView.findViewById(R.id.sample);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            this.itemClickListner.onItemClickListner(view,getLayoutPosition());
        }
        public void setItemClickListner(ItemClickListner ic){
            this.itemClickListner=ic;
        }

    }
}
