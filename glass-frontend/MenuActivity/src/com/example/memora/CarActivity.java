package com.example.memora;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		new HTTPTask().execute("http://54.208.144.6/remote.php?v0=car");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.car, menu);
		return true;
	}

}
