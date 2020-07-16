package com.example.cadappforuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.R;
import com.example.cadappforuser.SqliteDatabase.FavDB;
import com.example.cadappforuser.model.CompanyAddServiceModel;
import com.example.cadappforuser.model.CompanyProfileDataModel;
import com.example.cadappforuser.model.ServicesListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CompanyAddServiceAdapater extends RecyclerView.Adapter<CompanyAddServiceAdapater.ViewHolder> {

    Context context;
    List<CompanyAddServiceModel> companyAddServiceModels;
    private boolean[] favorites;
    private FavDB favDB;
    Activity activity;
    int count;
    Act_Session act_session;

    public CompanyAddServiceAdapater(Context context, List<CompanyAddServiceModel> companyAddServiceModels,Act_Session act_session,Activity activity) {
        this.context = context;
        this.companyAddServiceModels = companyAddServiceModels;
        this.act_session = act_session;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CompanyAddServiceAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        favDB=new FavDB(context);
        SharedPreferences sharedPreferences=context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
        boolean firstStart=sharedPreferences.getBoolean("firstStart",true);
        if(firstStart){
            createTableOnFirstStart();
        }

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.companyaddservice,parent,false);
        CompanyAddServiceAdapater.ViewHolder viewHolder=new CompanyAddServiceAdapater.ViewHolder(view);
        return  viewHolder;
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();
        SharedPreferences preferences=context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAddServiceAdapater.ViewHolder holder, int position) {




        CompanyAddServiceModel companyAddServiceModel=companyAddServiceModels.get(position);
       // holder.sample.setText(companyAddServiceModel.getDescription());

        holder.sample.setText(companyAddServiceModels.get(position).getDescription());
        holder.name.setText(companyAddServiceModel.getServiceName());
        holder.price.setText("Rs."+companyAddServiceModel.getSetPrice());
        //holder.imageView.setImageResource(servicesListModel.getImage());
        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyAddServiceModel.getServiceImage()).resize(400, 400).centerCrop().into(holder.imageView);

    }




    @Override
    public int getItemCount() {

        return companyAddServiceModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,fevicon;
        TextView price,name,sample;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.serviceListServiceImage);
            price=itemView.findViewById(R.id.serviceListServicePrice);
            name=itemView.findViewById(R.id.serviceListServiceName);
            sample=itemView.findViewById(R.id.serviceListServiceSample);
           // fevicon = itemView.findViewById(R.id.fevicon);

        }
    }
}
