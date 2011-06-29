package com.overlakehome.locals;

import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TabHost;

import com.overlakehome.locals.common.Place;
import com.overlakehome.locals.common.Places;

public class MainActivity extends TabActivity implements LocationListener {
    private LocationManager locationManager = null;

    public void onCreate(Bundle savedInstanceState) {
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, this); // TODO: get right minDistance.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost(); // The activity TabHost
        TabHost.TabSpec spec; // Resusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, PlacesActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("places").setIndicator("Places", res.getDrawable(R.drawable.ic_tab_places)).setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, DealsActivity.class);
        spec = tabHost.newTabSpec("deals").setIndicator("Deals", res.getDrawable(R.drawable.ic_tab_deals)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, HotPlacesActivity.class);
        spec = tabHost.newTabSpec("hotplaces").setIndicator("HotPlaces", res.getDrawable(R.drawable.ic_tab_hotplaces)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, BookmarksActivity.class);
        spec = tabHost.newTabSpec("bookmarks").setIndicator("Bookmarks", res.getDrawable(R.drawable.ic_tab_bookmarks)).setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (null != location) {
            NearBys.getInstance().setLocation(location);
            NearBys.getInstance().findNearBys();
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

    public static class NearBys {
        private static NearBys nearBys = new NearBys();
        private List<Place> places = new ArrayList<Place>();
        private Location location;

        public static NearBys getInstance() {
            return nearBys;
        }

        public List<Place> getPlaces() {
            return places;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Location getLocation() {
            return location;
        }

        public void findNearBys() {
            try {
                Place[] places = Places.Foursquare.findNearby(location.getLatitude(), location.getLongitude(), null, 100, 50);
                if (null != places && places.length > 0) {
                    this.places.clear();
                    for (Place place : places) {
                        this.places.add(place);
                    }
                }
            } catch (Exception e) { // TODO: get right exception handling.
            }
        }

        public double distanceTo(Place place) {
            return distanceTo(place.getLatitude(), place.getLongitude());
        }

        private double distanceTo(double latitude, double longitude) {
            double pk = (180/3.14169);

            double a1 = location.getLatitude() / pk;
            double a2 = location.getLongitude() / pk;
            double b1 = latitude / pk;
            double b2 = longitude / pk;

            double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
            double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
            double t3 = Math.sin(a1)*Math.sin(b1);
            double tt = Math.acos(t1 + t2 + t3);
           
            return 3963167*tt;
        }

    }
}
