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
import com.example.cadappforuser.adapter.ServicesListAdapter;
import com.example.cadappforuser.adapter.ServicesListAdapterForShow;
import com.example.cadappforuser.model.ServicesListModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ServicesListModel> servicesListModelArrayList;
   // String url="https://aoneservice.net.in/salon/get-apis/company_freelancerservices_api.php";
    String id,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Services List");

        recyclerView=findViewById(R.id.recycle);
        servicesListModelArrayList=new ArrayList<>();

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        img=intent.getStringExtra("user");


        StringRequest request=new StringRequest(Request.Method.POST, "https://aoneservice.net.in/salon/get-apis/company_freelancerservices_api.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                   // Toast.makeText(ServicesListActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    String sucess = jsonObject.getString("status");
                   // Log.d("response","response"+response+" "+sucess);
                    //  String message=jsonObject.getString("message");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (sucess.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String service_gender=object.getString("service_gender");
                            //if(service_gender.equals("male")){
                            String id = object.getString("Service_id");
                            String freelancerId=object.getString("freelancer_id");
                            String service_name = object.getString("service_name");
                            String description = object.getString("description");
                            String set_price = object.getString("set_price");
                            String duration = object.getString("duration");

                            String item_image = object.getString("service_image");
                            String u = "http://aoneservice.net.in/salon/documents/" + item_image;
                            servicesListModelArrayList.add(new ServicesListModel(u,set_price,description,service_name,id,"0",freelancerId,"1",img));

                           // servicesListModelArrayList.add(new ServicesListModel(u,set_price,description,service_name,id,"0"));
                            ServicesListAdapterForShow servicesListAdapter=new ServicesListAdapterForShow(ServicesListActivity.this,servicesListModelArrayList);
                            LinearLayoutManager layoutManager=new LinearLayoutManager(ServicesListActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
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
                Toast.makeText(ServicesListActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params=new HashMap<>();
                params.put("id",id);
                Log.d("actid","actid"+id);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
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
