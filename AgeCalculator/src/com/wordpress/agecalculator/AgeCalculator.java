package com.wordpress.agecalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AgeCalculator extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_age_calculator);

		final EditText editText1 = (EditText) findViewById(R.id.editText1);
		final EditText editText2 = (EditText) findViewById(R.id.editText2);
		final EditText editText3 = (EditText) findViewById(R.id.editText3);
		Button button1 = (Button) findViewById(R.id.button1);
		final TextView textView5 = (TextView) findViewById(R.id.textView5);
		final TextView textView4 = (TextView) findViewById(R.id.textView4);
		final TextView textView6 = (TextView) findViewById(R.id.textView6);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String yra = editText3.getText().toString();
				int yr = Integer.parseInt(yra);
				int year = 2016 - yr;

				String month = editText2.getText().toString();
				int monthe = Integer.parseInt(month);
				int mnt = 6 - monthe;

				String day = editText1.getText().toString();
				int days = Integer.parseInt(day);
				int dy = 23 - days;

				if (dy < 0) {
					mnt = mnt + 1;
					dy = dy - 23;

				}
				if (mnt < 0) {
					mnt = 12 - (-1 * mnt) - 1;
					year = year + 1;
				}

				textView5.setText("days=" + dy);
				textView4.setText("month=" + mnt);
				textView6.setText("year=" + year);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.age_calculator, menu);
		return true;
	}

}
