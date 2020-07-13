package com.example.cadappforuser;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadappforuser.ServiceModel.NewModel;
import com.example.cadappforuser.adapter.NewAdapter;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FreelancerAddServicesActivity extends AppCompatActivity {
    Button btn_addservice;
    Spinner spinner;
    EditText et_setprice,et_servicedescription,et_servicename,et_duration;
    String setprice,setservicename,description,selectcategory,duration;
    BaseRequest baseRequest;
    Context context;
    Act_Session act_session;
    ProgressDialog progressDialog;
    private Uri filepath1, filepath2, filepath3, filepath4;
    String encodeImage, encodeImage1;
    Bitmap bitmap, bitmap1;
    // String url="http://aoneservice.net.in/salon/get-apis/freelancer_servicedata_api.php";
   ImageView image_service,iv_camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelancer_activity_add_services);


        image_service=findViewById(R.id.serviceImage);
        iv_camera=findViewById(R.id.iv_camera);

        context = this;
        act_session = new Act_Session(context);
        btn_addservice = findViewById(R.id.btn_add_service);
        et_duration = findViewById(R.id.et_duration);
        et_servicedescription = findViewById(R.id.et_servicedescription);
        et_servicename = findViewById(R.id.etSName);
        et_setprice = findViewById(R.id.etSetPrice);

        act_session=new Act_Session(this);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Add Services");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


     /*   List<String> list=new ArrayList<>();
         list.add("Select Category");
        list.add("RICA Waxing");
        list.add("Honey Waxing");
        list.add("Hair Color & Care");
        list.add("Threading");
        list.add("Waxing");
        list.add("Facial, Cleanup & Detan");
        list.add("Massage");
        list.add("Pedicure & Manicure");*/

     Intent intent=getIntent();
     final String gender=intent.getStringExtra("gender");

        final String userid=act_session.userId;
        Log.d("userid","userid"+userid);


        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(FreelancerAddServicesActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();

                            }

                        }).check();
            }

        });


        btn_addservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog=new ProgressDialog(FreelancerAddServicesActivity.this,R.style.MyAlertDialogStyle);
                progressDialog.setTitle("Upload");
                progressDialog.setMessage("Please Wait......");
                progressDialog.show();
                String url="https://aoneservice.net.in/salon/freelancer_addservice_api.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");

                            if(status.equals("true")&& message.equals("Success")){
                                progressDialog.dismiss();
                                Toast.makeText(FreelancerAddServicesActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                                Log.d("response","response"+response);

                                et_setprice.setText("");
                                et_servicedescription.setText("");
                                et_servicename.setText("");
                                et_duration.setText("");
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(FreelancerAddServicesActivity.this, "not insert", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FreelancerAddServicesActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String ,String > map=new HashMap<>();
                        map.put("service_name",et_servicename.getText().toString());
                        map.put("description",et_servicedescription.getText().toString());
                        map.put("set_price",et_setprice.getText().toString());
                        map.put("duration",et_duration.getText().toString());
                        map.put("id",act_session.userId);
                        map.put("service_gender",gender);
                        map.put("service_image",encodeImage);
                        //service_image
                        Log.d("staffid","staff"+act_session.userId);
                        return map;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(FreelancerAddServicesActivity.this);
                requestQueue.add(stringRequest);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            filepath1 = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath1);
                bitmap = BitmapFactory.decodeStream(inputStream);
                image_service.setImageBitmap(bitmap);
                bitmap=getResizedBitmap(bitmap,1024);
                imageStore(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageBytes = stream.toByteArray();
        encodeImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
        /* btn_addservice.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 setprice = et_setprice.getText().toString();
                 description = et_servicedescription.getText().toString();
                 setservicename = et_servicename.getText().toString();
                 duration = et_duration.getText().toString();

                 if (setprice.equals("")){

                     Toast.makeText(FreelancerAddServicesActivity.this, "Please set the price", Toast.LENGTH_SHORT).show();
                 }else  if (description.equals("")){
                     Toast.makeText(FreelancerAddServicesActivity.this, "Please add description of service", Toast.LENGTH_SHORT).show();

                 }else  if (setservicename.equals("")){
                     Toast.makeText(FreelancerAddServicesActivity.this, "Please set service name", Toast.LENGTH_SHORT).show();

                 }else if (duration.equals("")){
                     Toast.makeText(FreelancerAddServicesActivity.this, "Please enter duration", Toast.LENGTH_SHORT).show();
                 }else {
                // ApiAddService();
                 }
             }
         });*/
    /*private void ApiAddService() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                // act_session.loginSession(context);
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                    Toast.makeText(getApplicationContext(), "Add Successfully", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(RegisterAsFreelancerActivity.this, FreelancerMobileNumberRegisterActivity.class));
                    Intent intent = new Intent(getApplicationContext(),FreelancerHomePageActivity.class);
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int requestCode, String errorCode, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNetworkFailure(int requestCode, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
        RequestBody userid_ = RequestBody.create(MediaType.parse("text/plain"),act_session.userId );
        RequestBody service_name_ = RequestBody.create(MediaType.parse("text/plain"),setservicename );
        RequestBody description_ = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody set_price_ = RequestBody.create(MediaType.parse("text/plain"), setprice);
        RequestBody duration_ = RequestBody.create(MediaType.parse("text/plain"), duration);

        baseRequest.callApiAddservicefreelancer(1,"https://aoneservice.net.in/" , userid_, service_name_, description_, set_price_, duration_);

    }*/



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

