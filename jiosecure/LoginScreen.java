package com.example.jiosecure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

public class LoginScreen extends AppCompatActivity {

    public EditText Mob_no;
    public String mobileNo;
    public String last_4_digit;
    public String mobile_no;
    public Intent i;
    public HttpsURLConnection conn;
    public URL url;
    public String auth_token;
    public int responseCode;
    public Button login;
    public ProgressBar simpleProgressBar;
    SharedPreferences sp;
    public SharedPreferences.Editor login_Screen_Editor;
    certificate_and_connection login_url_conn = new certificate_and_connection() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_RIGHT_ICON);
        setContentView(R.layout.activity_login_screen);

        Mob_no = findViewById(R.id.phone_no);
        login = findViewById(R.id.login_button);
        simpleProgressBar = findViewById(R.id.simple_progressBar);
        sp = getSharedPreferences("shared_Pref", Context.MODE_PRIVATE);
        final  AssetManager assetManager = getResources().getAssets();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleProgressBar.setVisibility(View.VISIBLE);
                mobile_no = Mob_no.getText().toString();

                if (mobile_no.length() < 10)  // when we need top access something from inner class it need to be declared final.
                {
                    Toast.makeText(getApplicationContext(), "Enter 10 digit Mobile No ", Toast.LENGTH_LONG).show();
                } else {

                    login_Screen_Editor =sp.edit();
                    login_Screen_Editor.putString("sp_mobile_no",mobile_no);
                    login_Screen_Editor.commit();
                    Thread doInBack_network_operation = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            login_url_conn.certificate_info(assetManager);
                            login_url_conn.login_url_connection(sp);
                           // login_url_connection();
                          responseCode = login_url_conn.responseCode;
                          Log.i("res code log screen", ""+responseCode);
                            //conn = login_url_conn.conn;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                 responseCodeVerification();
                                }
                            });
                        }
                    });
                    doInBack_network_operation.start();
                }
            }
        });


    }


    private void responseCodeVerification() {
        switch (responseCode) {
            case HttpURLConnection.HTTP_ACCEPTED:
                Log.i("Accepted", "res code" + HttpsURLConnection.HTTP_ACCEPTED);

                last_4_digit = mobile_no.substring(6, 10);
                i = new Intent(LoginScreen.this, OtpScreen.class);
                i.putExtra("Last4Digit", last_4_digit);
                i.putExtra("Mobile_No", mobile_no);     // for placing this in json body.
                startActivity(i);
               break;

            case HttpURLConnection.HTTP_OK:
                Toast.makeText(getApplicationContext(), "The Response Code is:- " + responseCode, Toast.LENGTH_LONG).show();
                break;

            case HttpsURLConnection.HTTP_FORBIDDEN:
                Toast.makeText(getApplicationContext(), "The Response Code is:- " + responseCode, Toast.LENGTH_LONG).show();
                break;

            case HttpURLConnection.HTTP_BAD_REQUEST:
                Toast.makeText(getApplicationContext(), "The Response Code is:- " + responseCode, Toast.LENGTH_LONG).show();
                break;

            case HttpURLConnection.HTTP_BAD_GATEWAY:
                Toast.makeText(getApplicationContext(), "The Response Code is:- " + responseCode, Toast.LENGTH_LONG).show();
                break;

            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                Toast.makeText(getApplicationContext(), "The Response Code is:- " + responseCode, Toast.LENGTH_LONG).show();
                break;

            case HttpURLConnection.HTTP_NOT_FOUND:

                Toast.makeText(getApplicationContext(), "The Response Code is:- " + responseCode, Toast.LENGTH_LONG).show();
                break;

            case HttpURLConnection.HTTP_CONFLICT:
                Toast.makeText(getApplicationContext(), "The Response Code is:- " + responseCode, Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "The default response Code is " + responseCode, Toast.LENGTH_LONG).show();
        }

    }


}


