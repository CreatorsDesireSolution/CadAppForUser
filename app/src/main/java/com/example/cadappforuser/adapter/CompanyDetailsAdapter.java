package com.example.cadappforuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_CompanyNewProfile;
import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CompanyDetailsAdapter extends RecyclerView.Adapter<CompanyDetailsAdapter.ViewHolder> implements Filterable {

    Context context;
    List<CompanyDetailsModel> companyDetailsModels;
    Activity activity;
    int count;
    Act_Session act_session;
    List<CompanyDetailsModel> Allcompanydeatil;

    public CompanyDetailsAdapter(Context context, List<CompanyDetailsModel> companyDetailsModels, Activity activity, Act_Session act_session) {
        this.context = context;
        this.companyDetailsModels = companyDetailsModels;
        this.activity = activity;
        this.act_session = act_session;
        this.Allcompanydeatil = companyDetailsModels;
    }

    @NonNull
    @Override
    public CompanyDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.company_details,parent,false);
        CompanyDetailsAdapter.ViewHolder viewHolder=new CompanyDetailsAdapter.ViewHolder(view);
        return  viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull CompanyDetailsAdapter.ViewHolder holder, int position) {
        CompanyDetailsModel companyDetailsModel = companyDetailsModels.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.tv_companyname.setText(companyDetailsModel.getCompanyname());
        //holder.price.setText("Rs."+companyDetailsModel.getAboutCompany());
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyDetailsModel.getProfilePic()).
                resize(400, 400).centerCrop().into(holder.companyimageview);

        final String companyname = companyDetailsModel.getCompanyname();
        final String email = companyDetailsModel.getEmail();
        final String number = companyDetailsModel.getMobilenumber();
        final String address = companyDetailsModel.getAddress();
        final String aboutcompany = companyDetailsModel.getAboutCompany();
        final String ageofcompany =companyDetailsModel.getTotalYearEstablishment();
        final  String no_of_staff = companyDetailsModel.getNoOfStaff();
        final  String companyid=companyDetailsModel.getId();
        final String image= Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyDetailsModel.getProfilePic()).
                resize(400, 400).centerCrop().toString();

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent=new Intent(context, Act_CompanyNewProfile.class);
                intent.putExtra("companyname",companyname);
                intent.putExtra("email",email);
                intent.putExtra("number",number);
                intent.putExtra("address",address);
                intent.putExtra("aboutcompnay",aboutcompany);
                intent.putExtra("ageofcompany",ageofcompany);
                intent.putExtra("no_of_staff",no_of_staff);
                intent.putExtra("image",image);
                intent.putExtra("id",companyid);

                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
    return companyDetailsModels.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<CompanyDetailsModel> filterList = new ArrayList<>();
            if (charSequence.toString() == null) {
                filterList.addAll(companyDetailsModels);
            } else {
                String serachStr = charSequence.toString().toUpperCase();
                for (CompanyDetailsModel servicesS : Allcompanydeatil) {
                    if (servicesS.getCompanyname().toUpperCase().contains(serachStr)) {
                        filterList.add(servicesS);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            companyDetailsModels.clear();
            companyDetailsModels.addAll((List<CompanyDetailsModel>)results.values);
            notifyDataSetChanged();

        }
    };



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_companyname,userrating;
        ImageView ratingBar;
        ImageView companyimageview;

       ItemClickListner itemClickListner;


    public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_companyname = itemView.findViewById(R.id.companyname);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            companyimageview =itemView.findViewById(R.id.companyimageview);
        userrating=itemView.findViewById(R.id.userrating);
            itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.itemClickListner.onItemClickListner(v,getLayoutPosition());

    }

    public void setItemClickListner(ItemClickListner ic){
        this.itemClickListner=ic;
    }

}
}
