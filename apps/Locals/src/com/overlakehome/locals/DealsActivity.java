package com.overlakehome.locals;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class DealsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deals);

// Create a custom multi line listview : 
//        dealsactivity.java, 
//        MyCustomBaseAdapter,
//        SearchResult.java, 
//        deals_row_view, dealsactivity.xml
//http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx
//http://devblogs.net/2011/01/04/multicolumn-listview-with-image-icon/  
       
        final ListView lv1 = (ListView) findViewById(R.id.ListView01);
        lv1.setAdapter(new MyCustomBaseAdapter(this));
       
        lv1.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
          Toast.makeText(DealsActivity.this, "You have chosen: " + " " + NearBys.getInstance().getPlaces()[position].getName(), Toast.LENGTH_LONG).show();
         } 
        });
	}
}
