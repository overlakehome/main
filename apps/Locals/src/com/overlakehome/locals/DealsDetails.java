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
import com.overlakehome.locals.common.Places;
import com.overlakehome.locals.common.Special;

public class DealsDetails extends Activity {
    private Special[] specials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        

        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_details);
        specials = NearBys.getInstance().getSpecials();
        
        Bundle bundle = getIntent().getExtras();
        Integer position = bundle.getInt("param_position");
        //Integer.parseInt
        TextView tv = (TextView)findViewById(R.id.detailsName);
        tv.setText(specials[position].getName());
        
        //tv.setText(position);

/*        ListView lv = (ListView)findViewById(R.id.placesListView1);
        lv.setAdapter(new PlacesListAdapter(this, NearBys.getInstance().getPlaces()));
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(PlacesActivity.this, "Selected " + NearBys.getInstance().getPlaces()[position].getName(), Toast.LENGTH_LONG).show();
            }*/
    }
}
