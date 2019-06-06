package com.nrxtechnologies.hoto.Activities;

import android.annotation.SuppressLint;
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

public class ElectricActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spin ,spin8,spin10,spin11 ;
    String transactionid="";
    Button submit;

    String[] ebmeterboxspinner = {  "Select", "Ok", "Minor damage", "Fully Damaged", "Corroded & Painting Required", "Not Available"};
    String[] b8 = {  "Select",  "Faulty", "Display  Faulty" };
    String[] b10 = {  "Select",  "Available", "Not Available", "Not Required" };
    String[] b11 = { "Select",   "Available", "Fuse Damaged", "Not Available" };
    String ecType,eb,meterReading,neutralEarthing,serielNo,device="android",token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);
        Intent intename = getIntent();

         spin =  findViewById(R.id.ebmeterboxspinner);
        spin.setOnItemSelectedListener(this);
         spin8 =  findViewById(R.id.b8spinner);
        spin8.setOnItemSelectedListener(this);
         spin10 =  findViewById(R.id.b10spinner);
        spin10.setOnItemSelectedListener(this);
        spin11 = findViewById(R.id.b11spinner);
        spin11.setOnItemSelectedListener(this);
        submit=(Button)findViewById(R.id.electric_submit) ;

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ebmeterboxspinner);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,b8);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin8.setAdapter(aa1);

        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,b10);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin10.setAdapter(aa2);

        ArrayAdapter aa3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,b11);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin11.setAdapter(aa3);



        UserDetails user=SharedPrefmanager.getInstance(this).getUser();
        //  Toast.makeText(getApplicationContext(),user.getToken(),Toast.LENGTH_LONG).show();
        token=user.getToken();



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
