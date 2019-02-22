package com.miguelcr.a01_hellomaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private LatLng lastPositionClicked = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Triana
        LatLng triana = new LatLng(37.380341,-6.007760);

        // Create a marker in "triana" location
        mMap.addMarker(new MarkerOptions()
                .position(triana)
                .title("Salesianos Triana")
                .snippet("Here we've the first foreign teacher that finished th course")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.house))
        );

        mMap.animateCamera(CameraUpdateFactory.newLatLng(triana));

        // Define the Map click event
        mMap.setOnMapClickListener(this);

        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(37.380341,-6.007760)) // Triana
                .add(new LatLng(46.038241,17.066153)); // Durdevac

        // Get back the mutable Polyline
        Polyline polyline = mMap.addPolyline(rectOptions);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.house))
        );

        if(lastPositionClicked == null) {
            // is not possible to create the polyline
            // because we have only one point
        } else {
            PolylineOptions rectOptions = new PolylineOptions()
                    .add(lastPositionClicked) // last position clicked
                    .add(latLng); // position clicked

            Polyline polyline = mMap.addPolyline(rectOptions);

        }

        lastPositionClicked = latLng;

    }
}
