package com.wordpress.sjatyourservice.javajargons;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH =2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent shashi=new Intent(MainActivity.this,Questions.class);
                MainActivity.this.startActivity(shashi);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);





    }
}
