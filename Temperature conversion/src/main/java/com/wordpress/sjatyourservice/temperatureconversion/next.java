package com.wordpress.sjatyourservice.temperatureconversion;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



public class next extends AppCompatActivity {
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);





        MobileAds.initialize(this,"ca-app-pub-5278704802151871~6014550454");

        mAdView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAdFailedToLoad (int i)
            {
                mAdView.setVisibility(View.GONE);
            }
        });

        mAdView.loadAd(adRequest);



        final EditText edfar=(EditText) findViewById(R.id.ed_far);
        final EditText edcel=(EditText) findViewById(R.id.ed_Cel);
        Button bt_far_to_cel=(Button) findViewById(R.id.bt_con_far_to_cel);
        final Button bt_cel_to_far=(Button) findViewById(R.id.bt_con_cel_to_far);
        final TextView tv_result_cel=(TextView) findViewById(R.id.tv_result_celsius);
        final TextView tv_result_far=(TextView) findViewById(R.id.tv_result_farenheit);
      //  final TextView tv_general=(TextView) findViewById(R.id.tv_general);




    bt_far_to_cel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ed_farenheit = edfar.getText().toString();
            int Farenheit = Integer.parseInt(ed_farenheit);


         int  result_celsius = (((Farenheit - 32) * 5) / 9);

            tv_result_cel.setText("Temp is :" + result_celsius + " C");




        }
    });


    bt_cel_to_far.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ed_celsius = edcel.getText().toString();
            int Celsius = Integer.parseInt(ed_celsius);


          int result_Farenheit = (((Celsius * 9) / 5) + 32);




                tv_result_far.setText("Temp is :" + result_Farenheit + " F");



        }
    });







    }


}










/**
 *import android.support.v4.view.ViewCompat;
 *import static com.wordpress.sjatyourservice.temperatureconversion.R.id.adView;
 *
 * if(ed_celsius==null)
 {
 tv_result_far.setText(" 17 ");
 }
 else

 tv_result_far.setText(" 18 ");


 String ed_celsius=edcel.getText().toString();
 Float Celsius=Float.parseFloat(ed_celsius);


 String ed_farenheit=edfar.getText().toString();
 Float Farenheit =Float.parseFloat(ed_farenheit);



 else
 tv_general.setText(" Please enter temperature in one box for conversion ");



 if (Farenheit!=null && Celsius==null ) {

 tv_result_cel.setText("Temp is :" + result_celsius + " C");
 }

 else
 tv_general.setText(" Please enter temperature in one box for conversion ");





 if (Celsius!=null && Farenheit==null) {


 tv_result_far.setText("Temp is :" + result_Farenheit + " F");

 }

 else
 tv_general.setText(" Please enter temperature in one box for conversion ");




 final String ed_celsius = edcel.getText().toString();
 Float Celsius =
 Float.parseFloat(ed_celsius);

 final String ed_farenheit = edfar.getText().toString();
 final Float Farenheit = Float.parseFloat(ed_farenheit);





 String ed_celsius = edcel.getText().toString();
 Float Celsius = Float.parseFloat(ed_celsius);

//.addTestDevice("C04B1BFFB0774708339BC273F8A43708")


 **/