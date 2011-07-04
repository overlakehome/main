package com.overlakehome.locals;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.overlakehome.locals.common.Place;

public class PlacesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places);

        TextView tv = (TextView)findViewById(R.id.placesNearby);
        tv.setText("Nearby ");
        TextView tv2 = (TextView)findViewById(R.id.placesNearbyAddress);
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder.getFromLocation(47.6192649, -122.3404047, 1);

            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                tv2.setText(strReturnedAddress.toString());
            } else {
                tv2.setText("No Address returned!");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            tv2.setText("Canont get Address!");
        }

        ListView lv = (ListView)findViewById(R.id.placesListView1);
        lv.setAdapter(new DealsListAdapter(this, NearBys.getInstance().getPlaces()));
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(PlacesActivity.this, "Selected " + NearBys.getInstance().getPlaces()[position].getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Resource: http://devblogs.net/2011/01/04/multicolumn-listview-with-image-icon/
    private static class DealsListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private Place[] places;

        public DealsListAdapter(Context context, Place[] places) {
            this.inflater = LayoutInflater.from(context);
            this.places = places;
        }

        @Override
        public int getCount() {
            return places.length;
        }

        @Override
        public Object getItem(int position) {
            return places[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                view = inflater.inflate(R.layout.places_row, null);
                view.setTag(new Tag(view));
            }

            Tag tag = (Tag)view.getTag();
            tag.iv.setImageResource(NearBys.toDrawableId(places[position]));
            tag.tv1.setText(places[position].getName());
            tag.tv2.setText(places[position].getAddress());
            tag.tv3.setText(Double.toString(NearBys.toDistance(places[position]))); // String.format("42.22 miles", ...);
            return view;
        }

        private static class Tag {
            final ImageView iv;
            final TextView tv1;
            final TextView tv2;
            final TextView tv3;

            public Tag(View view) {
                iv = (ImageView)view.findViewById(R.id.image);
                tv1 = (TextView)view.findViewById(R.id.text1);
                tv2 = (TextView)view.findViewById(R.id.text2);
                tv3 = (TextView)view.findViewById(R.id.text3);
            }
        }
    }
}
