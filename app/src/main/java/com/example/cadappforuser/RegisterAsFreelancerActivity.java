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
import android.webkit.PermissionRequest;
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

public class RegisterAsFreelancerActivity extends AppCompatActivity {

    Button btnRegister;
    TextView txtGender;
    Bitmap bitmap;
    TextView etAddress;
    String encodeImage;
    EditText etFirstName, etLatName, etUserEmail, etUsePhoneNumber, etGender, etReferralCode, etpassword;

    ImageView imageUserLogo;
    CircleImageView iv_camera;
    MarshMallowPermission marshMallowPermission;
    Activity activity;
    Context context;
    String firstname, lastname, email, DOB, mobilenumber, gender1, address, referrelcode, password;
    String deviceId;
    BaseRequest baseRequest;
    Act_Session act_session;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_freelancer_register);

        marshMallowPermission = new MarshMallowPermission(activity);
        activity = this;
        context = this;
       act_session = new Act_Session(getApplicationContext());

        imageUserLogo = findViewById(R.id.userImageIcon);
        iv_camera = findViewById(R.id.iv_camera);
        txtGender = findViewById(R.id.etGender);
        etAddress = findViewById(R.id.etAddress);
        etFirstName = findViewById(R.id.etFirstName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etLatName = findViewById(R.id.etLatName);
        etUsePhoneNumber = findViewById(R.id.etUsePhoneNumber);
        etReferralCode = findViewById(R.id.etReferralCode);
        etpassword = findViewById(R.id.etpassword);




        etAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(RegisterAsFreelancerActivity.this, FreelancerCurrentLocation.class);
                intent1.putExtra("gender", gender1);
                startActivity(intent1);
            }
        });

        Intent intent2 = getIntent();
        etAddress.setText(intent2.getStringExtra("address"));
        address = intent2.getStringExtra(address);
        Intent intent = getIntent();
        gender1 = intent.getStringExtra(gender1);
        txtGender.setText(intent.getStringExtra("gender"));

        btnRegister = findViewById(R.id.btnSignedIn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password = etpassword.getText().toString();
                firstname = etFirstName.getText().toString();
                lastname = etLatName.getText().toString();
                email = etUserEmail.getText().toString();
                gender1 = txtGender.getText().toString();
                mobilenumber = etUsePhoneNumber.getText().toString();
                address = etAddress.getText().toString();
                if (firstname.equals("")) {
                    Toast.makeText(activity, "Please enter firstname", Toast.LENGTH_SHORT).show();
                } else if (lastname.equals("")) {
                    Toast.makeText(activity, "Please enter lastname", Toast.LENGTH_SHORT).show();

                } else if (email.equals("")) {
                    Toast.makeText(activity, "Please enter email", Toast.LENGTH_SHORT).show();


                } else if (mobilenumber.equals("")) {
                    Toast.makeText(activity, "Please enter mobilenumber", Toast.LENGTH_SHORT).show();


                } else if (address.equals("")) {
                    Toast.makeText(activity, "Please enter current location", Toast.LENGTH_SHORT).show();



                } else if (etpassword.equals("")) {
                    Toast.makeText(activity, "Please Enter password", Toast.LENGTH_SHORT).show();

                } else {

                    api_register();
                }

            }
        });



        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(RegisterAsFreelancerActivity.this)
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
                    startActivity(new Intent(RegisterAsFreelancerActivity.this, FreelancerMobileNumberRegisterActivity.class));

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
        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobilenumber);
        RequestBody gender_ = RequestBody.create(MediaType.parse("text/plain"), gender1);
        RequestBody address_ = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody deviceid_ = RequestBody.create(MediaType.parse("text/plain"), deviceId);
        RequestBody password_ = RequestBody.create(MediaType.parse("text/plain"), password);


        baseRequest.callApiRegisterfreelancer(1,"https://aoneservice.net.in/" , firstname_, lastname_, email_, mobilenumber_, gender_,address_,deviceid_,password_);

    }





}
