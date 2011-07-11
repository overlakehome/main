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
        setContentView(R.layout.special_details);
        specials = NearBys.getInstance().getSpecials();

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        TextView tv = (TextView)findViewById(R.id.detailsName);
        tv.setText(specials[position].getName());
    }
}
