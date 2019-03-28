package com.example.jiosecure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
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

public class certificate_and_connection {

    public HttpsURLConnection conn_to_server;
    public URL url_to_server;
    public OutputStream os;
    public BufferedWriter bufferedwriter;
    public JSONObject jsonObject;
    SSLContext context = null;
    HostnameVerifier hostnameVerifier = null;
    SharedPreferences sp;
    int responseCode;
    String response_Msg, auth_token;
    public URL url;
    public HttpsURLConnection conn;


    protected void certificate_info(AssetManager assetManager) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            //     AssetManager assetManager = getResources().getAssets();
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

    protected void login_url_connection(SharedPreferences sp) {


        try {
            url = new URL("https://116.50.76.148:8074/nbiot/appRest/");    // it is server ip
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(context.getSocketFactory());
            conn.setHostnameVerifier(hostnameVerifier);
            conn.setReadTimeout(10000);      //10  sec
            conn.setConnectTimeout(10000);  // 10  sec
            conn.setRequestMethod("POST");
            conn.addRequestProperty("operation", "getotp");
           /*String mobile_no=  sp.getString("sp_mobile_no",null);
            Log.i("mobile no is",mobile_no);*/
            conn.addRequestProperty("X-user.MSISDN ", sp.getString("sp_mobile_no", null));
            conn.addRequestProperty("androidkey", sp.getString("sp_mobile_no", null));
            conn.setDoInput(true);    //you intend to use the URL connection for input
            conn.setDoOutput(true);   // you intend to use the URL connection for output
            responseCode = conn.getResponseCode();
            Log.i("Res Code", "response Code is   " + responseCode);
            //  conn.getInputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("test", "sendingDataWithBody() called exception " + e.toString());
        }

    }

    protected void url_conn_with_data_posting_otp_screen(SharedPreferences sp) {
        try {

            URL url = new URL("https://116.50.76.148:8074/nbiot/appRest/");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(context.getSocketFactory());   // verification
            httpsURLConnection.setHostnameVerifier(hostnameVerifier);
            httpsURLConnection.setRequestMethod("POST");    //method type
            // header added
            httpsURLConnection.setRequestProperty("Accept", "application/xml");
            httpsURLConnection.setRequestProperty("operation", "login");
            httpsURLConnection.setRequestProperty("Content-Type", "application/xml");
            httpsURLConnection.setDoOutput(true);
            OutputStream outStream = httpsURLConnection.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
            jsonObject = new JSONObject();
            jsonObject.put("mobile", sp.getString("sp_mobile_no", null));
            Log.i("mobile_number_body ",""+sp.getString("sp_mobile_no", null));
            jsonObject.put("otp", sp.getString("sp_otp", null));                   // from here otp is sent and it verified by server itself from this end only.
            Log.i("otp_no_from_body"," "+sp.getString("sp_otp", null));
            jsonObject.put("Android_key", sp.getString("sp_mobile_no", null));
            jsonObject.put("fcm_token", sp.getString("sp_fcm_token", null));// check
            Log.i("fcm_token"," "+sp.getString("sp_fcm_token", null));
            jsonObject.put("device_type", "android");
            jsonObject.put("client_id", sp.getString("sp_mobile_no", null));
            outStreamWriter.write(jsonObject.toString());
            Log.i("complete_body"," "+jsonObject.toString());
            outStreamWriter.flush();
            outStreamWriter.close();
            outStream.close();
            responseCode = httpsURLConnection.getResponseCode();
            Log.i("res code", " :-> " + responseCode);
            response_Msg = httpsURLConnection.getResponseMessage();
            Log.i("res_msg", "" + response_Msg);
            auth_token = httpsURLConnection.getHeaderField("auth_token");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void get_All_Devices(SharedPreferences sp) {
        sp.getString("sp_mobile_no", null);
        sp.getString("sp_otp", null);


        try {
            URL url = new URL("https://116.50.76.148:8074/nbiot/appRest/");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(context.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(hostnameVerifier);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("operation", "getalldevices");
            httpsURLConnection.setRequestProperty("token", sp.getString("sp_auth_token", null));
            httpsURLConnection.setRequestProperty("mobile", sp.getString("sp_mobile_no", null));
            httpsURLConnection.setRequestProperty("refresh", "true");
            httpsURLConnection.setDoOutput(true);
            System.out.println(httpsURLConnection.getResponseCode());
            responseCode = httpsURLConnection.getResponseCode();
            Log.i("res code", " :-> " + responseCode);
            response_Msg = httpsURLConnection.getResponseMessage();
            Log.i("res Msg in json", response_Msg);
            // retrieve the response from server
            InputStream is = null;
            is = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            int ch;
            StringBuffer sb = new StringBuffer();
            while ((ch = is.read()) != -1) {
                sb.append((char) ch);

                Log.i("res", sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
