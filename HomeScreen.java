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


public class HomeScreen extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    private List<DataPOJO> abc;
 //   private ActionBar action_toolbar;
    Toolbar toolbar_for_bottom_fragment;
    public JSONObject jsonObject;
    SSLContext context = null;
    HostnameVerifier hostnameVerifier = null;
    SharedPreferences sp;
    int responseCode;

    String data[]={ "fygj","vhg","dfj"};

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
       setContentView(R.layout.activity_home_screen);




        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                get_All_Devices();
            }
        });
        daemonThread.start();

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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
      /*  CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
       // toolbar_for_bottom_fragment.setTitle("Shop");
        // loadFragment(new StoreFragment());
*/




        int no_of_column = 2;
        abc = filling_recycler_view_with_images();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, no_of_column));

       recyclerView.setAdapter(new RecyclerViewAdapter(data));

       /* adapter = new MyRecyclerViewAdapter(this, abc);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);*/
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


    // for assigning refresh options

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



// section out of onCreate Method

    public void onItemClick(View view, DataPOJO dataPOJO) {
        Toast.makeText(this, "You clicked " + dataPOJO.getTopic() + " on image  ", Toast.LENGTH_SHORT).show();
    }
    // for accessing that images

    public List<DataPOJO> filling_recycler_view_with_images() {

        List<DataPOJO> abc = new ArrayList<>();
        abc.add(new DataPOJO(R.drawable.buzzer_150, "Buzzer", "Connected"));
        abc.add(new DataPOJO(R.drawable.door_150, "DoorSensor", "Not Connected"));
        abc.add(new DataPOJO(R.drawable.motion_150, "Motion", "Connected"));
        return abc;

    }
    void toast(){
        Toast.makeText(this, "home screen"+ sp.getString("sp_otp",null)+" and mobile no is "+sp.getString("mobile_number_sp",null), Toast.LENGTH_SHORT).show();

    }

    protected void certificate_info() {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            AssetManager assetManager = getResources().getAssets();
            InputStream caInput = assetManager.open("sslnbiot.cer");
            Certificate ca = cf.generateCertificate(caInput);

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                    return hv.verify("iotconnect.rancoretech.com", session);
                }
            };
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

    }

    protected void get_All_Devices() {
        certificate_info();
    //    SharedPreferences.Editor home_screen_Editor = sp.edit();

        sp = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        sp.getString("mobile_number_sp",null);
        sp.getString("sp_otp",null);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               toast();
            }
        });




        try {
            URL url = new URL("https://116.50.76.148:8074/nbiot/appRest/");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(context.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(hostnameVerifier);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("operation", "getalldevices");
            httpsURLConnection.setRequestProperty("token", "123456");
            httpsURLConnection.setRequestProperty("mobile",  sp.getString("mobile_number_sp",null));
            httpsURLConnection.setRequestProperty("refresh", "true/false");
            httpsURLConnection.setDoOutput(true);
            OutputStream outStream = httpsURLConnection.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
            jsonObject = new JSONObject();
            jsonObject.put("mobile",  sp.getString("mobile_number_sp",null));
            Log.i("Check_mo_no", "Mob is:-" +  sp.getString("mobile_number_sp",null));
            jsonObject.put("otp", sp.getString("sp_otp",null));                   // from here otp is sent and it verified by server itself from this end only.
            jsonObject.put("Android_key", "12345");
            jsonObject.put("fcm_token", "achievement");
            jsonObject.put("device_type", "android");
            //  auth_token = httpsURLConnection.getHeaderField("auth_token");
         //   jsonObject.put("auth_token", auth_token); // how can we send response header in request body as it said request can't be send after response has been read.
            jsonObject.put("client_id", 12345);
            outStreamWriter.write(jsonObject.toString());
            outStreamWriter.flush();
            outStreamWriter.close();
            outStream.close();
            System.out.println(httpsURLConnection.getResponseCode());
            responseCode= httpsURLConnection.getResponseCode();
            Log.i("res code"," :-> "+httpsURLConnection.getResponseCode());
            System.out.println(httpsURLConnection.getResponseMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
/**
 super.onCreateOptionsMenu(menu);


 **/