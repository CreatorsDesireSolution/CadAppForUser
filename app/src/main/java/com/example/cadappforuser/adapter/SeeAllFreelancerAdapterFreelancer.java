package com.example.cadappforuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_FreelancerProfileOnlyShow;
import com.example.cadappforuser.FreelancerDetailsForCompay;
import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.model.FreelancerDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeeAllFreelancerAdapterFreelancer extends RecyclerView.Adapter<SeeAllFreelancerAdapterFreelancer.ViewHolder>

{
    Context context;
    List<FreelancerDetailsModel> companyDetailsModels;

    public SeeAllFreelancerAdapterFreelancer(Context context, List<FreelancerDetailsModel> companyDetailsModels) {
        this.context = context;
        this.companyDetailsModels = companyDetailsModels;
    }

    @NonNull
    @Override
    public SeeAllFreelancerAdapterFreelancer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.freelancerall,parent,false);
        SeeAllFreelancerAdapterFreelancer.ViewHolder viewHolder=new SeeAllFreelancerAdapterFreelancer.ViewHolder(view);
        return  viewHolder;      }

    @Override
    public void onBindViewHolder(@NonNull SeeAllFreelancerAdapterFreelancer.ViewHolder holder, int position) {
        FreelancerDetailsModel companyDetailsModel = companyDetailsModels.get(position);
        // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.tv_companyname.setText(companyDetailsModel.getFirstname());
        holder.location_address.setText(companyDetailsModel.getAddress());
        holder.kilometer.setText(companyDetailsModel.getKm()+"KM");
        holder.tv_lastname.setText(companyDetailsModel.getLastname());
        //holder.price.setText("Rs."+companyDetailsModel.getAboutCompany());
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyDetailsModel.getProfilePic()).
                resize(400, 400).centerCrop().into(holder.companyimageview);

        final String firstname = companyDetailsModel.getFirstname();
        final String lastname = companyDetailsModel.getLastname();
        final String email = companyDetailsModel.getEmail();
        final String number = companyDetailsModel.getMobilenumber();
        final String address = companyDetailsModel.getAddress();
        final String aboutcompany = companyDetailsModel.getAboutYourself();
        final String ageofcompany =companyDetailsModel.getExperience();
        final String id= companyDetailsModel.getId();
        final String image= companyDetailsModel.getProfilePic();


        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Intent intent=new Intent(context, Act_FreelancerProfileOnlyShow.class);
                intent.putExtra("firstname",firstname);
                intent.putExtra("lastname",lastname);
                intent.putExtra("email",email);
                intent.putExtra("number",number);
                intent.putExtra("address",address);
                intent.putExtra("aboutcompnay",aboutcompany);
                intent.putExtra("ageofcompany",ageofcompany);
                intent.putExtra("image",image);
                intent.putExtra("id",id);

                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return companyDetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {

        TextView tv_companyname,kilometer,location_address,tv_lastname;
        ImageView ratingBar;
        ImageView companyimageview;
        ItemClickListner itemClickListner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_companyname = itemView.findViewById(R.id.firstname);
            tv_lastname = itemView.findViewById(R.id.lastname);
           // ratingBar = itemView.findViewById(R.id.ratingbar);
            companyimageview =itemView.findViewById(R.id.companyimageview);
            location_address= itemView.findViewById(R.id.location_address);
            kilometer= itemView.findViewById(R.id.kilometer);
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



