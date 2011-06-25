package com.overlakehome.locals;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BookmarksActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		TextView textview = new TextView(this);
		textview.setText("This is the Bookmarks tab");
		setContentView(textview);
	}
}
