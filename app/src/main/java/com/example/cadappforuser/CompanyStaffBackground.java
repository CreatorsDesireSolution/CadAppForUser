package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CompanyStaffBackground extends AppCompatActivity {

    Button btnCompanyStaff;
    Act_Session act_session;
    EditText et_aboutstaff,et_currentplacestaff,et_priviouswork,et_experience;
    String aboutyourself,experience,previousworkplace,currentworkplace;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_staff_background);

        act_session=new Act_Session(this);

        et_aboutstaff = findViewById(R.id.et_aboutfreelancer);
        et_currentplacestaff = findViewById(R.id.et_currentplacefreelancer);
        et_priviouswork = findViewById(R.id.et_priviouswork);
        et_experience = findViewById(R.id.et_experience);

        btnCompanyStaff=findViewById(R.id.btnBackgroundStaff);

        btnCompanyStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog=new ProgressDialog(CompanyStaffBackground.this,R.style.MyAlertDialogStyle);
                progressDialog.setTitle("Upload");
                progressDialog.setMessage("Please Wait......");
                progressDialog.show();
                String url="http://aoneservice.net.in/salon/company_staffbackground_api.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");

                            if(status.equals("true")&& message.equals("Success")){
                                Toast.makeText(CompanyStaffBackground.this, ""+response, Toast.LENGTH_SHORT).show();
                                Log.d("response","response"+response);
                                Intent intent=new Intent(CompanyStaffBackground.this,FreelancerCertificationActivity.class);
                                startActivity(intent);
                                et_aboutstaff.setText("");
                                et_currentplacestaff.setText("");
                                et_priviouswork.setText("");
                                et_experience.setText("");
                            }
                            else {
                                Toast.makeText(CompanyStaffBackground.this, "not insert", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CompanyStaffBackground.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String ,String > map=new HashMap<>();
                        map.put("about_yourself",et_aboutstaff.getText().toString());
                        map.put("curr_workplace",et_currentplacestaff.getText().toString());
                        map.put("prev_workplace",et_priviouswork.getText().toString());
                        map.put("experience",et_experience.getText().toString());
                        map.put("id",act_session.staffid);
                        Log.d("staffid","staff"+act_session.staffid);
                        return map;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(CompanyStaffBackground.this);
                requestQueue.add(stringRequest);

            }
        });

    }
}
