package com.wordpress.sjatyourservice.sensoraccelerometerdemo;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class SensorAccelerometerDemo extends Activity {

	SensorManager sm= null;
	TextView tv_sensor =null;
	List list;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_accelerometer_demo);
	
		
		// to get sensor manager instance
		
		sm =(SensorManager)getSystemService(SENSOR_SERVICE);
		
		//refering to textview of xml
		 tv_sensor=(TextView) findViewById(R.id.tv_sensor);
		
		 //to get list view of sensor
		 list =sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
		 
		 
		 // if there is any sensor registered  alistener to the first else print a little error message 
		 if(list.size()>0)
		 {
			 sm.registerListener(sel, (Sensor)list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
			 
		 }
		 else
		 {
			 Toast.makeText(getBaseContext(), "Error:no  sensor", Toast.LENGTH_LONG).show();
		 }
		 
		 
	}
	
	//now this code is response to sensor event
	
	SensorEventListener sel= new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event  ) {
			// TODO Auto-generated method stub
			float[] values= event.values;
			tv_sensor.setText("X: "+values[0]+"\nY:"+values[1]+"\nZ:"+values[2]);
		}
		
		@Override
		public void onAccuracyChanged(  Sensor sensor,int accuracy ) {
			// TODO Auto-generated method stub
			
			
		}
	};
	protected void onstop() {

		if(list.size()>0){
			
			
			sm.unregisterListener(sel);
		}
		
		super.onStop();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor_accelerometer_demo, menu);
		return true;
	}

}
