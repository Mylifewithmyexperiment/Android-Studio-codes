package com.example.allinone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static int firstVisibleInListview;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    DatabaseHelper databaseHelper;
    TextView heading, subheading;
    Animation fade_in, fade_out, slide_up, slide_down;
    ImageView circular_image, square_image;
    RelativeLayout relativeLayout, main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);


        heading = findViewById(R.id.text_header);
        subheading = findViewById(R.id.sub_heading);
        circular_image = findViewById(R.id.circular_image);
        square_image = findViewById(R.id.square_image);
        relativeLayout = findViewById(R.id.header);
        main_layout = findViewById(R.id.main_layout);
        


        fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);


        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        
        Spinner spinner = findViewById(R.id.travelType_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shop_type, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        databaseHelper = new DatabaseHelper(MainActivity.this);
        databaseHelper.insertdata(R.drawable.hotel, "Seawood Shop", "shop", "vertica c 1804");
        databaseHelper.insertdata(R.drawable.hotel, "Seawood Flat ", "flat", "dombivily east ");
        databaseHelper.insertdata(R.drawable.hotel, "Seawood Resturant", "resturant", "thane ");
        databaseHelper.insertdata(R.drawable.hotel, "nucleus mall", "mall", "mumbai cst ");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String val = parent.getItemAtPosition(position).toString();
                List<DataPojo> datamodel = null;

                if (val.equals("All Locations")) {
                    datamodel = databaseHelper.getdata();

                } else {
                    datamodel = databaseHelper.getdataByChoice(val);

                }

                recyclerView = findViewById(R.id.recycler_view);
                recyclerViewAdapter = new RecyclerViewAdapter(datamodel);
                RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(reLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);

                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        // this we will use for state change that is on click and on hold
                        /*
                          The user had previously been scrolling using touch and had performed a fling. The
                          animation is now coasting to a stop
                         */

                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                            // Do something
                         /*   heading.startAnimation(slide_up);
                            subheading.startAnimation(slide_up);
                            circular_image.startAnimation(slide_up);
                            */

                      /*      heading.startAnimation(slide_up);
                            subheading.startAnimation(slide_up);
                            circular_image.startAnimation(slide_up);
                            square_image.startAnimation(slide_up);
*/
                            /*The user is scrolling using touch, and their finger is still on the screen*/
                        } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                            // Do something going down
                           /* heading.startAnimation(fade_out);
                            subheading.startAnimation(fade_out);
                            circular_image.startAnimation(fade_out);*/

                        } else {


                        }

                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        // this we are using for on scroll up and down
                        // going up  then home feed and rectangular box
                        if (dy > dx) {
                            relativeLayout.startAnimation(slide_down);
                           /* heading.startAnimation(slide_down);
                            subheading.startAnimation(slide_down);
                            circular_image.startAnimation(slide_down);
                            square_image.startAnimation(slide_down);*/
                           relativeLayout.startAnimation(fade_in);
                         //   Toast.makeText(MainActivity.this, "when going up", Toast.LENGTH_SHORT).show();
                            // write some code so that after some time it shows nothing

                        }
                        else {
                           // Toast.makeText(MainActivity.this, "what is happening now ", Toast.LENGTH_SHORT).show();
                            //going down  it display circular image only
                            relativeLayout.startAnimation(slide_up);
                           /*
                           heading.startAnimation(slide_down);
                            subheading.startAnimation(slide_down);
                            circular_image.startAnimation(slide_down);

                            */
                        }

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                heading.setVisibility(View.VISIBLE);
                heading.startAnimation(fade_in);
                heading.startAnimation(fade_out);

            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        List<DataPojo> datamodel = databaseHelper.getdata();
        recyclerViewAdapter = new RecyclerViewAdapter(datamodel);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

