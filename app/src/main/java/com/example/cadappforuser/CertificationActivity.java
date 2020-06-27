package com.example.cadappforuser;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
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
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.example.cadappforuser.retrofit.Constants.BASE_URL;

public class CertificationActivity extends AppCompatActivity {

    TextView txt_uploadcertification,txt_uploadPicture;
    Button btn_nextcertificate;

    Bitmap bitmap, bitmap1;
    ImageView imageViewworkperform, imageViewcertificate;
    String encodeImage, encodeImage1;
    private Uri filepath1, filepath2, filepath3, filepath4;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_certification);

        context = this;

        act_session = new Act_Session(getApplicationContext());
        txt_uploadPicture =findViewById(R.id.txt_uploadPicture);
        txt_uploadcertification = findViewById(R.id.txt_uploadcertification);
        imageViewcertificate= findViewById(R.id.imageViewcertificate);
        imageViewworkperform = findViewById(R.id.imageViewworkperform);

        btn_nextcertificate = findViewById(R.id.btn_nextcertificate);



        txt_uploadcertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dexter.withActivity(CertificationActivity.this)
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





        txt_uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(CertificationActivity.this)
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

        btn_nextcertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (encodeImage.equals("")){
                    Toast.makeText(CertificationActivity.this, "Please choose certificate", Toast.LENGTH_SHORT).show();
                }else if (encodeImage1.equals("")){
                    Toast.makeText(CertificationActivity.this, "Please select picture", Toast.LENGTH_SHORT).show();
                }else {
                    APiPostCertificate();
                }
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
    public  void APiPostCertificate(){
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                //act_session.loginSession(context);

                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");



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
        RequestBody userid_ = RequestBody.create(MediaType.parse("text/plain"), act_session.userId);
        RequestBody certificate = RequestBody.create(MediaType.parse("text/plain"), encodeImage);
        RequestBody picture_of_workperform = RequestBody.create(MediaType.parse("text/plain"), encodeImage1);

        baseRequest.callApipostCertificatecompany(1, BASE_URL, userid_, certificate,picture_of_workperform);

    }
}


