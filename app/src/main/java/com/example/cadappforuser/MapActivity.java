package com.example.cadappforuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    PlacesClient placesClient;
    LatLng latLng;
    private GoogleMap mMap;

    Button btnMapDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        btnMapDone=findViewById(R.id.btnMapDone);
        btnMapDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MapActivity.this,SearchByNameOrServicesOrNearby.class);
                startActivity(intent);
            }
        });


        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(MainActivity.this);

        String apikey="AIzaSyDJIq4-W6B09EtXLfOMJ28MAjTqRnvghiw";

        if (!Places.isInitialized()) {
            Places.initialize(MapActivity.this, apikey);
        }
        placesClient = Places.createClient(this);

        final AutocompleteSupportFragment autocompleteSupportFragment =
                (AutocompleteSupportFragment)
                        getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID,  Place.Field.NAME,Place.Field.LAT_LNG,Place.Field.ADDRESS));
        autocompleteSupportFragment.setOnPlaceSelectedListener(
                new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {
                        latLng = place.getLatLng();
                        mapFragment.getMapAsync(MapActivity.this);
                        Toast.makeText(MapActivity.this, ""+latLng.latitude+""+latLng.longitude, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Status status) {
                        Toast.makeText(MapActivity.this, ""+status.getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CameraUpdate cameraUpdateFactory= CameraUpdateFactory.newLatLngZoom(latLng,17);
        mMap.moveCamera(cameraUpdateFactory);
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(latLng.latitude+""+latLng.longitude)
                .snippet("location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMap != null) {
            mMap.clear();
        }
    }

}
