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
import android.widget.TextView;
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

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.example.cadappforuser.retrofit.Constants.BASE_URL;

public class CompanyRegisterAsCompanyStaffActivity extends AppCompatActivity {

    Button btnRegister;
    TextView txtGender,etAddress;
    String firstname,lastname,email,mobilenumber,gender,address;

    EditText etFirstName,etLastName,etUserEmail,etUsePhoneNumber,etGender,etReferralCode;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    CircleImageView iv_camera;
    ImageView imageUserLogo;
    Bitmap bitmap;
    String encodeImage;
    Uri file,file1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity_as_staff_register);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Register");

        context = this;

        act_session = new Act_Session(context);
        txtGender=findViewById(R.id.etGender);
        etFirstName = findViewById(R.id.etFirstName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etLastName= findViewById(R.id.etLastName);
        etUsePhoneNumber = findViewById(R.id.etUsePhoneNumber);
        etAddress = findViewById(R.id.etAddress);
        etReferralCode = findViewById(R.id.etReferralCode);
        imageUserLogo = findViewById(R.id.userImageIcon);
        iv_camera = findViewById(R.id.iv_camera);

        final Intent intent=getIntent();
         gender=intent.getStringExtra("gender");
        txtGender.setText(gender);



        etAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = etFirstName.getText().toString();
                lastname =etLastName.getText().toString();
                mobilenumber = etUsePhoneNumber.getText().toString();
                email = etUserEmail.getText().toString();

                Intent intent1 = new Intent(getApplicationContext(),CurrentLocationofcompanyStaff.class);
                intent1.putExtra("firstname",firstname);
                intent1.putExtra("lastname",lastname);
                intent1.putExtra("mobilenumber",mobilenumber);
                intent1.putExtra("email",email);
                intent1.putExtra("gender",gender);
                startActivity(intent1);
            }
        });


        Intent intent2 = getIntent();
        address = intent2.getStringExtra("address");
        firstname=intent2.getStringExtra("firstname");
        lastname=intent2.getStringExtra("lastname");
        mobilenumber=intent2.getStringExtra("mobilenumber");
        email=intent2.getStringExtra("email");
        gender= intent2.getStringExtra("gender");


        etFirstName.setText(firstname);
        etLastName.setText(lastname);
        etUsePhoneNumber.setText(mobilenumber);
        etUserEmail.setText(email);
        etAddress.setText(address);
      //  etGender.setText(gender);


        btnRegister=findViewById(R.id.btnSignedIn);
         btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 firstname = etFirstName.getText().toString();
                 lastname= etLastName.getText().toString();
                 mobilenumber =etUsePhoneNumber.getText().toString();
                 email = etUserEmail.getText().toString();
                 gender = txtGender.getText().toString();
                 address = etAddress.getText().toString();

                 if (firstname.equals("")){
                     Toast.makeText(context, "please enter firstname", Toast.LENGTH_SHORT).show();
                 }else  if (lastname.equals("")){
                     Toast.makeText(context, "please enter lastname", Toast.LENGTH_SHORT).show();

                 }else if (mobilenumber.equals("")){
                     Toast.makeText(context, "please enter mobilenumber", Toast.LENGTH_SHORT).show();

                 }else if (address.equals("")){
                     Toast.makeText(context, "please enter address", Toast.LENGTH_SHORT).show();

                 }else  if (email.equals("")){
                     Toast.makeText(context, "please enter email", Toast.LENGTH_SHORT).show();

                 }else {
                      ApiPostStaff();
                 }
             }
         });


        iv_camera.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Dexter.withActivity(CompanyRegisterAsCompanyStaffActivity.this)
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


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void ApiPostStaff() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {

                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");

                    String staff_id=jsonObject1.getString("staffid");
                    act_session.userstaffid(getApplicationContext(),staff_id);
                   // act_session = new Act_Session(context, jsonObject1);

                    Toast.makeText(getApplicationContext(), "Add Successfully"+act_session.staffid, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CompanyRegisterAsCompanyStaffActivity.this,CompanySetAvalibiltyCustomActivityStaff.class));



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
        RequestBody userid = RequestBody.create(MediaType.parse("text/plain"),act_session.userId );
        RequestBody firstname_ = RequestBody.create(MediaType.parse("text/plain"),firstname );
        RequestBody lastname_ = RequestBody.create(MediaType.parse("text/plain"), lastname);
        RequestBody email_ = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobilenumber);
        RequestBody gender_ = RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody address_ = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody profile_pic = RequestBody.create(MediaType.parse("text/plain"), encodeImage);



        baseRequest.CallApiPOstStaff(1,BASE_URL , userid,
                firstname_, lastname_, email_,mobilenumber_,gender_,address_,profile_pic);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){

            file=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(file);
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
