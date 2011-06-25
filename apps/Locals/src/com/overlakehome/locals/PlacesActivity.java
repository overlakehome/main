package com.overlakehome.locals;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.overlakehome.locals.common.Place;
import com.overlakehome.locals.common.Places;

public class PlacesActivity extends Activity implements LocationListener {
    
    private TextView locals01;
    private TextView locals02;
    private LocationManager locationManager = null;
    private double lat = 47.59755;
    private double lng = -122.32775;
    private Place[] places;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placesactivity);
        locals01 = (TextView)findViewById(R.id.locals01);
        
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (null != location) {
            onLocationChanged(location);
        }

        try {
            places = Places.Foursquare.findNearby(lat, lng, null, 50, 50);
            locals01.setText(places[0].getName());
        } catch (Exception e) {
            locals01.setText(e.toString());
        }
    }
    // https://sites.google.com/site/androidhowto/how-to-1/using-the-gps
    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        locals02 = (TextView)findViewById(R.id.locals02);
        try {
            places = Places.Foursquare.findNearby(lat, lng, null, 50, 50);
            locals02.setText(places[1].getName());
        } catch (Exception e) {
            locals02.setText(e.toString());
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}