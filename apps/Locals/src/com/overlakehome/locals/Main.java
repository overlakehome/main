package com.overlakehome.locals;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Main extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, PlacesActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("places").setIndicator("Places",
	                      res.getDrawable(R.drawable.ic_tab_places))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, DealsActivity.class);
	    spec = tabHost.newTabSpec("deals").setIndicator("Deals",
	                      res.getDrawable(R.drawable.ic_tab_deals))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, HotPlacesActivity.class);
	    spec = tabHost.newTabSpec("hotplaces").setIndicator("HotPlaces",
	                      res.getDrawable(R.drawable.ic_tab_hotplaces))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	       intent = new Intent().setClass(this, BookmarksActivity.class);
	        spec = tabHost.newTabSpec("bookmarks").setIndicator("Bookmarks",
	                          res.getDrawable(R.drawable.ic_tab_bookmarks))
	                      .setContent(intent);
	        tabHost.addTab(spec);
	        
	    tabHost.setCurrentTab(0);
	}

}
