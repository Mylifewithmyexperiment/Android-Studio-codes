package com.wordpress.sjatyourservice.jharkhandcarwale;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH =1500;
	private AdView mAdView;
	private WebView web;
	private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		MobileAds.initialize(this, "ca-app-pub-5278704802151871~5035122921");

		setContentView(R.layout.activity_main);

		new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub


				interstitialAd = new InterstitialAd(MainActivity.this);
				interstitialAd.setAdUnitId(" ca-app-pub-5278704802151871/1678509451");// testing ad unit id
				// interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				//		.build());
				//ca-app-pub-5278704802151871/4071897217
				interstitialAd.loadAd(new AdRequest.Builder().build());

				interstitialAd.setAdListener(new AdListener() {
					@Override
					public void onAdClosed() {

						Intent shashi=new Intent(MainActivity.this,next.class);
						MainActivity.this.startActivity(shashi);
						MainActivity.this.finish();


						interstitialAd.loadAd(new AdRequest.Builder().build());
					}
				});


				if (interstitialAd.isLoaded())

				{
					interstitialAd.show();
				} else {

					Intent shashi=new Intent(MainActivity.this,next.class);
					MainActivity.this.startActivity(shashi);
					MainActivity.this.finish();

				}





            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}



/***
 ca-app-pub-5278704802151871~5035122921

 ca-app-pub-5278704802151871/2217387890 banner

 ca-app-pub-5278704802151871/1678509451 interstitial

 **/