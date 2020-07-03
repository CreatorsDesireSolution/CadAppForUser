package com.example.cadappforuser;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CompanyAddServicesActivity extends AppCompatActivity {

    Button btn_addservice;
    Spinner spinner;
    EditText et_setprice,et_servicedescription,et_servicename,et_duration;
    String setprice,setservicename,description,selectcategory,duration;
    BaseRequest baseRequest;
    Context context;
    Act_Session act_session;
    ImageView iv_camera,image_service;
    private Uri filepath1, filepath2, filepath3, filepath4;
    String encodeImage, encodeImage1;
    Bitmap bitmap, bitmap1;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_add_services);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Add Services");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        context = this;
        act_session = new Act_Session(context);
        btn_addservice = findViewById(R.id.btn_add_service);
        iv_camera= findViewById(R.id.iv_camera);
        image_service= findViewById(R.id.serviceImage);
        et_duration = findViewById(R.id.et_duration);
        et_servicedescription = findViewById(R.id.et_servicedescription);
        et_servicename = findViewById(R.id.etSName);
        et_setprice = findViewById(R.id.etSetPrice);

        Intent intent = getIntent();
        gender = intent.getStringExtra("gender");




        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(CompanyAddServicesActivity.this)
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
            public void onClick(View v) {
                setprice = et_setprice.getText().toString();
                description = et_servicedescription.getText().toString();
                setservicename = et_servicename.getText().toString();
                duration = et_duration.getText().toString();

                if (setprice.equals("")){

                    Toast.makeText(CompanyAddServicesActivity.this, "Please set the price", Toast.LENGTH_SHORT).show();
                }else  if (description.equals("")){
                    Toast.makeText(CompanyAddServicesActivity.this, "Please add description of service", Toast.LENGTH_SHORT).show();

                }else  if (setservicename.equals("")){
                    Toast.makeText(CompanyAddServicesActivity.this, "Please set service name", Toast.LENGTH_SHORT).show();

                }else if (duration.equals("")){
                    Toast.makeText(CompanyAddServicesActivity.this, "Please enter duration", Toast.LENGTH_SHORT).show();

                }else if (filepath1 == null){
                    Toast.makeText(CompanyAddServicesActivity.this, "Please select image ", Toast.LENGTH_SHORT).show();

                }else {
                    ApiAddService();

                }



            }
        });

    }


    private void ApiAddService() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                // act_session.loginSession(context);
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                   // JSONObject jsonObject1 = jsonObject.optJSONObject("data");

                    Toast.makeText(getApplicationContext(), "Add Successfully", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(RegisterAsFreelancerActivity.this, FreelancerMobileNumberRegisterActivity.class));
                    Intent intent = new Intent(getApplicationContext(),CompanyHomePageActivity.class);
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
        RequestBody gender_ = RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody image_ = RequestBody.create(MediaType.parse("text/plain"), encodeImage);



        baseRequest.callApiAddservicecompany(1,"https://aoneservice.net.in/" , userid_, service_name_,
                description_, set_price_, duration_,gender_,image_);

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


    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageBytes = stream.toByteArray();
        encodeImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
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
