package com.example.alertdialogdemo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlertDialogDemo extends Activity {

	final Context context =this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert_dialog_demo);
		
		Button b1= (Button) findViewById(R.id.btn1);
		
		b1.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				//create objext of static inner 
				AlertDialog.Builder alBuilder = new AlertDialog.Builder(context);
				
				// to set title
				alBuilder.setTitle("Warning ");
				
				//to set dialog message
				alBuilder.setMessage("click yes to exit");
				
				//set canceleable false
				alBuilder.setCancelable(false);
				
				// set positive button
				
				alBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) 
					{
						// TODO Auto-generated method stub
						AlertDialogDemo.this.finish();
						
					}
				});
				
				// set negative button
				alBuilder.setNegativeButton("no ", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.cancel();
						
					}
				});
				
				// create alert dialog using all above elements
				AlertDialog alertDialog=alBuilder.create();
				
				// show
				alertDialog.show();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alert_dialog_demo, menu);
		return true;
	}

}
