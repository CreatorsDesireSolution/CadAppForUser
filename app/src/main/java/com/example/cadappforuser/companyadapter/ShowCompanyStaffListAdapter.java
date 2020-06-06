package com.example.cadappforuser.companyadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.ItemClickListner;
import com.example.cadappforuser.R;
import com.example.cadappforuser.ShowCompanyStaffProfile;
import com.example.cadappforuser.ShowStaffList;
import com.example.cadappforuser.StaffProfile;
import com.example.cadappforuser.companymodel.CompanyStaffListModel;

import java.util.List;

public class ShowCompanyStaffListAdapter extends RecyclerView.Adapter<ShowCompanyStaffListAdapter.StaffViewholder> {

    Context context;
    List<CompanyStaffListModel> companyStaffListModelList;

    public ShowCompanyStaffListAdapter(Context context, List<CompanyStaffListModel> companyStaffListModelList) {
        this.context = context;
        this.companyStaffListModelList = companyStaffListModelList;
    }

    @NonNull
    @Override
    public StaffViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_staff_list,parent,false);
        StaffViewholder staffViewholder=new StaffViewholder(view);
        return staffViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StaffViewholder holder, int position) {

        final CompanyStaffListModel companyStaffListModel=companyStaffListModelList.get(position);
       holder.mobile.setText(companyStaffListModel.getMobile());
        holder.email.setText(companyStaffListModel.getEmail());
        holder.name.setText(companyStaffListModel.getName());



        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

                Intent intent=new Intent(context, ShowCompanyStaffProfile.class);
                intent.putExtra("email",companyStaffListModel.getEmail());
                intent.putExtra("name",companyStaffListModel.getName());
                intent.putExtra("mobile",companyStaffListModel.getMobile());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return companyStaffListModelList.size();
    }

    public class StaffViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,email,mobile;
        ItemClickListner itemClickListner;
        public StaffViewholder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.txtStaffName);
            email=itemView.findViewById(R.id.txtStaffEmail);
            mobile=itemView.findViewById(R.id.txtStaffMobileNumber);

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
