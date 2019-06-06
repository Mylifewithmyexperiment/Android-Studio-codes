package com.nrxtechnologies.hoto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nrxtechnologies.hoto.Activities.AirConditionerActivity;

public class MainActivity extends AppCompatActivity {
Button aircondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aircondition=(Button)findViewById(R.id.air_conditioner);
        aircondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), AirConditionerActivity.class);
                startActivity(i);
            }
        });

    }
}
