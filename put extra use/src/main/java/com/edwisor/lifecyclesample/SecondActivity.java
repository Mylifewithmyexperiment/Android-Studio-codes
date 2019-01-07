package com.edwisor.lifecyclesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "**SECOND_ACTIVITY**";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");
        setContentView(R.layout.activity_second);
        //Getting the parameters passed to this Activity via Intent extras.
        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", -1);
        Log.e(TAG, "Name is = " + name + "  and age is = " + age);

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }
}
