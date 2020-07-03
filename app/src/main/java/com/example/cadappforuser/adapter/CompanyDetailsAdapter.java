package com.example.cadappforuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_CompanyNewProfile;
import com.example.cadappforuser.Act_CompanyPersonalProfileEdit;
import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ServiceDescription;
import com.example.cadappforuser.SqliteDatabase.FavDB;
import com.example.cadappforuser.model.CompanyAddServiceModel;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CompanyDetailsAdapter extends RecyclerView.Adapter<CompanyDetailsAdapter.ViewHolder> {

    Context context;
    List<CompanyDetailsModel> companyDetailsModels;
    Activity activity;
    int count;
    Act_Session act_session;

    public CompanyDetailsAdapter(Context context, List<CompanyDetailsModel> companyDetailsModels, Activity activity, Act_Session act_session) {
        this.context = context;
        this.companyDetailsModels = companyDetailsModels;
        this.activity = activity;
        this.act_session = act_session;
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

                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
    return companyDetailsModels.size();
    }



public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_companyname;
        RatingBar ratingBar;
        ImageView companyimageview;
       ItemClickListner itemClickListner;


    public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_companyname = itemView.findViewById(R.id.companyname);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            companyimageview =itemView.findViewById(R.id.companyimageview);
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
