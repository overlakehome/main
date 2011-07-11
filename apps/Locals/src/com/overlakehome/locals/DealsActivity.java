package com.overlakehome.locals;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.overlakehome.locals.common.Places;
import com.overlakehome.locals.common.Special;

public class DealsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deals);

        TextView tv = (TextView)findViewById(R.id.dealsTextView1);
        tv.setText("Nearby " + Places.toString(getBaseContext(), NearBys.getInstance().getLocation()));

        ListView lv = (ListView)findViewById(R.id.dealsListView1);
        lv.setAdapter(new DealsListAdapter(this, NearBys.getInstance().getSpecials()));
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                // TODO Auto-generated method stub
                Intent detailsIntent = new Intent(DealsActivity.this, DealsDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("param_position", position);
                detailsIntent.putExtras(bundle);
                DealsActivity.this.startActivity(detailsIntent);
                
            }
           
/*            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(DealsActivity.this, "Selected " + NearBys.getInstance().getPlaces()[position].getName(), Toast.LENGTH_LONG).show();
            }*/
    } );
       
    }
    // Resource: http://devblogs.net/2011/01/04/multicolumn-listview-with-image-icon/
    private static class DealsListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private Special[] specials;

        public DealsListAdapter(Context context, Special[] specials) {
            this.inflater = LayoutInflater.from(context);
            this.specials = specials;
        }

        @Override
        public int getCount() {
            return specials.length;
        }

        @Override
        public Object getItem(int position) {
            return specials[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                view = inflater.inflate(R.layout.deals_row, null);
                view.setTag(new Tag(view));
            }

            Tag tag = (Tag)view.getTag();
            tag.iv.setImageResource(NearBys.toDrawableId(specials[position]));
            tag.tv1.setText(specials[position].getName());
            tag.tv2.setText(specials[position].getAddress());
            tag.tv3.setText(specials[position].getMessage());
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
