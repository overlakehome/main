package com.overlakehome.locals;

import java.util.ArrayList;

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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dealsactivity);

//Create a custom multi line listview : dealsactivity.java, MyCustomBaseAdapter,SearchResult.java, deals_row_view, dealsactivity.xml
//http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx
//http://devblogs.net/2011/01/04/multicolumn-listview-with-image-icon/  
        ArrayList<SearchResults> searchResults = GetSearchResults();
       
        final ListView lv1 = (ListView) findViewById(R.id.ListView01);
        lv1.setAdapter(new MyCustomBaseAdapter(this, searchResults));
       
        lv1.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> a, View v, int position, long id) {
          Object o = lv1.getItemAtPosition(position);
          SearchResults fullObject = (SearchResults)o;
          Toast.makeText(DealsActivity.this, "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
         } 
        });
	}
	
    private ArrayList<SearchResults> GetSearchResults(){
        ArrayList<SearchResults> results = new ArrayList<SearchResults>();
        
        SearchResults sr1 = new SearchResults();
        sr1.setName("John Smith");
        sr1.setAddress("Dallas, TX");
        sr1.setDistance("214-555-1234");
        sr1.setCategoryIcon((R.drawable.ic_categ_food));
        results.add(sr1);
        
        sr1 = new SearchResults();
        sr1.setName("Jane Doe");
        sr1.setAddress("Atlanta, GA");
        sr1.setDistance("469-555-2587");
        sr1.setCategoryIcon((R.drawable.ic_categ_arts));
        results.add(sr1);
        
        sr1 = new SearchResults();
        sr1.setName("Steve Young");
        sr1.setAddress("Miami, FL");
        sr1.setCategoryIcon((R.drawable.ic_categ_food));
        sr1.setDistance("305-555-7895");
        results.add(sr1);
        
        sr1 = new SearchResults();
        sr1.setName("Fred Jones");
        sr1.setAddress("Las Vegas, NV");
        sr1.setDistance("612-555-8214");
        sr1.setCategoryIcon((R.drawable.ic_categ_food));
        results.add(sr1);
      
        sr1 = new SearchResults();
        sr1.setName("Fred Jones");
        sr1.setAddress("Las Vegas, NV");
        sr1.setDistance("612-555-8214");
        sr1.setCategoryIcon((R.drawable.ic_categ_food));
        results.add(sr1);
        
        sr1 = new SearchResults();
        sr1.setName("Fred Jones");
        sr1.setAddress("Las Vegas, NV");
        sr1.setDistance("612-555-8214");
        sr1.setCategoryIcon((R.drawable.ic_categ_food));
        results.add(sr1);
        
        sr1 = new SearchResults();
        sr1.setName("Fred Jones");
        sr1.setAddress("Las Vegas, NV");
        sr1.setDistance("612-555-8214");
        sr1.setCategoryIcon((R.drawable.ic_categ_food));
        results.add(sr1);
        
        return results;
       }
}
