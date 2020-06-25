package com.example.cadappforuser;

import android.Manifest;
import android.app.Activity;
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

import androidx.annotation.Nullable;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.example.cadappforuser.retrofit.Constants.BASE_URL;

public class FreelancerCertificationActivity extends AppCompatActivity {

    Button btnCertificate;
    TextView txt_uploadcertification,txt_upload_picture;
    Bitmap bitmap,bitmap1;
    ImageView imageViewworkperform,imageViewcertificate;
    String encodeImage,encodeImage1;
    public static final int REQUEST_IMAGE = 100;
    BaseRequest baseRequest ;
    Act_Session act_session;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        act_session = new Act_Session(getApplicationContext());
        context = this;

        btnCertificate=findViewById(R.id.btnCertificate);
        txt_uploadcertification = findViewById(R.id.txt_uploadcertification);
        txt_upload_picture = findViewById(R.id.txt_upload_picture);

        imageViewcertificate = findViewById(R.id.imageViewcertificate);
        imageViewworkperform = findViewById(R.id.imageViewworkperform);



        txt_upload_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dexter.withActivity(FreelancerCertificationActivity.this)
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




        txt_uploadcertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(FreelancerCertificationActivity.this)
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

                ApiPostImage();

            }
        });
    }

    private void ApiPostImage() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {

                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                   // act_session = new Act_Session(context, jsonObject1);

                  //  Toast.makeText(context, "Upload image successfully", Toast.LENGTH_SHORT).show();






                } catch (JSONException e) {
                    e.printStackTrace();
                }






//                Utility.sucessDialog("User image uploaded successfully !","Konnectin","OK",context);


            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNetworkFailure(int requestCode, String message) {

            }
        });

        RequestBody encodeimage_ = RequestBody.create(MediaType.parse("text/plain"), encodeImage);
        RequestBody user_id_ = RequestBody.create(MediaType.parse("text/plain"), act_session.userId);
        baseRequest.callAPIUploadImage(1, "https://aoneservice.net.in/", encodeimage_, user_id_);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){

            Uri filepath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                imageViewcertificate.setImageBitmap(bitmap);
                imageViewworkperform.setImageBitmap(bitmap);

                imageStore(bitmap);



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] imageBytes=stream.toByteArray();
        encodeImage=android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//
//                Uri filepath = data.getData();
//                try {
//                    InputStream inputStream = getContentResolver().openInputStream(filepath);
//                    bitmap = BitmapFactory.decodeStream(inputStream);
//                    imageViewcertificate.setImageBitmap(bitmap);
//                      imageViewworkperform.setImageBitmap(bitmap1);
//
//                    imageStore(bitmap);
//
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//
//
//                }
//            }
//        }
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
//
//            Uri filepath = data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(filepath);
//                bitmap1 = BitmapFactory.decodeStream(inputStream);
//                // imageViewcertificate.setImageBitmap(bitmap);
//                imageViewworkperform.setImageBitmap(bitmap1);
//
//                imageStore1(bitmap1);
//
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//
//        private void imageStore1 (Bitmap bitmap1){
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte[] imageBytes = stream.toByteArray();
//            encodeImage1 = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        }


    }


