package com.wordpress.sjatyourservice.temperatureconversion;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {



    private final int SPLASH_DISPLAY_LENGTH =1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this,"ca-app-pub-5278704802151871~6014550454");


        setContentView(R.layout.activity_main);new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent shashi=new Intent(MainActivity.this,next.class);
                MainActivity.this.startActivity(shashi);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
/**

 App ID: ca-app-pub-5278704802151871~6014550454
 Ad unit ID: ca-app-pub-5278704802151871/1458204256
 **/