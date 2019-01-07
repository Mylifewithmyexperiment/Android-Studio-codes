package com.edwisor.scrollviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TimePicker;

public class UIExampleActivity extends AppCompatActivity {
  TimePicker mTimePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiexample);
    }
}
