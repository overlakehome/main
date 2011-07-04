package com.overlakehome.locals;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PlacesActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places);
        TextView tv = (TextView)findViewById(R.id.locals01);

        try {
            tv.setText(NearBys.getInstance().getPlaces()[0].getName());
        } catch (Exception e) {
            tv.setText(e.toString());
        }
    }
}