package com.example.jiosecure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class OtpScreen extends AppCompatActivity
{

    //data types declarations
    public String mobile_No, otp, otpSentDetails, last_4_digits, auth_token, response_Msg;
    public int responseCode;
    // xml elements
    TextView otp_sent;
    EditText otp_digit;
    public Button verify;
    // connections
    public HttpsURLConnection conn_to_server;
    public URL url_to_server;
    public OutputStream os;
    public BufferedWriter bufferedwriter;
    public JSONObject jsonObject;
    SSLContext context = null;
    HostnameVerifier hostnameVerifier = null;
    SharedPreferences sp;
    //public final  AssetManager assetManager ;
    certificate_and_connection otp_screen_url_conn = new certificate_and_connection();
    public SharedPreferences.Editor otp_screen_editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_otp_screen);
        // defining xml view in activity
        otp_sent = findViewById(R.id.otpSent);
        otpSentDetails = otp_sent.getText().toString();
        last_4_digits = getIntent().getStringExtra("Last4Digit");
        mobile_No = getIntent().getStringExtra("Mobile_No");
        otpSentDetails = otpSentDetails + last_4_digits;
        otp_sent.setText(otpSentDetails);

        final AssetManager assetManager = getResources().getAssets();

        sp = getSharedPreferences("shared_Pref", Context.MODE_PRIVATE);
        Log.i("response code", "" + responseCode);

        // on button click of verify
        verify = findViewById(R.id.verify);
        otp_digit = findViewById(R.id.enter6digitotp);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread doInBack_netowrk_operation = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        otp = otp_digit.getText().toString();
                        Log.i("otp is:- ", otp);
                        otp_screen_editor = sp.edit();
                        otp_screen_editor.putString("sp_otp", otp);
                        Log.i("otp_screen_otp is", "" + otp);
                        Log.i("mobile no",""+mobile_No);
                        otp_screen_editor.putInt("sp_last_4_digit", Integer.parseInt(last_4_digits));// its default value is -1
                        otp_screen_url_conn.certificate_info(assetManager);
                        fcm_token_generation();
                        otp_screen_editor.commit();
                        otp_screen_url_conn.url_conn_with_data_posting_otp_screen(sp);
                        responseCode = otp_screen_url_conn.responseCode;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                responseCodeVerification();
                            }
                        });
                    }
                });
                doInBack_netowrk_operation.start();
            }
        });

    }

    // method for changing mobile no
    public void goingToLoginActivity_changing_mob_no(View view) {
        Intent i = new Intent(OtpScreen.this, LoginScreen.class);
        startActivity(i);
    }

    // method for resend otp
    public void resendOtp(View view) {
        Thread doInBack_network_operation = new Thread(new Runnable() {
            @Override
            public void run() {
                resendOtp();
            }
        });
        doInBack_network_operation.start();
    }


    protected void resendOtp() {
        final AssetManager assetManager = getResources().getAssets();
        otp_screen_url_conn.certificate_info(assetManager);
        otp_screen_url_conn.login_url_connection(sp);
    }


    private void responseCodeVerification() {
        switch (responseCode) {
            case HttpURLConnection.HTTP_ACCEPTED:

                auth_token = otp_screen_url_conn.auth_token;
                Log.i("auth token is:- ", "yes " + auth_token);
                otp_screen_editor.putString("sp_auth_token", auth_token);
                otp_screen_editor.commit();
                Intent i = new Intent(OtpScreen.this, HomeScreen.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "The Response Code is:- " + responseCode, Toast.LENGTH_LONG).show();
                break;

            case HttpURLConnection.HTTP_OK:

                auth_token = otp_screen_url_conn.auth_token;
                Log.i("auth token is:- ", "yes " + auth_token);
                otp_screen_editor.putString("sp_auth_token", auth_token);
                otp_screen_editor.commit();
                Intent i200 = new Intent(OtpScreen.this, HomeScreen.class);
                startActivity(i200);
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


    private void fcm_token_generation() {

        // [START retrieve_current_token]
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.w("get instance", "getInstanceId failed", task.getException());
                    return;
                }
                // Get new Instance ID token
                String token = task.getResult().getToken();
                Log.d("message check", token);
                otp_screen_editor.putString("sp_fcm_token", token);
                otp_screen_editor.commit();
                //   Toast.makeText(OtpScreen.this, token, Toast.LENGTH_SHORT).show();
            }
        });
        // [END retrieve_current_token]

    }

}










