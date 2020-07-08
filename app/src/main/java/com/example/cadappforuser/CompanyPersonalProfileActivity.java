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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadappforuser.model.CompanyProfileDataModel;
import com.example.cadappforuser.model.FreelancerProfileDetailsModel;
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

public class CompanyPersonalProfileActivity extends AppCompatActivity {

    EditText userMobile,userFirstName,userLastName,userGender,userLocation,userBackground,et_no_ofstaff,et_regnumber;
    ImageView userImage,userEditor,iv_profile_bck;
    TextView userEmail,userRating,tv_profile_heading;
    Button btnSubmit;
    BaseRequest baseRequest;
    Act_Session act_session;
    ArrayList<FreelancerProfileDetailsModel> profile_list1 = new ArrayList<>();
    Context context;
    String firstname,lastname,background,email,mobile,gender,address,register_number,no_of_staff;
    ImageView workperformed,btn_certificate;
    LinearLayout iv_camera;
    CircleImageView imageUserLogo;
    Bitmap bitmap;
    Uri file,fileget;
    String encodeImage,encodeimage1;
    ArrayList<CompanyProfileDataModel> companyProfileDataModels= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_personal_profile2);



        act_session = new Act_Session(getApplicationContext());
        userEmail=findViewById(R.id.userEmail);
        userMobile=findViewById(R.id.userMobileNo);
        userFirstName=findViewById(R.id.userFirstName);

        userRating=findViewById(R.id.userRating);
        userLocation=findViewById(R.id.userLocation);
        userBackground=findViewById(R.id.userBackground);
        userEditor=findViewById(R.id.editProfile);
        iv_profile_bck=findViewById(R.id.iv_profile_bck);
        tv_profile_heading=findViewById(R.id.tv_profile_heading);
        workperformed = findViewById(R.id.menCircleImageView);
        btn_certificate = findViewById(R.id.womenCircleImageView);

        imageUserLogo = findViewById(R.id.userImageIcon);
        iv_camera = findViewById(R.id.iv_camera);

        et_regnumber = findViewById(R.id.et_regnumber);
        et_no_ofstaff = findViewById(R.id.et_no_of_staff);

        context=this;
        btnSubmit=findViewById(R.id.btnSumbit);


        userEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFirstName.setEnabled(true);
                userBackground.setEnabled(true);
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
                Dexter.withActivity(CompanyPersonalProfileActivity.this)
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


        workperformed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CompanyGetWorkPerformed.class);
                startActivity(intent);
            }
        });

        btn_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CompanyGetAllCertificate.class);
                startActivity(intent);
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFirstName.setEnabled(false);
                userBackground.setEnabled(false);
                userMobile.setEnabled(false);
                et_no_ofstaff.setEnabled(false);
                et_regnumber.setEnabled(false);
                btnSubmit.setVisibility(View.GONE);
                userEditor.setVisibility(View.VISIBLE);
                tv_profile_heading.setText("Edit Profile");


                firstname = userFirstName.getText().toString();
                mobile = userMobile.getText().toString();
                email = userEmail.getText().toString();
                address = userLocation.getText().toString();
                background = userBackground.getText().toString();
                register_number = et_regnumber.getText().toString();
                no_of_staff=et_no_ofstaff.getText().toString();

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
                    companyProfileDataModels = baseRequest.getDataList(jsonArray, CompanyProfileDataModel.class);

                    if (companyProfileDataModels.size() != 0) {

                        Picasso.get().load("http://aoneservice.net.in/salon/documents/"+companyProfileDataModels.get(0).getProfile_pic())
                                .resize(400, 400).centerCrop().into(imageUserLogo);

                        userFirstName.setText(companyProfileDataModels.get(0).getCompanyname());
                        userMobile.setText(companyProfileDataModels.get(0).getMobilenumber());
                        userEmail.setText(companyProfileDataModels.get(0).getEmail());
                        userLocation.setText(companyProfileDataModels.get(0).getAddress());
                        userBackground.setText(companyProfileDataModels.get(0).getAboutcompany());
                        et_regnumber.setText(companyProfileDataModels.get(0).getRegnumber());
                        et_no_ofstaff.setText(companyProfileDataModels.get(0).getNoOfStaff());
                        // tv_age .setText(companyProfileDataModels.get(0).ge);


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
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/company_dataedit_api.php?" + "id=" + act_session.userId;
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
                    Intent intent = new Intent(getApplicationContext(),CompanyHomePageActivity.class);
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
        RequestBody companyname_ = RequestBody.create(MediaType.parse("text/plain"),firstname );
        RequestBody background_ = RequestBody.create(MediaType.parse("text/plain"),background );
        RequestBody staff = RequestBody.create(MediaType.parse("text/plain"), no_of_staff);
        RequestBody register_no = RequestBody.create(MediaType.parse("text/plain"), register_number);
        RequestBody mobilenumber_ = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody  address_= RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody  email_= RequestBody.create(MediaType.parse("text/plain"), email);





        baseRequest.CallUpdateprofileComapay(1,"https://aoneservice.net.in/" , companyname_, background_,
                staff,register_no,mobilenumber_,address_,email_);

    }


}




