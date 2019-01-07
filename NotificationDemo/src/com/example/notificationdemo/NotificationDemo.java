package com.example.notificationdemo;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_demo);
		Button b= (Button) findViewById(R.id.btn1);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				
				NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				
				Notification notification = new Notification(R.drawable.ic_launcher,"notification rollingmsg",System.currentTimeMillis());
				CharSequence title ="Title";
				CharSequence message ="message";
				
				Intent intent = new Intent(NotificationDemo.this,NotificationDemo.class);
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				PendingIntent pendingIntent= PendingIntent.getActivity(NotificationDemo.this,0, intent, 0);
				
				notification.setLatestEventInfo(NotificationDemo.this, title, message,pendingIntent);
				notificationManager.notify(1,notification);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification_demo, menu);
		return true;
	}

}
