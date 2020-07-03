package com.example.cadappforuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadappforuser.adapter.ServicesListAdapterForShow;
import com.example.cadappforuser.adapter.ServicesListAdapterForShow1;
import com.example.cadappforuser.model.CompanyService;
import com.example.cadappforuser.model.ServicesListModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompanyServiceVolley extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CompanyService> servicesListModelArrayList;
    String url="http://aoneservice.net.in/salon/get-apis/company_servicedata_api.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_service_volley);



        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services List");

        recyclerView=findViewById(R.id.recycle);
        servicesListModelArrayList=new ArrayList<>();

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final Act_Session act_session=new Act_Session(this);
        // Log.d("actid","actid"+act_session.userId);

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(CompanyServiceVolley.this, ""+response, Toast.LENGTH_SHORT).show();
                   // String sucess = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    String status=jsonObject.getString("status");
                    String message=jsonObject.getString("message");

                    if(status.equals("true") || message.equals("Success")){

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String service_gender=object.getString("service_gender");
                            Log.d("response","response"+response);
                            //if(service_gender.equals("male")){
                            String id = object.getString("id");
                            String service_name = object.getString("service_name");
                            String description = object.getString("description");
                            String set_price = object.getString("set_price");
                            String duration = object.getString("duration");

                            String item_image = object.getString("service_image");
                            String u = "http://aoneservice.net.in/salon/documents/" + item_image;

                            servicesListModelArrayList.add(new CompanyService(u,set_price,description,service_name,"1","0"));
                            ServicesListAdapterForShow1 servicesListAdapter=new ServicesListAdapterForShow1(CompanyServiceVolley.this,servicesListModelArrayList);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(servicesListAdapter);

                            //  }

                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CompanyServiceVolley.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params=new HashMap<>();
                params.put("id",act_session.userId);
                Log.d("actid","actid"+act_session.userId);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);



       /* servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"450","Lorem Ipsum","Facial","0","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.pro,"250","Lorem Ipsum","Hair Styling","1","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"550","Lorem Ipsum","Hair Wash","2","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.pro,"650","Lorem Ipsum","Hair Spa","3","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"150","Lorem Ipsum","Hair cut","4","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.pro,"850","Lorem Ipsum","Hair Styling","5","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"50","Lorem Ipsum","Hair Wash","6","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.pro,"150","Lorem Ipsum","Hair Cut","7","0"));
        servicesListModelArrayList.add(new ServicesListModel(R.drawable.hairwash,"450","Lorem Ipsum","Face Massage","8","0"));*/


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_cart,menu);
        MenuItem menuItem=menu.findItem(R.id.cart1);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}



