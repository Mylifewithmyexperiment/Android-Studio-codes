package com.example.allinone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Screen2 extends AppCompatActivity {

    Toolbar toolbar;
    String location_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitle(location_names);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);                   // for enabling back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView image_of_items = findViewById(R.id.image_of_item);
        TextView location_type = findViewById(R.id.get_location_type);
        TextView location_name = findViewById(R.id.get_location_name);
        TextView location_address = findViewById(R.id.get_location_address);
        ImageView deleteIcon = findViewById(R.id.delete);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


        Bundle bundle = getIntent().getExtras();
        location_names = (String) bundle.get("location_name");
        String location_types = (String) bundle.get("location_type");
        String locationAddress = (String) bundle.get("all_address");
        int image_id = (int) bundle.get("all_image");

        Log.i("image id", " " + image_id);


        location_name.setText(location_names);
        location_type.setText(location_types);
        location_address.setText(locationAddress);
//      image_of_items.setImageResource(image_id);   //check


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Screen2.this, MainActivity.class);
        startActivity(i);

        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            Intent i = new Intent(Screen2.this, MainActivity.class);
            startActivity(i);
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }

    private void openDialog() {
        // method to open dialog to delete the particular entity

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to delete the item?");


// Set up the buttons
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


    }


}
