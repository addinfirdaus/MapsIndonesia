package com.sinau.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static final LatLng SIDOARJO = new LatLng(-7.3870732, 112.7269496);
    private static final LatLng MALANG = new LatLng(-7.9124853, 112.6545007);
    private static final LatLng BALI = new LatLng(-8.674295, 115.1836986);

    private Marker mSidoarjo;
    private Marker mMalang;
    private Marker mBali;

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

        //show marker
        LatLng sidoarjo = new LatLng(-7.3870732, 112.7269496);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(sidoarjo)
                .anchor(1,1)//marker miring 90 derajat haha
                .rotation(90)// marker miring 90 derajat haha
//                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launc_depo))//custom marker
//                .flat(true)// mempertahankan ukurannya bila peta diperbesar atau diperkecil.
                .title("Sidoarjo"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sidoarjo));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sidoarjo, 6));//zooming
        marker.showInfoWindow();//menampilkan info title secara terus tidak menunggu diklik

        // draw marker data array
        LatLng[] point_new = new LatLng[3];
        point_new[0] = new LatLng(-7.302664, 112.733089);
        point_new[1] = new LatLng(-7.257472,112.752088);
        point_new[2] = new LatLng(-7.401005, 112.590067);
        for (int i = 0; i < point_new.length; i++) {
            drawMarker(point_new[i]);
        }


   //     gMap.addMarker(new MarkerOptions().title(e.getString("name")).position(new LatLng(lat1, lng1)));//show marker from json maybe

        // Add some markers to the map, and add a data object to each marker.
        mSidoarjo = mMap.addMarker(new MarkerOptions()
                .position(SIDOARJO)
                .title("SIDOARJO")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mSidoarjo.setTag(0);

        mMalang = mMap.addMarker(new MarkerOptions()
                .position(MALANG)
                .title("MALANG")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMalang.setTag(0);

        mBali = mMap.addMarker(new MarkerOptions()
                .position(BALI)
                .title("BALI")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mBali.setTag(0);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    private void drawMarker(LatLng point) {
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(point);

        // Adding marker on the Google Map
        mMap.addMarker(markerOptions);
    }

}
