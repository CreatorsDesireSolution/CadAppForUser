package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_CompanyNewProfile;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.CompanyDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeeAllCompanyAdapter extends RecyclerView.Adapter<SeeAllCompanyAdapter.ViewHolder> {


    Context context;
    List<CompanyDetailsModel> companyDetailsModels;

    public SeeAllCompanyAdapter(Context context, List<CompanyDetailsModel> companyDetailsModels) {
        this.context = context;
        this.companyDetailsModels = companyDetailsModels;
    }

    @NonNull
    @Override
    public SeeAllCompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.companyall, parent, false);
        SeeAllCompanyAdapter.ViewHolder viewHolder = new SeeAllCompanyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeeAllCompanyAdapter.ViewHolder holder, int position) {

        CompanyDetailsModel companyDetailsModel = companyDetailsModels.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.tv_companyname.setText(companyDetailsModel.getCompanyname());
        Log.e("companyname",companyDetailsModel.getCompanyname());
        holder.location.setText(companyDetailsModel.getAddress());
        holder.kilometer.setText(companyDetailsModel.getKm()+"KM");
        //holder.tv_lastname.setText(companyDetailsModel.getLastname());
        //holder.price.setText("Rs."+companyDetailsModel.getAboutCompany());
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/" + companyDetailsModel.getProfilePic()).
                resize(400, 400).centerCrop().into(holder.companyimageview);
        final String companyname = companyDetailsModel.getCompanyname();
        final String email = companyDetailsModel.getEmail();
        final String number = companyDetailsModel.getMobilenumber();
        final String address = companyDetailsModel.getAddress();
        final String aboutcompany = companyDetailsModel.getAboutCompany();
        final String ageofcompany =companyDetailsModel.getTotalYearEstablishment();
        final  String no_of_staff = companyDetailsModel.getNoOfStaff();
        final  String companyid=companyDetailsModel.getId();
        final String image= companyDetailsModel.getProfilePic();



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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_companyname, tv_lastname,location, userrating,kilometer;
        ImageView ratingBar;
        ImageView companyimageview;
        ItemClickListner itemClickListner;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_companyname = itemView.findViewById(R.id.firstname);
            location = itemView.findViewById(R.id.location_address);
            kilometer = itemView.findViewById(R.id.kilometer);
           // tv_lastname = itemView.findViewById(R.id.lastname);
           // ratingBar = itemView.findViewById(R.id.ratingbar);
            companyimageview = itemView.findViewById(R.id.companyimageview);
          //  userrating = itemView.findViewById(R.id.userrating);
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
