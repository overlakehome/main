package com.overlakehome.locals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

public class TrendsActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        List<Place> places = new ArrayList<Place>();
//        Collections.addAll(places, NearBys.getInstance().getPlaces());
//        Collections.sort(places, new Comparator<Place>() {
//            @Override
//            public int compare(Place lhs, Place rhs) {
//                return rhs.getCheckins() - lhs.getCheckins();
//            }
//        });

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

        setListAdapter(new ArrayAdapter<String>(this, R.layout.trends, placeNames));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}