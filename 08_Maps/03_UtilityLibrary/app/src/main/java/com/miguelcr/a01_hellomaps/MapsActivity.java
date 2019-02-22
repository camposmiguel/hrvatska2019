package com.miguelcr.a01_hellomaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.clustering.ClusterManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private LatLng lastPositionClicked = null;
    private ClusterManager<MyItem> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Triana
        LatLng triana = new LatLng(37.380341,-6.007760);
        lastPositionClicked = new LatLng(37.380341,-6.007760);

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

        setUpClusterer();
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

            double distance = SphericalUtil.computeDistanceBetween(
                    lastPositionClicked,
                    latLng
            );

            Toast.makeText(this, "Distance: " + formatNumber(distance),
                    Toast.LENGTH_SHORT).show();

        }
        lastPositionClicked = latLng;
    } // end onMapClick

    private String formatNumber(double distance) {
        String unit = "m";

        if (distance > 1000) {
            distance = distance / 1000; // distance /= 1000;
            unit = "km";
        }

        return String.format("%4.3f%s", distance, unit);
    }


    private void setUpClusterer() {
        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyItem>(this, mMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        // Add cluster items (markers) to the cluster manager.
        addItems();
    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
        double lat = 51.5145160;
        double lng = -0.1270060;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            lat = lat + offset;
            lng = lng + offset;
            MyItem offsetItem = new MyItem(lat, lng);
            mClusterManager.addItem(offsetItem);
        }
    }

}
