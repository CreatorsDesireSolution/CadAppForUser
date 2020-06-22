package com.example.cadappforuser;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.UtilsClasses.MarshMallowPermission;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterAsCompanyActivity extends AppCompatActivity {

    Button btnRegister;
    TextView etAddress;
    CircleImageView iv_camera;
    ImageView imageUserLogo;
    Bitmap bitmap;
    String encodeImage;
    EditText etCompanyName,etRegistrationNumber,etMobileNumber,etEmail,etAboutCompany;
    MarshMallowPermission marshMallowPermission;
    Activity activity;
    Context context;
    String deviceId;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_company_register);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Register");

        context = this;
        activity = this;
        marshMallowPermission = new MarshMallowPermission(activity);

        etAddress=findViewById(R.id.etAddress);
        imageUserLogo=findViewById(R.id.userImageIcon);
        iv_camera = findViewById(R.id.iv_camera);
        etCompanyName=findViewById(R.id.etCompanyName);
        etRegistrationNumber=findViewById(R.id.etRegistrationNumber);
        etMobileNumber=findViewById(R.id.etMobileNumber);
        etEmail=findViewById(R.id.etEmail);
        etAboutCompany=findViewById(R.id.etAboutCompany);
        btnRegister=findViewById(R.id.btnSignedIncomapny);


        etAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(RegisterAsCompanyActivity.this,CompanyCurrentLocation.class);
                startActivity(intent1);
            }
        });

        Intent intent2=getIntent();
        etAddress.setText(intent2.getStringExtra("address"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterAsCompanyActivity.this,CompanyMobileNumberRegisterActivity.class));
            }
        });

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(RegisterAsCompanyActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
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

        final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        if (mTelephony.getDeviceId() != null) {
            deviceId = mTelephony.getDeviceId();
        } else {
            deviceId = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){

            Uri filepath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                imageUserLogo.setImageBitmap(bitmap);

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
