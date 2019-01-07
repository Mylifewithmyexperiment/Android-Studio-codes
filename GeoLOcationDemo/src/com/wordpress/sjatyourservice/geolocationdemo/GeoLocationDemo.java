package com.wordpress.sjatyourservice.geolocationdemo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class GeoLocationDemo extends Activity {

	TextView tv_lati;
	TextView tv_longi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geo_location_demo);
		tv_lati = (TextView) findViewById(R.id.tv_lati);
		tv_longi = (TextView) findViewById(R.id.tv_longi);

		// to obtain GPS location
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		LocationListener locationListener = new MyLocationListemer();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);

	}

	// now inner class for location :i.e my location listener

	public class MyLocationListemer implements LocationListener {

		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			tv_lati.setText("Latitude :" + arg0.getLatitude());
			tv_longi.setText("Longitude :" + arg0.getLongitude());

		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "GPS DISABLE",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "GPS ENABLE",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.geo_location_demo, menu);
		return true;
	}

}
