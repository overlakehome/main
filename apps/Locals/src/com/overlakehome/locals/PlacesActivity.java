package com.overlakehome.locals;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.overlakehome.locals.common.Place;
import com.overlakehome.locals.common.Places;

public class PlacesActivity extends Activity {
    
    private TextView locals01;
    private double lat = 47.59755;
    private double lng = -122.32775;
    private Place[] places;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placesactivity);
        locals01 = (TextView)findViewById(R.id.locals01);

        try {
            places = Places.Foursquare.findNearby(lat, lng, null, 50, 50);
            locals01.setText(places[0].getName());
        } catch (Exception e) {
            locals01.setText(e.toString());
        }
    }
}