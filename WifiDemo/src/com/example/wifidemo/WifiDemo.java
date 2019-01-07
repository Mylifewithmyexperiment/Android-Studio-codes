package com.example.wifidemo;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WifiDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi_demo);
		
		// to call wifi service
	final WifiManager	wifi=(WifiManager) getSystemService(Context.WIFI_SERVICE);
		
	Button btn1 = (Button) findViewById(R.id.button1);
	Button btn2= (Button) findViewById(R.id.button2);
	
	btn1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View V) {
			// TODO Auto-generated method stub
			wifi.setWifiEnabled(true);
		}
	});
	
    btn2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View V) {
			wifi.setWifiEnabled(false);
		}
	});
	

	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wifi_demo, menu);
		return true;
	}

}
