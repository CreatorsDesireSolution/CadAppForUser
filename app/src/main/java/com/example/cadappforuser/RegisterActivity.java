package com.example.cadappforuser;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    TextView txtGender;
    TextView etAddress;
    TextView text_DOB;
    Calendar calendarView;
    int day,months,year;
    CircleImageView iv_camera;
    ImageView imageUserLogo;
    Bitmap bitmap;
    String encodeImage;
    EditText etFirstName,etLatName,etUserEmail,etUsePhoneNumber,etReferralCode,etpassword;
    MarshMallowPermission marshMallowPermission;
    Activity activity;
    Context context;
    String deviceId;
    BaseRequest baseRequest;
    String firstname,lastname,email,DOB,mobilenumber,gender,address,referrelcode,password;
    Act_Session act_session;
    Uri selectedImage;
    File file;
    public static final int REQUEST_IMAGE = 100;




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;
        activity = this;
        marshMallowPermission = new MarshMallowPermission(activity);
        act_session = new Act_Session(getApplicationContext());


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Register");

        txtGender=findViewById(R.id.etGender);
        etpassword= findViewById(R.id.etpassword);
        imageUserLogo=findViewById(R.id.userImageIcon);
        iv_camera = findViewById(R.id.iv_camera);
        etAddress=findViewById(R.id.etAddress);
        text_DOB = findViewById(R.id.txt_DOB);
        etFirstName = findViewById(R.id.etFirstName);
        etLatName = findViewById(R.id.etLatName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etUsePhoneNumber = findViewById(R.id.etUsePhoneNumber);
        etReferralCode = findViewById(R.id.etReferralCode);




        calendarView=Calendar.getInstance();
        day=calendarView.get(Calendar.DAY_OF_MONTH);
        months=calendarView.get(Calendar.MONTH);
        year=calendarView.get(Calendar.YEAR);
        months=months+1;

        final Intent intent=getIntent();
        gender = intent.getStringExtra("gender");
        txtGender.setText(gender);


       // final String gender=intent.getStringExtra("gender");


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

        text_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonhts) {
                        month=month+1;
                        text_DOB.setText(dayOfMonhts+"/"+month+"/"+year);
                    }
                },year,months,day);
                datePickerDialog.show();
            }
        });



        etAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(RegisterActivity.this,CurrentLocation.class);
                intent1.putExtra("gender",gender);
                startActivity(intent1);
            }
        });

        Intent intent2=getIntent();
        address = intent2.getStringExtra("address");
        etAddress.setText(address);
       // etAddress.setText(intent2.getStringExtra("address"));
        txtGender.setText(intent2.getStringExtra("gender"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);







        btnRegister=findViewById(R.id.btnSignedIn);
         btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                // startActivity(new Intent(RegisterActivity.this,MobileNumberRegisterActivity.class));


                   password = etpassword.getText().toString();
                 firstname = etFirstName.getText().toString();
                 lastname = etLatName.getText().toString();
                 email = etUserEmail.getText().toString();
                 mobilenumber = etUsePhoneNumber.getText().toString();
                 address = etAddress.getText().toString();
                 DOB = text_DOB.getText().toString();
                 if (firstname.equals("")){
                     Toast.makeText(activity, "Please enter firstname", Toast.LENGTH_SHORT).show();
                 }else  if(lastname.equals("")){
                     Toast.makeText(activity, "Please enter lastname", Toast.LENGTH_SHORT).show();

                 }else if (email.equals("")){
                     Toast.makeText(activity, "Please enter email", Toast.LENGTH_SHORT).show();


                 }else  if(mobilenumber.equals("")){
                     Toast.makeText(activity, "Please enter mobilenumber", Toast.LENGTH_SHORT).show();


                 }else  if (address.equals("")){
                     Toast.makeText(activity, "Please enter current location", Toast.LENGTH_SHORT).show();


                 }else if (DOB.equals("")){
                     Toast.makeText(activity, "Please select dob", Toast.LENGTH_SHORT).show();

                 }else if(etpassword.equals("")) {
                     Toast.makeText(activity, "Please Enter password", Toast.LENGTH_SHORT).show();

                 }else {

                     api_register();
                 }

             }
         });

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(RegisterActivity.this)
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_IMAGE) {
                if (resultCode == Activity.RESULT_OK) {

                    selectedImage = data.getParcelableExtra("path");
                    imageUserLogo.setImageURI(selectedImage);

                    File actualImageFile = new File(selectedImage.getPath());
                    file = saveBitmapToFile(actualImageFile);

                    long fileSizeInBytes = file.length();
                    long fileSizeInKB = fileSizeInBytes / 1024;
                    long fileSizeInMB = fileSizeInKB / 1024;

                    Log.e("SIZE>>>", String.valueOf(fileSizeInKB));

                }
            }
        }
    }


    public File saveBitmapToFile(File file){
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE=75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }

    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] imageBytes=stream.toByteArray();
        encodeImage=android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }


    private void api_register() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                act_session.loginSession(context);
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                    act_session = new Act_Session(context, jsonObject1);

                    Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,MobileNumberRegisterActivity.class));

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
        RequestBody firstname_ = RequestBody.create(MediaType.parse("text/plain"),firstname );
        RequestBody lastname_ = RequestBody.create(MediaType.parse("text/plain"),lastname );
        RequestBody email_ = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody dob_ = RequestBody.create(MediaType.parse("text/plain"), DOB);
        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobilenumber);
        RequestBody gender_ = RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody address_ = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody deviceid_ = RequestBody.create(MediaType.parse("text/plain"), deviceId);
        RequestBody password_ = RequestBody.create(MediaType.parse("text/plain"), password);


        baseRequest.callAPIRegister(1,"https://aoneservice.net.in/" , firstname_, lastname_, email_, dob_, mobilenumber_, gender_,address_,deviceid_,password_);

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
