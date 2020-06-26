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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.UtilsClasses.MarshMallowPermission;
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

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegisterAsCompanyActivity extends AppCompatActivity {

    Button btnRegister;
    TextView etAddress;
    CircleImageView iv_camera;
    ImageView imageUserLogo;
    Bitmap bitmap;
    String encodeImage;
    EditText etCompanyName,etRegistrationNumber,etMobileNumber,etEmail,etAboutCompany,etPassword;
    MarshMallowPermission marshMallowPermission;
    Activity activity;
    Context context;
    String deviceId;
    Act_Session act_session;
    BaseRequest baseRequest;
    EditText et_staff;
    String companyname,registrationnumber,address,mobilenumber,email,password,aboutcompany,staff;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_company_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register");

        context = this;
        activity = this;
        marshMallowPermission = new MarshMallowPermission(activity);
        act_session = new Act_Session(getApplicationContext());

        etAddress = findViewById(R.id.etAddress);
        et_staff = findViewById(R.id.et_staff);
        etPassword = findViewById(R.id.etPassword);
        imageUserLogo = findViewById(R.id.userImageIcon);
        iv_camera = findViewById(R.id.iv_camera);
        etCompanyName = findViewById(R.id.etCompanyName);
        etRegistrationNumber = findViewById(R.id.etRegistrationNumber);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etEmail = findViewById(R.id.etEmail);
        etAboutCompany = findViewById(R.id.etAboutCompany);
        btnRegister = findViewById(R.id.btnSignedIncomapny);


        etAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyname = etCompanyName.getText().toString();
                registrationnumber =etRegistrationNumber.getText().toString();

                mobilenumber = etMobileNumber.getText().toString();
                email = etEmail.getText().toString();
                Intent intent1 = new Intent(RegisterAsCompanyActivity.this, CompanyCurrentLocation.class);
                intent1.putExtra("name",companyname);
                intent1.putExtra("registernumber",registrationnumber);

                intent1.putExtra("mobileaddress",mobilenumber);
                intent1.putExtra("email",email);
                startActivity(intent1);
            }
        });

        Intent intent2 = getIntent();
        address = intent2.getStringExtra("address");
        companyname=intent2.getStringExtra("name");
        registrationnumber=intent2.getStringExtra("registernumber");
        mobilenumber=intent2.getStringExtra("mobileaddress");
        email=intent2.getStringExtra("email");

        etCompanyName.setText(companyname);
        etRegistrationNumber.setText(registrationnumber);
        etMobileNumber.setText(mobilenumber);
        etEmail.setText(email);

        etAddress.setText(address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyname = etCompanyName.getText().toString();
                registrationnumber =etRegistrationNumber.getText().toString();
                address = etAddress.getText().toString();
                mobilenumber = etMobileNumber.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                staff = et_staff.getText().toString();
                aboutcompany = etAboutCompany.getText().toString();

                if (companyname.equals("")){
                    Toast.makeText(activity, "Please enter company name", Toast.LENGTH_SHORT).show();
                }else  if(registrationnumber.equals("")){
                    Toast.makeText(activity, "Please enter registration number", Toast.LENGTH_SHORT).show();

                }else if (address.equals("")){
                    Toast.makeText(activity, "Please enter address", Toast.LENGTH_SHORT).show();


                }else  if(mobilenumber.equals("")){
                    Toast.makeText(activity, "Please enter mobilenumber", Toast.LENGTH_SHORT).show();


                }else  if (email.equals("")){
                    Toast.makeText(activity, "Please enter email", Toast.LENGTH_SHORT).show();


                }else if (password.equals("")){
                    Toast.makeText(activity, "Please enter password", Toast.LENGTH_SHORT).show();

                }else if(aboutcompany.equals("")) {
                    Toast.makeText(activity, "Please enter about company", Toast.LENGTH_SHORT).show();

                }else {

                    api_register();
                }


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

    }



    private void api_register() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
               // act_session.loginSession(context);
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                   // act_session = new Act_Session(context, jsonObject1);

                    Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),CompanyMobileNumberRegisterActivity.class);
                    intent.putExtra("mobilenumber",mobilenumber);
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
        RequestBody companyname_ = RequestBody.create(MediaType.parse("text/plain"),companyname );
        RequestBody aboutcompany_ = RequestBody.create(MediaType.parse("text/plain"),aboutcompany );
        RequestBody address_ = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobilenumber);
        RequestBody email_ = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody password_ = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody registarion_no_ = RequestBody.create(MediaType.parse("text/plain"), registrationnumber);
        RequestBody deviceId_ = RequestBody.create(MediaType.parse("text/plain"), deviceId);
        RequestBody staff_ = RequestBody.create(MediaType.parse("text/plain"), staff);


        baseRequest.callAPIRegisterascompany(1,"https://aoneservice.net.in/" , companyname_,
                aboutcompany_, address_, mobilenumber_,email_,password_,registarion_no_,deviceId_,staff_);

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
