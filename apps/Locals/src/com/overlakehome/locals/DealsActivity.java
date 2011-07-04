package com.overlakehome.locals;

import android.app.Activity;
import android.content.Context;
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

public class DealsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deals);

        ListView lv = (ListView) findViewById(R.id.dealsListView1);
        lv.setAdapter(new DealsListAdapter(this, NearBys.getInstance().getPlaces()));
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(DealsActivity.this, "You have chosen: " + " " + NearBys.getInstance().getPlaces()[position].getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Resource: http://devblogs.net/2011/01/04/multicolumn-listview-with-image-icon/
    private static class DealsListAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private Place[] places;

        public DealsListAdapter(Context context, Place[] places){
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
        public View getView(int position, View convertView, ViewGroup parent) {
            Tag tag;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.deals_row, null);
                convertView.setTag(new Tag(convertView));
            }

            tag = (Tag)convertView.getTag();
            tag.iv.setImageResource(NearBys.toClazz(places[position]).getDrawableId());
            tag.tv1.setText(places[position].getName());
            tag.tv2.setText(places[position].getAddress());
            tag.tv3.setText(Double.toString(NearBys.distanceTo(places[position])));
            return convertView;
        }

        private static class Tag {
            ImageView iv;
            TextView tv1;
            TextView tv2;
            TextView tv3;

            public Tag(View convertView) {
                iv = (ImageView) convertView.findViewById(R.id.image);
                tv1 = (TextView) convertView.findViewById(R.id.text1);
                tv2 = (TextView) convertView.findViewById(R.id.text2);
                tv3 = (TextView) convertView.findViewById(R.id.text3);
            }
        }
    }
}
