package com.example.memora;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LightsOnActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lights_toggle);
		new HTTPTask().execute("http://54.208.144.6/remote.php?v0=light&v1=power&v2=1");
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lights_toggle, menu);
		return true;
	}

}
