package com.example.cadappforuser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

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
    EditText etFirstName,etLatName,etUserEmail,etUsePhoneNumber,etReferralCode;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Register");

        txtGender=findViewById(R.id.etGender);
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
        final String gender=intent.getStringExtra("gender");
        txtGender.setText(gender);

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
        etAddress.setText(intent2.getStringExtra("address"));
        txtGender.setText(intent2.getStringExtra("gender"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        btnRegister=findViewById(R.id.btnSignedIn);
         btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(RegisterActivity.this,MobileNumberRegisterActivity.class));
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
