package com.example.jiosecure;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;


public class HomeScreen extends AppCompatActivity
      //  implements MyRecyclerViewAdapter.ItemClickListener
{

    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    private List<DataPOJO> abc;
    private List<DataPOJO> dataPOJOList_of_image_title;

    List<DataPOJO> dataPOJOList_containing_all_getter_and_setter;

    DataPOJO dataPOJO_of_image_title_of_pojo_type;
    Toolbar toolbar_for_bottom_fragment;
    public JSONObject jsonObject;

    SharedPreferences sp;
    int responseCode;
    String responseMsg;

    certificate_and_connection home_screen_conn = new certificate_and_connection();


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_home_screen);

        AssetManager assetManager = getResources().getAssets();

        home_screen_conn.certificate_info(assetManager);
        sp = getSharedPreferences("shared_Pref", Context.MODE_PRIVATE);

        Log.i("getAllDevice","In get all device");
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
               home_screen_conn.get_All_Devices(sp);
               responseCode=home_screen_conn.responseCode;
               responseMsg = home_screen_conn.response_Msg;
               Log.i("responseMsg",".."+responseMsg);
            }
        });
        daemonThread.start();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        int no_of_column = 2;
        dataPOJOList_containing_all_getter_and_setter = filling_recycler_view_with_images();
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, no_of_column));
       recyclerView.setAdapter(new RecyclerViewAdapter(dataPOJOList_containing_all_getter_and_setter));

    }


    private  BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_dashboard:
                    Toast.makeText(getApplicationContext(),"You clicked Dashboard",Toast.LENGTH_LONG).show();
                   return  true;

                case R.id.nav_history:
                    Toast.makeText(getApplicationContext(), "You clicked History", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.nav_settings:
                    Toast.makeText(getApplicationContext(), "You clicked Settings", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_setting_refresh, menu);
        return true;  // return true so that menu popup is open
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.homes_spinner:
                Toast.makeText(getApplicationContext(),"You clicked on home",Toast.LENGTH_LONG).show();
                return true;


            case R.id.settings:
                Toast.makeText(getApplicationContext(), "You clicked on Setting button", Toast.LENGTH_SHORT).show();
               return true;


            case  R.id.refresh:
                Toast.makeText(getApplicationContext(), "you clicked on refresh button", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    // from json array we have to fetch data and put it here.
    String deviceName= "Buzzer";
    String deviceDescription = "connected";

        public List<DataPOJO> filling_recycler_view_with_images() {
        List<DataPOJO> abc = new ArrayList<>();
        abc.add(new DataPOJO(R.drawable.buzzer_150, deviceName, deviceDescription));
        abc.add(new DataPOJO(R.drawable.door_150, "DoorSensor", "Not Connected"));
        abc.add(new DataPOJO(R.drawable.motion_150, "Motion", "Connected"));
        return abc;

    }

}



 /*  // displaying toolbar_for_bottom_fragment and icons present over there
       toolbar_for_bottom_fragment = findViewById(R.id.app_bar_bottom_layout_tool_bar);
       setSupportActionBar(toolbar_for_bottom_fragment);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
       toolbar_for_bottom_fragment.setTitle("asc");


       // for bottom navigation View
       *//*

        action_toolbar = getSupportActionBar();
        action_toolbar.setTitle("Jio Secure");

        *//*
 */



// attaching bottom sheet behaviour - hide / show on scroll
      /*  CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
       // toolbar_for_bottom_fragment.setTitle("Shop");
        // loadFragment(new StoreFragment());
*/



/*
        adapter = new MyRecyclerViewAdapter(this, data_pojo_list);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

*/