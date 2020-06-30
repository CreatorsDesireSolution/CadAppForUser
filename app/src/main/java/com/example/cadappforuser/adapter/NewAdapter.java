package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_FreelancerProfile;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceModel.AllServiceModel;
import com.example.cadappforuser.ServiceModel.NewModel;

import java.util.ArrayList;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> implements Filterable {

    Context context;
    List<NewModel> newModels;
    List<NewModel> newModelsAll;

    public NewAdapter(Context context, List<NewModel> newModels) {
        this.context = context;
        this.newModels = newModels;
        this.newModelsAll=new ArrayList<>(newModels);
    }

    @NonNull
    @Override
    public NewAdapter.NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout1111,parent,false);
        NewAdapter.NewViewHolder newViewHolder=new NewAdapter.NewViewHolder(view);
        return  newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewAdapter.NewViewHolder holder, int position) {


        NewModel newModel=newModels.get(position);
        holder.tv_freelancername.setText(newModel.getName());
        holder.ratingBar.setRating(newModel.getRating());
        holder.facialImageFreelancer.setImageResource(newModel.getImage());

        final String name = newModel.getName();
       // String name = object.getString("firstname");
        //String lastname = object.getString("lastname");
        //String email = object.getString("email");
        //String mobilenumber = object.getString("mobilenumber");
        //String gender = object.getString("gender");
        //String address = object.getString("address");


     holder.setItemClickListner(new ItemClickListner() {
         @Override
         public void onItemClickListner(View v, int position) {
             Intent intent=new Intent(context,Act_FreelancerProfile.class);
             intent.putExtra("name",name);
             context.startActivity(intent);
             //context.startActivity(new Intent(context, Act_FreelancerProfile.class));
         }
     });
    }

    @Override
    public int getItemCount() {
        return newModels.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<NewModel> filterList=new ArrayList<>();
            if(charSequence.toString()==null){
                filterList.addAll(newModelsAll);
            }else{
                String serachStr=charSequence.toString().toUpperCase();
                for (NewModel servicesS: newModelsAll){
                    if(servicesS.getName().toUpperCase().contains(serachStr)){
                        filterList.add(servicesS);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filterList;
            return filterResults;
        }

        //run on ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
           newModels.clear();
          newModels.addAll((List<NewModel>)filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class NewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView facialImageFreelancer;
        TextView tv_freelancername;
        RatingBar ratingBar;
        private ItemClickListner itemClickListner;

        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            facialImageFreelancer = itemView .findViewById(R.id.facialImageFreelancer);
            tv_freelancername = itemView.findViewById(R.id.freelancer);
            ratingBar = itemView.findViewById(R.id.ratingbar);
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
