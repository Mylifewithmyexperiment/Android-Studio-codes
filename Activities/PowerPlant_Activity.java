package com.nrxtechnologies.hoto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nrxtechnologies.hoto.Activities.SharedPrefmanager;
import com.nrxtechnologies.hoto.Activities.TicketActivity;
import com.nrxtechnologies.hoto.Activities.UserDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.nrxtechnologies.hoto.APIs.transactionid;

public class PowerPlant_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String [] no_of_powerPlant ={"1","2"};
    String [] no_of_working_powerPlant ={"1","2"};
    String [] no_of_module_slot={"1","2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_plant);

        Spinner no_of_power_plant = findViewById(R.id.d1spinner);
        Spinner no_of_working_power_plant = findViewById(R.id.d2spinner);
        Spinner no_of_module_slot = findViewById(R.id.d5spinner);
        Spinner power_plant_earthing_status = findViewById(R.id.d6spinner);
        EditText Dc_load_in_display = findViewById(R.id.editText_powerPlant_dc_load);
        Button save = findViewById(R.id.save);

        no_of_power_plant.setOnItemSelectedListener(this);
        no_of_working_power_plant.setOnItemSelectedListener(this);
        no_of_module_slot.setOnItemSelectedListener(this);
        power_plant_earthing_status.setOnItemSelectedListener(this);

        Intent getIntent = getIntent();   /** Return the intent that started this activity. */


        //we have to set array adapter data on spinner of all elements
        ArrayAdapter aa_no_of_power_plant = new ArrayAdapter(this, android.R.layout.simple_spinner_item,no_of_powerPlant);
        aa_no_of_power_plant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        no_of_power_plant.setAdapter(aa_no_of_power_plant);



        ArrayAdapter aa_no_of_working_module  = new ArrayAdapter(this,android.R.layout.simple_spinner_item , no_of_working_powerPlant);
        aa_no_of_working_module.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        no_of_working_power_plant.setAdapter(aa_no_of_working_module);

      // ArrayAdapter aa_no_of_module_slot = new ArrayAdapter(this, android.R.layout.simple_spinner_item, no_of_module_slot);


        final String Count = (String) getIntent.getSerializableExtra("count");
        final String WorkingCount = (String) getIntent.getSerializableExtra("workingCount");

        UserDetails user= SharedPrefmanager.getInstance(this).getUser();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPowerPlant(Count,WorkingCount);
            }

        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void postPowerPlant(final  String count, final String workingCount)  {
        final ProgressDialog progressDialog=new ProgressDialog(PowerPlant_Activity.this);
        progressDialog.setMessage(" Please Wait");
        progressDialog.show();
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, APIs.FORMAPI+transactionid+"/powerPlant",
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
                                SweetAlertDialog alertDialog=new SweetAlertDialog(PowerPlant_Activity.this,SweetAlertDialog.SUCCESS_TYPE);
                                alertDialog.setContentText(obj.getString("message"));
                                alertDialog.setConfirmText("OK");
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent intent=new Intent(PowerPlant_Activity.this, TicketActivity.class);
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
                params.put("count",count );
                params.put("workingCount", workingCount);
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
