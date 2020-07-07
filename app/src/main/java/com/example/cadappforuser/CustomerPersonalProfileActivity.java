package com.example.cadappforuser;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadappforuser.model.FreelancerProfileDetailsModel;
import com.example.cadappforuser.model.ProfilesDetailModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CustomerPersonalProfileActivity extends AppCompatActivity {

    EditText userMobile,userFirstName,userLastName,userGender,userLocation,userBackground,user_dob;
    ImageView userImage,userEditor,iv_profile_bck;
    TextView userEmail,userRating,tv_profile_heading;
    Button btnSubmit;
    BaseRequest baseRequest;
    Act_Session act_session;
    ArrayList<ProfilesDetailModel> profile_list1 = new ArrayList<>();
    Context context;
    String firstname,lastname,background,email,mobile,gender,address,DOB;
    ImageView workperformed,btn_certificate;
    CircleImageView iv_camera;
    ImageView imageUserLogo;
    Bitmap bitmap;
    Uri file,fileget;
    String encodeImage,encodeimage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_personal_profile);



        act_session = new Act_Session(getApplicationContext());
        userEmail=findViewById(R.id.userEmail);
        userMobile=findViewById(R.id.userMobileNo);
        userFirstName=findViewById(R.id.userFirstName);
        userGender=findViewById(R.id.userGender);
        userLastName=findViewById(R.id.userLastName);
        userRating=findViewById(R.id.userRating);
        userLocation=findViewById(R.id.userLocation);
        user_dob= findViewById(R.id.userDOB);
        userEditor=findViewById(R.id.editProfile);
        iv_profile_bck=findViewById(R.id.iv_profile_bck);
        tv_profile_heading=findViewById(R.id.tv_profile_heading);

        imageUserLogo = findViewById(R.id.userImageIcon);
        iv_camera = findViewById(R.id.iv_camera);

        context=this;
        btnSubmit=findViewById(R.id.btnSumbit);


        userEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFirstName.setEnabled(true);
//                userBackground.setEnabled(true);
                userLastName.setEnabled(true);
                userMobile.setEnabled(true);
                btnSubmit.setVisibility(View.VISIBLE);
                userEditor.setVisibility(View.GONE);
                tv_profile_heading.setText("Update Profile");
                iv_camera.setVisibility(View.VISIBLE);

            }
        });


        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(CustomerPersonalProfileActivity.this)
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


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFirstName.setEnabled(false);
//                userBackground.setEnabled(false);
                userLastName.setEnabled(false);
                userMobile.setEnabled(false);
                btnSubmit.setVisibility(View.GONE);
                userEditor.setVisibility(View.VISIBLE);
                tv_profile_heading.setText("Edit Profile");


                firstname = userFirstName.getText().toString();
                lastname = userLastName.getText().toString();
                mobile = userMobile.getText().toString();
                email = userEmail.getText().toString();
                address = userLocation.getText().toString();
                gender = userGender.getText().toString();
                DOB = user_dob.getText().toString();
                ApiPost();
            }
        });


        iv_profile_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Apigetprofile1();
    }

    private void Apigetprofile1() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");
                    profile_list1 = baseRequest.getDataList(jsonArray, ProfilesDetailModel.class);

                    if (profile_list1.size() != 0) {

                        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+profile_list1.get(0).getProfile_pic())
                                .resize(400, 400).centerCrop().into(imageUserLogo);

                        userFirstName.setText(profile_list1.get(0).getFirstname());
                        // tv.setText(profile_list1.get(0).getLastname());
                        userEmail.setText(profile_list1.get(0).getEmail());
                        userGender.setText(profile_list1.get(0).getGender());
                        userMobile.setText(profile_list1.get(0).getMobilenumber());
                        userLocation.setText(profile_list1.get(0).getAddress());
                        user_dob.setText(profile_list1.get(0).getDob());
                        userLastName.setText(profile_list1.get(0).getLastname());


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {

            }

            @Override
            public void onNetworkFailure(int requestCode, String message) {

            }
        });
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/customer_dataedit_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }


    private void ApiPost() {
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
                    Intent intent = new Intent(getApplicationContext(),CustomerPersonalProfileActivity.class);
                    // intent.putExtra("mobilenumber",mobilenumber);
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
        RequestBody firstname_ = RequestBody.create(MediaType.parse("text/plain"),firstname );
        RequestBody lastname_ = RequestBody.create(MediaType.parse("text/plain"),lastname );
        RequestBody email_ = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody gender_ = RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody address_ = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody dob = RequestBody.create(MediaType.parse("text/plain"), DOB);





        baseRequest.CallUpdateprofileCustomer(1,"https://aoneservice.net.in/" , firstname_, lastname_,
                email_,dob,
                mobilenumber_,gender_,address_);

    }

}
