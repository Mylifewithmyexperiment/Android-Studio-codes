package com.edwisor.lifecyclesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * This class overrides all the lifecycle methods and prints log when a corresponding method gets called.
 * This example shows the sequence of lifecycle methods.
 */
public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "**FIRST_ACTIVITY**";
    private Button mGoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");
        setContentView(R.layout.activity_first);
        mGoButton = (Button) findViewById(R.id.button_go_to_second);

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "onClick() method is called");

                // passing values in Intent extra to the next Activity.
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("name", "paramvir");
                intent.putExtra("age", 28);
                startActivity(intent);
            }
        });
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
