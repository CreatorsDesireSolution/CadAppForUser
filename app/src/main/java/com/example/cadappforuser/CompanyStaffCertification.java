package com.example.cadappforuser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import java.util.HashMap;
import java.util.Map;

public class CompanyStaffCertification extends AppCompatActivity {
    Act_Session act_session;
    Context context;
    ProgressDialog progressDialog;

    private Uri filepath1, filepath2, filepath3, filepath4;

    TextView txt_uploadcertification, txt_upload_picture;
    Bitmap bitmap, bitmap1;
    ImageView imageViewworkperform, imageViewcertificate;
    String encodeImage, encodeImage1;
    Button btnCertificateStaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_staff_certification);

        btnCertificateStaff=findViewById(R.id.btnCertificate);

        txt_uploadcertification = findViewById(R.id.txt_uploadcertification);
        txt_upload_picture = findViewById(R.id.txt_upload_picture);

        imageViewcertificate = findViewById(R.id.imageViewcertificate);
        imageViewworkperform = findViewById(R.id.imageViewworkperform);

        txt_uploadcertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dexter.withActivity(CompanyStaffCertification.this)
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


        txt_upload_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(CompanyStaffCertification.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), 2);
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


        btnCertificateStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog=new ProgressDialog(CompanyStaffCertification.this,R.style.MyAlertDialogStyle);
                progressDialog.setTitle("Upload");
                progressDialog.setMessage("Please Wait......");
                progressDialog.show();

                final StringRequest request=new StringRequest(Request.Method.POST, "http://aoneservice.net.in/salon/company_staffupload_api.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject= null;
                        try {
                            jsonObject = new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");

                            if(status.equals("true") || message.equals("Success")){
                                progressDialog.dismiss();
                                Toast.makeText(CompanyStaffCertification.this, ""+response, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(CompanyStaffCertification.this, ""+response, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d("res","res"+response);
                        Toast.makeText(CompanyStaffCertification.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("resorde","resse"+error.getMessage());
                        Toast.makeText(CompanyStaffCertification.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map=new HashMap<>();
                        map.put("certificate",encodeImage);
                        map.put("pic_work_performed",encodeImage1);
                        map.put("id",act_session.userId);
                        Log.d("id","id"+act_session.userId);

                        return  map;
                    }
                };

                RequestQueue requestQueue= Volley.newRequestQueue(CompanyStaffCertification.this);
                requestQueue.add(request);
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
                imageViewcertificate.setImageBitmap(bitmap);
                imageStore(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            filepath2 = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath2);
                bitmap1 = BitmapFactory.decodeStream(inputStream);
                imageViewworkperform.setImageBitmap(bitmap1);
                imageStoreWork(bitmap1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageBytes = stream.toByteArray();
        encodeImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void imageStoreWork(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageBytes = stream.toByteArray();
        encodeImage1 = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}
