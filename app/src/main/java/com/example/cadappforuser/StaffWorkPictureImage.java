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
import com.example.cadappforuser.retrofit.BaseRequest;
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

public class StaffWorkPictureImage extends AppCompatActivity {
    Button btnCertificate;
    TextView txt_uploadcertification, txt_upload_picture;
    Bitmap bitmap, bitmap1;
    ImageView imageViewworkperform, imageViewcertificate;
    String encodeImage, encodeImage1;
    public static final int REQUEST_IMAGE = 100;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    ProgressDialog progressDialog;

    private Uri filepath1, filepath2, filepath3, filepath4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_work_picture_image);

        act_session = new Act_Session(getApplicationContext());
        context = this;

        btnCertificate = findViewById(R.id.btnCertificate);
        txt_uploadcertification = findViewById(R.id.txt_uploadcertification);
        txt_upload_picture = findViewById(R.id.txt_upload_picture);

        imageViewcertificate = findViewById(R.id.imageViewcertificate);
        imageViewworkperform = findViewById(R.id.imageViewworkperform);

        txt_upload_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(StaffWorkPictureImage.this)
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

        btnCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog=new ProgressDialog(StaffWorkPictureImage.this,R.style.MyAlertDialogStyle);
                progressDialog.setTitle("Upload");
                progressDialog.setMessage("Please Wait......");
                progressDialog.show();

                final StringRequest request=new StringRequest(Request.Method.POST, "https://aoneservice.net.in/salon/company_staffworkperformed_api.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject= null;
                        try {
                            jsonObject = new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");

                            if(status.equals("true") || message.equals("Success")){
                                progressDialog.dismiss();
                                Toast.makeText(StaffWorkPictureImage.this, ""+response, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(StaffWorkPictureImage.this, ""+response, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d("res","res"+response);
                        Toast.makeText(StaffWorkPictureImage.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("resorde","resse"+error.getMessage());
                        Toast.makeText(StaffWorkPictureImage.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map=new HashMap<>();
                        //map.put("certificate",encodeImage);
                        map.put("pic_work_performed",encodeImage);
                        map.put("id","31");
                        Log.d("id","id"+act_session.userId);

                        return  map;
                    }
                };

                RequestQueue requestQueue= Volley.newRequestQueue(StaffWorkPictureImage.this);
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
                imageViewworkperform.setImageBitmap(bitmap);
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
}
