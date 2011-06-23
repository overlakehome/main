package com.overlakehome.locals;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView)findViewById(R.id.textView1);
        Place[] places;
        try {
            places = Places.findNearby(0, 0, "query", 0, 0);
            tv.setText(places[0].getName());
        } catch (Exception e) {
            tv.setText(e.toString());
        }
    }
}