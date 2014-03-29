package com.example.memora;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;

public class MenuActivity extends Activity {
	
	public static final String MILLIS_EXTRA_KEY = "millis";
	public static final String memoraDirectory = Environment.getExternalStorageDirectory()+File.separator+"memora";
	public static final String memoraDirectoryAudio = memoraDirectory+File.separator+"audio";
	public static final String memoraDirectoryImages = memoraDirectory+File.separator+"images";
	private static final String LOG_TAG = "Menu Activity";

	@Override
    public void onResume() {
        super.onResume();
        openOptionsMenu();
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memora_live_card);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		Log.d(LOG_TAG, "asdfg");
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.stop:
                stopService(new Intent(this, AudioRecorder.class));
                finish();
                closeOptionsMenu();
                //What is the result of calling finish before the return statement?
                //It does get past the finish statement.
                return true;
            case R.id.moments:
            	Intent myIntent = new Intent(this, MomentsImmersion.class);
            	startActivity(myIntent);
            	return true;
            case R.id.lights:
            	HttpURLConnectionExample http = new HttpURLConnectionExample();
            	try {
            		Log.d(LOG_TAG, "fetching google.com");
            		String response = http.sendGet("http://www.google.com/");
            		Log.d(LOG_TAG, "here it is!");
            		Log.d(LOG_TAG, response);
            	}
            	catch (Exception e) {
            		Log.d(LOG_TAG, "exception while fetching google.com");
            		Log.d(LOG_TAG, e.getMessage());
            	}
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	public void onOptionsMenuClosed(Menu menu) {
        finish();
    }
	
	private void captureAudioMesssage(long millis) {
		  Log.d("sender", "Broadcasting message");
		  Intent intent = new Intent("save_audio_intent");
		  intent.putExtra(MILLIS_EXTRA_KEY, millis);
		  LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}

	private void capturePhoto(long millis) {
		Intent intent = new Intent(this, PhotoActivity.class);
		intent.putExtra(MILLIS_EXTRA_KEY, millis);
		startActivity(intent);
	}
}



 
class HttpURLConnectionExample {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	// HTTP GET request
	public String sendGet(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		return response.toString();
 
	}

}
