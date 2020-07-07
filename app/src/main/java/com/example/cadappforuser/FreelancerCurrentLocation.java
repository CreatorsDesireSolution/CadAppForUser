package com.example.cadappforuser;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FreelancerCurrentLocation extends AppCompatActivity implements OnMapReadyCallback {


    EditText textView;
    Geocoder geocoder;
    List<Address> addresses;
    ImageView btnAddress;
    GoogleMap mMap;
    double lat,lng;
    private static  final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    Uri path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);
        btnAddress=findViewById(R.id.btn_address);

        textView=findViewById(R.id.textView);
        geocoder=new Geocoder(this, Locale.getDefault());

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocatin();
    }

    private void fetchLastLocatin() {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION_PERMISSION);
            return;
        }

        Task<Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    currentLocation=location;
                    Log.d("curent","current"+currentLocation);
                    Toast.makeText(FreelancerCurrentLocation.this, ""+currentLocation.getLatitude()+" "+currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(FreelancerCurrentLocation.this);


                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        LatLng latLng=new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());

        lat=currentLocation.getLatitude();
        lng=currentLocation.getLongitude();

        mMap.addMarker(new MarkerOptions().position(latLng));
        CameraUpdate cameraUpdateFactory= CameraUpdateFactory.newLatLngZoom(latLng,17);
        mMap.moveCamera(cameraUpdateFactory);


        try {
            addresses=geocoder.getFromLocation(currentLocation.getLatitude(),currentLocation.getLongitude(),1);

            String  addresse=addresses.get(0).getAddressLine(0);
            String area= addresses.get(0).getLocality();
            String city=addresses.get(0).getSubAdminArea();

            final String fullAddress=addresse+", "+area+", "+city;
            textView.setText(fullAddress);

            Intent intent=getIntent();
            final String gender=intent.getStringExtra("gender");
            final String firstname=intent.getStringExtra("firstname");
            final String lastname=intent.getStringExtra("lastname");
            final String email=intent.getStringExtra("email");
            final String mobilenumber=intent.getStringExtra("mobilenumber");
            path= getIntent().getParcelableExtra("image");



            btnAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(FreelancerCurrentLocation.this,RegisterAsFreelancerActivity.class);
                    intent.putExtra("address",fullAddress);
                    intent.putExtra("gender",gender);
                    intent.putExtra("firstname",firstname);
                    intent.putExtra("lastname",lastname);
                    intent.putExtra("email",email);
                    intent.putExtra("mobilenumber",mobilenumber);
                    intent.putExtra("image",path);

                    Bundle b = new Bundle();
                    b.putDouble("lat", lat);
                    b.putDouble("lng",lng);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_LOCATION_PERMISSION:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocatin();
                }
                break;
        }
    }

}
