package mylinkedin.example.shashi.mylinkedin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView_wattpad =(WebView) findViewById(R.id.my_wattpad);
        webView_wattpad.getSettings().setJavaScriptEnabled(true);
        webView_wattpad.setWebViewClient(new WebViewClient());
        webView_wattpad.loadUrl("https://www.wattpad.com/story/47852032");


    }
}
