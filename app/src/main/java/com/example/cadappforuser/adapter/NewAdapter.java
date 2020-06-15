package com.example.cadappforuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceModel.AllServiceModel;
import com.example.cadappforuser.ServiceModel.NewModel;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {

    Context context;
    List<NewModel> newModels;

    public NewAdapter(Context context, List<NewModel> newModels) {
        this.context = context;
        this.newModels = newModels;
    }

    @NonNull
    @Override
    public NewAdapter.NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customservice,parent,false);
        NewAdapter.NewViewHolder newViewHolder=new NewAdapter.NewViewHolder(view);
        return  newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewAdapter.NewViewHolder holder, int position) {

        NewModel newModel=newModels.get(position);
        holder.tv_freelancername.setText(newModel.getName());
       // holder.ratingBar.setRating();
        holder.facialImageFreelancer.setImageResource(newModel.getImage());


    }

    @Override
    public int getItemCount() {
        return newModels.size();
    }

    public class NewViewHolder extends RecyclerView.ViewHolder {
        ImageView facialImageFreelancer;
        TextView tv_freelancername;
        RatingBar ratingBar;
        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            facialImageFreelancer = itemView .findViewById(R.id.facialImageFreelancer);
            tv_freelancername = itemView.findViewById(R.id.freelancer);
            ratingBar = itemView.findViewById(R.id.ratingbar);
        }
    }
}
