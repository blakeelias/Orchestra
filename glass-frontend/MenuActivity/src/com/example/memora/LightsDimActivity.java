package com.example.memora;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LightsDimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lights_dim);
		int x = 128; // 0 <= x <= 255
		new HTTPTask().execute("http://54.208.144.6/remote.php?v0=light&v1=dim&v2=" + x);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lights_dim, menu);
		return true;
	}

}
