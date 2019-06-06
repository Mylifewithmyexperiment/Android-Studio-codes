package com.nrxtechnologies.hoto.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nrxtechnologies.hoto.APIs;
import com.nrxtechnologies.hoto.R;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TowerDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
TextView textViewTower;
String transactionid="";
Spinner spinner ;
String[] tower = { "Select", "Yes", "No"};
String signbord,device="android",token="";
Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower_details);
        buttonSubmit=(Button)findViewById(R.id.tower_submit);
       Intent intename = getIntent();

        //Get the USERNAME passed from IntentExampleActivity
        spinner =(Spinner)findViewById(R.id.towerspinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tower);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


        final String Tower = (String) intename.getSerializableExtra("tower");
        transactionid=(String)intename.getSerializableExtra("tid");
        textViewTower=findViewById(R.id.tower);
        textViewTower.setText(Tower);
        UserDetails user=SharedPrefmanager.getInstance(this).getUser();
        //  Toast.makeText(getApplicationContext(),user.getToken(),Toast.LENGTH_LONG).show();
        token=user.getToken();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),signbord,Toast.LENGTH_LONG).show();
                postTower(Tower,signbord);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        signbord=tower[position];
      //  Toast.makeText(getApplicationContext(),signbord,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void  postTower(final String towername, final String signbord)
    {
        final ProgressDialog progressDialog=new ProgressDialog(TowerDetailsActivity.this);
        progressDialog.setMessage(" Please Wait");
        progressDialog.show();
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, APIs.FORMAPI+transactionid+"/tower",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progress bar functionaity
                        progressDialog.dismiss();
                        try {
                            Log.e("response",response);
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            if (obj.getBoolean("success")) {
                              //  Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();
                                            SweetAlertDialog alertDialog=new SweetAlertDialog(TowerDetailsActivity.this,SweetAlertDialog.SUCCESS_TYPE);
                                            alertDialog.setContentText(obj.getString("message"));
                                            alertDialog.setConfirmText("OK");
                                            alertDialog.setCanceledOnTouchOutside(false);
                                            alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    Intent intent=new Intent(TowerDetailsActivity.this,TicketActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(intent);
                                                    sweetAlertDialog.dismiss();
                                                }
                                            });
                                            alertDialog.show();
                                Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();

                                        }



                            //if no error in response
                         /*   if (obj.getBoolean("success")) {
                                Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();
                            }*/
                            else

                            {
                                Toast.makeText(getApplicationContext(), "message", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
               @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                     Map<String, String> params = new HashMap<>();
                     params.put("tower",towername );
                     params.put("signBoard", signbord);
                  //   Toast.makeText(getApplicationContext(),device+"\n"+token,Toast.LENGTH_LONG).show();

                     return params;
                 }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-request-form", device);
                headers.put("x-token-code", token);

                return headers;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
