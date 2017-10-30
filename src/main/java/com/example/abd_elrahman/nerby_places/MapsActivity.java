package com.example.abd_elrahman.nerby_places;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.abd_elrahman.nerby_places.PlaceModel.PlaceModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    PlaceModel placeModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent adapter = getIntent();
        placeModel = (PlaceModel) getIntent().getSerializableExtra("placeModel");

        Intent nav_deatils = getIntent();
        placeModel = (PlaceModel) getIntent().getSerializableExtra("placeModel");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //LatLng egypt = new LatLng(26.6194394,39.8970157);

        LatLng place = new LatLng(placeModel.getGeometry().getLocation().getLat(),placeModel.getGeometry().getLocation().getLng());
        // mMap.addMarker(new MarkerOptions().position(place).title("Marker in Egypt"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
    }
}
