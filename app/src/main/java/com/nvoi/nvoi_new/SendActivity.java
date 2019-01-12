package com.nvoi.nvoi_new;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class SendActivity extends FragmentActivity implements OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener
        , com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;

    private Button mLogout,mRequest,mSettings;

    private LatLng pack_destination;
    private LatLng pack_pickup;

    private PlaceAutocompleteFragment placeAutocompleteFragment;
    Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLogout=(Button)findViewById(R.id.logout);
        mRequest=(Button)findViewById(R.id.request);
        mSettings=(Button)findViewById(R.id.settings);
        mRequest.setVisibility(View.GONE);

        placeAutocompleteFragment=(PlaceAutocompleteFragment)getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                final LatLng latLngDest=place.getLatLng();
                if(marker != null){
                    marker.remove();
                }
                marker=mMap.addMarker(new MarkerOptions().position(latLngDest).title("destination"));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(12),2000,null);
                pack_destination=marker.getPosition();

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.

            }
        });


        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passing the destination and pick up

                LatLng start=pack_pickup;
                LatLng end=pack_destination;

                Double start_lat=start.latitude;
                Double start_lng=start.longitude;

                Double end_lat=end.latitude;
                Double end_lng=end.latitude;


                Intent passData_intent=new Intent(SendActivity.this,AddJob.class);

                passData_intent.putExtra("start_lat",start_lat);
                passData_intent.putExtra("start_lng",start_lng);

                passData_intent.putExtra("end_lat",end_lat);
                passData_intent.putExtra("end_lng",end_lng);

                startActivity(passData_intent);


            }
        });


    }



//    private int radius=1; // this is in Km
//    private Boolean driverFound=false;
//    private String driverFoundID;
//    private void getClosestDriver() {
//        DatabaseReference driverLocation=FirebaseDatabase.getInstance().getReference().child("driversAvailable");
//
//        GeoFire geoFire=new GeoFire(driverLocation);
//        // geo queries
//        GeoQuery geoQuery=geoFire.queryAtLocation(new GeoLocation(pickupLocation.latitude,pickupLocation.longitude),radius);
//        geoQuery.removeAllListeners();
//
//        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
//            @Override
//            public void onKeyEntered(String key, GeoLocation location) {
//                // key is the id of the user
//                // location is the location of that driver
//                if(!driverFound){ // if statement is there cuz there can be more than 1 driver in the radius
//                    driverFound=true;
//                    driverFoundID=key;
//
//                    //this code part will add will add a customer id to the driverfoundId's driver
//                    DatabaseReference driverRef=FirebaseDatabase.getInstance().getReference().child("User").child("Drivers")
//                            .child(driverFoundID).child("Customer Request");
//                    String customerId=FirebaseAuth.getInstance().getCurrentUser().getUid();
//                    HashMap map=new HashMap();
//                    map.put("customerRideId",customerId);
//                    map.put("destination",destination);
//                    driverRef.updateChildren(map);
//
//                    getDriverLocation(); // to show driver location on the customer map
//                    mRequest.setText("Looking for Driver Location..");
//                }
//
//            }
//
//            @Override
//            public void onKeyExited(String key) {
//
//            }
//
//            @Override
//            public void onKeyMoved(String key, GeoLocation location) {
//
//            }
//
//            @Override
//            public void onGeoQueryReady() {
//                if(!driverFound){
//                    radius += 1;
//                    getClosestDriver();
//                }
//
//
//            }
//
//            @Override
//            public void onGeoQueryError(DatabaseError error) {
//
//            }
//        });
//    }

//    private Marker mDriverMarker; // making the location marker change when the driver travels
//    private void getDriverLocation() {
//        DatabaseReference driverLocationRef=FirebaseDatabase.getInstance().getReference().child("driversWorking").child(driverFoundID).child("l");
//        driverLocationRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    List<Object> map=(List<Object>)dataSnapshot.getValue();
//                    double locationLat=0;
//                    double locationLng=0;
//                    mRequest.setText("Driver Found");
//                    if(map.get(0) != null){//l's  0th value
//                        locationLat=Double.parseDouble(map.get(0).toString());
//                    }
//                    if(map.get(1) != null){//l's  1th value
//                        locationLng=Double.parseDouble(map.get(1).toString());
//                    }
//                    LatLng driverLatLng=new LatLng(locationLat,locationLng);
//                    if(mDriverMarker != null){
//                        mDriverMarker.remove();
//                    }
//
//                    //getting the distance between 2 locations
//                    Location loc1=new Location("");
//                    loc1.setLatitude(pickupLocation.latitude);
//                    loc1.setLongitude(pickupLocation.longitude);
//
//                    Location loc2=new Location("");
//                    loc2.setLatitude(pickupLocation.latitude);
//                    loc2.setLongitude(pickupLocation.longitude);
//                    float distance=loc1.distanceTo(loc2);
//                    if(distance<100){
//                        mRequest.setText("Your Courier has arrived");
//                    }else{
//                        mRequest.setText("Your Courier is on the way.."+String.valueOf(distance));
//                    }
//
//
//                    mDriverMarker=mMap.addMarker(new MarkerOptions().position(driverLatLng).title("your courier"));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient=new GoogleApiClient.Builder(this).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).
                addApi(LocationServices.API).
                build();
        mGoogleApiClient.connect();

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation=location;
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        pack_pickup=new LatLng(location.getLatitude(),location.getLongitude());



    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000); // 1000 mean 1s
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
