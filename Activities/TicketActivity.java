package com.nrxtechnologies.hoto.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.nrxtechnologies.hoto.Adapters.Ticket_List_Adapter;
import com.nrxtechnologies.hoto.LoginActivity;
import com.nrxtechnologies.hoto.R;
import com.nrxtechnologies.hoto.VolleySingleton;
import com.nrxtechnologies.hoto.utilities.Ticket_List;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TicketActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    public static String Tower="",Transactionid="";
    Spinner spinner ;
    String device="android";

    String[] tower = { "Select","Non EB", "Non DG","Std Site(EB+DG)"};
TextView textView_TicketId,textView_StartTime,textViewCName,textViewState,textView_Circle,textView_CMP,textView_Sitename,
    textView_Site_Address,textViewSite_Id,textViewTypeofsite,textViewSourseofPower;
String Ticket="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        textView_TicketId=(TextView)findViewById(R.id.textView_Ticketid);
        textView_StartTime=(TextView)findViewById(R.id.textView_HST);

        textViewCName=(TextView)findViewById(R.id.textView_TCN);

        textViewState=(TextView)findViewById(R.id.textVew_State);

        textView_Circle=(TextView)findViewById(R.id.textView_NOTC);

        textView_CMP=(TextView)findViewById(R.id.textView_CMP);

        textView_Sitename=(TextView)findViewById(R.id.textView_NOTS);

        textView_Site_Address=(TextView)findViewById(R.id.textView_Site_Address);

        textViewSite_Id=(TextView)findViewById(R.id.textView_SiteId);
        textViewTypeofsite=(TextView)findViewById(R.id.textView_Site_Type);
        spinner= (Spinner) findViewById(R.id.Spinner_sourseofpower);
        Intent intename = getIntent();
         Tower = (String) intename.getSerializableExtra("tower");
       String ticket = (String) intename.getSerializableExtra("ticketid");

        String cname = (String) intename.getSerializableExtra("cname");

        String  state = (String) intename.getSerializableExtra("state");

        String circle = (String) intename.getSerializableExtra("circle");

        String cmp = (String) intename.getSerializableExtra("cmp");

      String   site = (String) intename.getSerializableExtra("site");

        String   sitetype = (String) intename.getSerializableExtra("sitetype");

        String   address = (String) intename.getSerializableExtra("address");

        String   id = (String) intename.getSerializableExtra("id");
        Ticket = (String) intename.getSerializableExtra("ticket");

        textView_TicketId.setText(ticket);
        textView_StartTime.setText("");
        textViewCName.setText(cname);
        textView_Circle.setText(circle);
        textViewSite_Id.setText(id);
textView_Site_Address.setText(address);
textViewTypeofsite.setText(sitetype);
textView_Sitename.setText(site);
textViewTypeofsite.setText(sitetype);
textViewState.setText(state);
        textView_CMP.setText(cmp);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tower);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);
        //Toast.makeText(TicketActivity.this, Tower, Toast.LENGTH_LONG).show();
        Ticket_List[] myListData = new Ticket_List[] {
                new Ticket_List("Tower Details"),
                new Ticket_List("Electric Connection"),
                new Ticket_List("Air Conditioners"),
                new Ticket_List("Power Plant Details"),
                new Ticket_List("Diesel Generator (DG)"),
                new Ticket_List("Shelter"),
                new Ticket_List("Outdoor Cabinet"),
                new Ticket_List("Battery Set"),
                new Ticket_List("External Tenants Personal details"),
                new Ticket_List("Power Management System"),
                new Ticket_List("General & Safety"),
                new Ticket_List("ACDB/DCDB"),
                new Ticket_List("Servo Stabilizer")

        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ticket_recyclerview);
        Ticket_List_Adapter adapter = new Ticket_List_Adapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        UserDetails user=SharedPrefmanager.getInstance(this).getUser();
      //  Toast.makeText(getApplicationContext(),user.getToken(),Toast.LENGTH_LONG).show();

        getTransactionId(Ticket,device,user.getToken());
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void getTransactionId(final String ticket, final String device, final String token)
    {

        final ProgressDialog progressDialog=new ProgressDialog(TicketActivity.this);
        progressDialog.setMessage(" Please Wait");
        progressDialog.show();
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.GET, APIs.transactionid+ticket,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progress bar functionaity
                        progressDialog.dismiss();
                        try {
                            Log.e("response",response);
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (obj.getBoolean("success")) {
                                JSONObject user = obj.getJSONObject("data");
                                Transactionid=user.getString("transactionID");
                               // Toast.makeText(getApplicationContext(), Transactionid, Toast.LENGTH_SHORT).show();


                            }
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
       /*     @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("x-request-form", device);
                params.put("x-token-code", token);
             //   Toast.makeText(getApplicationContext(),device+"\n"+token,Toast.LENGTH_LONG).show();

                return params;
            }*/
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

