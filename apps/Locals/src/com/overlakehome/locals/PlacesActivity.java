package com.overlakehome.locals;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PlacesActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places);

        try {
            TextView tv = (TextView)findViewById(R.id.textView1);
            tv.setText(NearBys.getInstance().getPlaces()[0].getName());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}