package com.overlakehome.locals;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity {
    private TextView locals01;
    private TextView locals02;
    private LocationManager locmgr = null;
    private LocationListener locationListener;
    double lat = 47.59755;
    double lng = -122.32775;
    //private double lat = 47.59755;
    //private double lng = -122.32775;
    private Place[] places;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        locals01 = (TextView)findViewById(R.id.locals01);
        
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        locmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener); 
        try {
            places = Places.Foursquare.findNearby(lat, lng, null, 50, 50);
            locals01.setText(places[0].getName());
        } catch (Exception e) {
            locals01.setText(e.toString());
        }
    }

    public class MyLocationListener implements LocationListener 
    {
       public void onLocationChanged(Location loc) {
           lat = loc.getLatitude();
           lng = loc.getLongitude();
           locals02 = (TextView)findViewById(R.id.locals01);
           try {
               places = Places.Foursquare.findNearby(lat, lng, null, 50, 50);
               locals02.setText(places[1].getName());
           } catch (Exception e) {
               locals02.setText(e.toString());
           }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider, int status, 
            Bundle extras) {
            // TODO Auto-generated method stub
        }
    }
}