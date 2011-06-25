package com.overlakehome.locals;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HotPlacesActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Hot places tab");
        setContentView(textview);

    }
}
