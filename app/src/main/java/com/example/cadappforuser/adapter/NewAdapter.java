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
import androidx.appcompat.widget.TintTypedArray;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_FreelancerProfile;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceModel.AllServiceModel;
import com.example.cadappforuser.ServiceModel.NewModel;
import com.squareup.picasso.Picasso;

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
        holder.kilometer.setText(newModel.getKm()+"KM");

       // holder.ratingBar.setRating(newModel.getRating());
       // holder.facialImageFreelancer.setImageResource(newModel.getImage());

        Picasso.get().load(newModel.getImage()).resize(400, 400).centerCrop().into(holder.facialImageFreelancer);

        final String name = newModel.getName();
        final String lastname = newModel.getLastname();
        final String email = newModel.getEmail();
        final String mobilenumber = newModel.getMobile();
        //String gender = object.getString("gender");
        final String address = newModel.getAddress();
        final String experience=newModel.getAddress();
        final String aboutus=newModel.getAbout_yourself();
        final  String image=newModel.getImage();
        final String id = newModel.getId();
        final String userImage=newModel.getUserImage();


     holder.setItemClickListner(new ItemClickListner() {
         @Override
         public void onItemClickListner(View v, int position) {
               Intent intent=new Intent(context,Act_FreelancerProfile.class);
               intent.putExtra("name",name);
               intent.putExtra("last",lastname);
               intent.putExtra("email",email);
               intent.putExtra("mobile",mobilenumber);
               intent.putExtra("address",address);
               intent.putExtra("experience",experience);
               intent.putExtra("aboutus",aboutus);
               intent.putExtra("image",image);
               intent.putExtra("id",id);
               intent.putExtra("user",userImage);

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
        TextView tv_freelancername,userrating,kilometer;
        ImageView ratingBar;
        private ItemClickListner itemClickListner;

        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            facialImageFreelancer = itemView .findViewById(R.id.facialImageFreelancer);
            tv_freelancername = itemView.findViewById(R.id.freelancer);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            userrating=itemView.findViewById(R.id.userrating);
            kilometer=itemView.findViewById(R.id.kilometer);
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
