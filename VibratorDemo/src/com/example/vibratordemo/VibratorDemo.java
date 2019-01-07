package com.example.vibratordemo;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class VibratorDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vibrator_demo);
		Button b1= (Button) findViewById(R.id.btn1);

		Button b2= (Button) findViewById(R.id.btn2);
		final Vibrator vibrator =(Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				vibrator.vibrate(new long[]{0,5000,1000},0);
				Toast.makeText(VibratorDemo.this, "vibrate shuru ho gya h", Toast.LENGTH_LONG).show();
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				vibrator.cancel();
				Toast.makeText(VibratorDemo.this, "vibrate band", Toast.LENGTH_LONG).show();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vibrator_demo, menu);
		return true;
	}

}
