package com.example.shashi.mytweets.mytweets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.MobileAds;

import ca.blogspot.sjatyourservice.mytweets.R;


public class MainActivity extends AppCompatActivity
{
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        MobileAds.initialize(this, "ca-app-pub-5278704802151871~5369551762");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView_More_about_jharkhand=(WebView) findViewById(R.id.my_tweets);
        webView_More_about_jharkhand.getSettings().setJavaScriptEnabled(true);
        webView_More_about_jharkhand.setWebViewClient(new WebViewClient());
        webView_More_about_jharkhand.loadUrl("https://twitter.com/shashijaiswal17");

    }

}
/**

 My Tweetsca-app-pub-5278704802151871~5369551762
 banner my tweets jioca-app-pub-5278704802151871/2192724085



 **/