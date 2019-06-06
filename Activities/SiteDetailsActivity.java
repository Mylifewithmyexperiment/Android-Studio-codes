package com.nrxtechnologies.hoto.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nrxtechnologies.hoto.APIs;
import com.nrxtechnologies.hoto.Adapters.SiteDetailsAdapter;
import com.nrxtechnologies.hoto.R;
import com.nrxtechnologies.hoto.utilities.RequestHandeler;
import com.nrxtechnologies.hoto.utilities.SessionManager;
import com.nrxtechnologies.hoto.utilities.SiteDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SiteDetailsActivity extends AppCompatActivity implements Filterable{
    Button ticket;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    SiteDetailsAdapter siteDetailAdapter;
    Toolbar toolbar;
    List<SiteDetails> ptwListFiltered;
    FloatingActionButton add_ptw_btn;
    SessionManager sessionManager;
    SearchView searchView;
    List<SiteDetails> siteDetailsList;
    SiteDetailsAdapter siteDetailsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_details);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.site_details_recyclerview);
       // sessionManager = new SessionManager(this);
        setSupportActionBar(toolbar);
       // sessionManager = new SessionManager(SiteDetailsActivity.this);
        //   recyclerView = findViewById(R.id.ptw_details);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // sessionManager=new SessionManager(SiteDetailsActivity.this);
        recyclerView=findViewById(R.id.site_details_recyclerview);

        siteDetailsList=new ArrayList<>();
        linearLayoutManager=new LinearLayoutManager(SiteDetailsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
       siteDetailsAdapter=new SiteDetailsAdapter(SiteDetailsActivity.this,siteDetailsList);

        UserDetails user=SharedPrefmanager.getInstance(this).getUser();
        //Toast.makeText(getApplicationContext(),user.getToken(),Toast.LENGTH_LONG).show();
       loadNotifications();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void loadNotifications() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, APIs.sitelistApi    ,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.INVISIBLE);
                       // imageView.setVisibility(View.INVISIBLE);
                        try{


                            JSONObject jsonObject1=new JSONObject(response);
                            if(jsonObject1.getBoolean("success")) {
                                JSONArray jsonArray = jsonObject1.getJSONArray("data");
                                for(int i=0;i<jsonArray.length();i++)
                                {
                                    JSONObject jsonObject2=jsonArray.getJSONObject(i);
                                    JSONObject jsonObject3=jsonObject2.getJSONObject("site");
                                    JSONObject jsonObject4=jsonObject3.getJSONObject("cluster");

                                    siteDetailsList.add(new SiteDetails(
                                            jsonObject2.getString("ticketID"),
                                            jsonObject2.getString("plannedDate"),
                                            jsonObject2.getString("status"),
                                            jsonObject3.getString("name"),
                                            jsonObject3.getString("tower"),jsonObject3.getString("_id"),
                                            jsonObject3.getString("customer"),jsonObject3.getString("state"),
                                            jsonObject3.getString("circle"),jsonObject4.getString("name"),
                                            jsonObject3.getString("address"),jsonObject3.getString("_id"),
                                            jsonObject3.getString("siteType"),jsonObject2.getString("_id")));
                                }

                                //creating adapter object and setting it to recyclerview
                                SiteDetailsAdapter adapter = new SiteDetailsAdapter(SiteDetailsActivity.this, siteDetailsList);
                                recyclerView.setAdapter(adapter);
                                }
                            else
                            {
                                Toast.makeText(SiteDetailsActivity.this, "Something  went rong ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e)
                        {
                            Toast.makeText(SiteDetailsActivity.this, " Error : "+e.toString(), Toast.LENGTH_LONG).show(); e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
       RequestHandeler.getInstance(this).addToRequestQueue(stringRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
            //    siteDetailsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
               // siteDetailAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.profile) {
            return true;
        }
        if (id == R.id.logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        Intent intent=new Intent(SiteDetailsActivity.this,SiteDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }*/

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    ptwListFiltered.addAll(siteDetailsList);
                } else {
                    List<SiteDetails> filteredList = new ArrayList<>();
                    for (SiteDetails datum : siteDetailsList)
                    {
                        if (datum.getSite_Name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(datum);
                        }
                    }
                    ptwListFiltered=filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = ptwListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ptwListFiltered = (List<SiteDetails>) results.values;
                //notifyDataSetChanged();
            }
        };
    }
}
