package com.overlakehome.locals;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity implements LocationListener {
    private LocationManager locationManager = null;

    public void onCreate(Bundle savedInstanceState) {
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, this); // TODO: get right minDistance.

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (null != location) {
            onLocationChanged(location);
        } else {
            Location l = new Location(LocationManager.GPS_PROVIDER);
            l.setLatitude(47.59755);
            l.setLongitude(-122.32775);
            onLocationChanged(l);
        }

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

}
