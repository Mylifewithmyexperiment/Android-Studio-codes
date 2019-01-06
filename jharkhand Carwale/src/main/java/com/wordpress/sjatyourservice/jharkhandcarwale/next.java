package com.wordpress.sjatyourservice.jharkhandcarwale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class next extends AppCompatActivity {
	private AdView mAdView;
	private WebView web;
	private InterstitialAd interstitialAd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);


		web = (WebView) findViewById(R.id.web);

		MobileAds.initialize(this, "ca-app-pub-5278704802151871~5035122921");

		mAdView = (AdView) findViewById(R.id.adView);

		AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();

		mAdView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				mAdView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAdFailedToLoad(int i) {
				mAdView.setVisibility(View.GONE);
			}
		});

		mAdView.loadAd(adRequest);


		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId(" ca-app-pub-5278704802151871/1678509451");// testing ad unit id
		// interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		//		.build());
		//ca-app-pub-5278704802151871/4071897217
		interstitialAd.loadAd(new AdRequest.Builder().build());

		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {


				web.getSettings().setJavaScriptEnabled(true);
				web.setWebViewClient(new WebViewClient());
				web.loadUrl("http://jharkhandcarwale.wordpress.com");

				// interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest
				//		 .DEVICE_ID_EMULATOR).build());

				interstitialAd.loadAd(new AdRequest.Builder().build());
			}
		});


		if (interstitialAd.isLoaded())

		{
			interstitialAd.show();
		} else {

			web.getSettings().setJavaScriptEnabled(true);
			web.setWebViewClient(new WebViewClient());
			web.loadUrl("http://jharkhandcarwale.wordpress.com");
		}


	}


}





/***
 ca-app-pub-5278704802151871~5035122921

 ca-app-pub-5278704802151871/2217387890 banner

 ca-app-pub-5278704802151871/1678509451 interstitial

 **/




















