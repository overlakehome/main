package com.overlakehome.locals;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.overlakehome.locals.common.Special;

public class DealsDetailsActivity extends Activity {
    private Special[] specials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.deals_details);
        specials = NearBys.getInstance().getSpecials();

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        TextView tv = (TextView)findViewById(R.id.detailsName);
        tv.setText(specials[position].getName());
        TextView tv2 = (TextView)findViewById(R.id.detailsMessage);
        tv2.setText(specials[position].getMessage());
        TextView tv3 = (TextView)findViewById(R.id.detailsFinePrint);
        tv3.setText(specials[position].getFinePrint());
        TextView tv4 = (TextView)findViewById(R.id.detailsAddress);
        tv4.setText(specials[position].getAddress());
        TextView tv5 = (TextView)findViewById(R.id.detailsDistance);
        tv5.setText(String.format("Distance:  %.2f miles", NearBys.toDistance(specials[position])));
        TextView tv6 = (TextView)findViewById(R.id.detailsPhone);
        tv6.setText(specials[position].getPhone());
        TextView tv7 = (TextView)findViewById(R.id.detailsWebsite);
        tv7.setText(specials[position].getUrl());
    }
}
