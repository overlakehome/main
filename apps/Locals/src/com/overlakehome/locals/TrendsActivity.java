package com.overlakehome.locals;

import java.util.Arrays;
import java.util.Comparator;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.overlakehome.locals.common.Place;

public class TrendsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places);

        Place[] places = NearBys.getInstance().getPlaces();
        Arrays.sort(places, new Comparator<Place>() {
            @Override
            public int compare(Place lhs, Place rhs) {
                return rhs.getCheckins() - lhs.getCheckins();
            }
        });

        String[] placeNames = new String[Math.max(20, places.length)];
        for (int i = 0; i < placeNames.length; i++) {
            placeNames[i] = String.format("%s (%d checkins)", places[i].getName(), places[i].getCheckins());
        }

        ListView lv = (ListView)findViewById(R.id.trendsListView);
//        lv.setAdapter(new ArrayAdapter<String>(this, R.id.trendsListView, placeNames));
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}