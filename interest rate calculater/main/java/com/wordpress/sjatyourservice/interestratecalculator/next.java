package com.wordpress.sjatyourservice.interestratecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class next extends AppCompatActivity {
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);




        MobileAds.initialize(this,"ca-app-pub-5278704802151871~1063085021");

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




        final EditText edprincipal   =(EditText) findViewById(R.id.edprincipal);
        final EditText edrate =(EditText) findViewById(R.id.edrate);
        final EditText edtime =(EditText) findViewById(R.id.edtime);
        Button btcalculate =(Button) findViewById(R.id.btcalculate);
        final TextView tvresult =(TextView) findViewById(R.id.tvresult);





        btcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String principal1=edprincipal.getText().toString();
                String rate1=edrate.getText().toString();
                String time1 =edtime.getText().toString();


                Float principal =Float.parseFloat(principal1);
                Float rate=Float.parseFloat(rate1);
                Float time =Float.parseFloat(time1);

 Float result1 =((principal*rate*time)/100);


                tvresult.setText("Your monthly interest rupees according to rate "+rate+"% and " +
                        "for months "+time+" is Rs. " +
                        ""+result1);
            }
        });
    }








}
