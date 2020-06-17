package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_FreelancerProfile;
import com.example.cadappforuser.CartActivity;
import com.example.cadappforuser.HomePageActivity;
import com.example.cadappforuser.R;
import com.example.cadappforuser.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HomeSearchAdapter extends RecyclerView.Adapter<HomeSearchAdapter.ViewHolder> implements Filterable {
    private static final String TAG = "RecyclerAdapter";
    List<String> serviceList;
    List<String> serviceListAll;
    Context context;


    public HomeSearchAdapter(List<String> moviesList, Context context) {
        this.serviceList = moviesList;
        serviceListAll = new ArrayList<>();
        serviceListAll.addAll(moviesList);
        this.context = context;
    }


    @NonNull
    @Override
    public HomeSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item11, parent, false);
        HomeSearchAdapter.ViewHolder viewHolder = new HomeSearchAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSearchAdapter.ViewHolder holder, int position) {

        holder.textView.setText(serviceList.get(position));


    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    @Override
    public Filter getFilter() {

        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<String> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(serviceListAll);
            } else {
                for (String service: serviceListAll) {
                    if (service.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(service);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            serviceList.clear();
            serviceList.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(v.getContext(), serviceList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            v.getContext().startActivity(new Intent(context, Act_FreelancerProfile.class));



        }
    }
}



