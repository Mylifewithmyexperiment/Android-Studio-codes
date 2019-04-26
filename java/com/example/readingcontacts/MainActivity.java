package com.example.readingcontacts;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<DataPojo> dataPojos_of_contact_list_main_array = new ArrayList<>();
    MainActivity mainActivityContext;
    RecyclerViewAdapter recyclerViewAdapter;
    Toolbar toolbar;
    ProgressDialog pDialog;
    ProgressBar progressBar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityContext = this;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true); // for enabling back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Please wait while contacts are loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        Thread doInBackground = new Thread(new Runnable() {
            @Override
            public void run() {
                recyclerViewAdapter = new RecyclerViewAdapter(mainActivityContext, readContacts());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (pDialog.isShowing())
                            pDialog.dismiss();
                        recyclerView.setAdapter(recyclerViewAdapter);
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        doInBackground.start();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NextActivity.class);
                Bundle bundle = new Bundle();
                if (recyclerViewAdapter.take_contact_name != null) {
                    bundle.putString("contact_name", recyclerViewAdapter.take_contact_name);
                    bundle.putString("contact_no", recyclerViewAdapter.take_contact_number);
                } else {
                    bundle.putString("contact_name", "No Name Selected");
                    bundle.putString("contact_no", "No number Selected");
                }
                intent.putExtras(bundle);
                startActivity(intent); }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private ArrayList<DataPojo> readContacts() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        Cursor contCursor = getContentResolver().query(uri, null, null,
                null, ContactsContract.Contacts.DISPLAY_NAME);
        if (contCursor.moveToFirst()) {
            do {
                long contact_Id = contCursor.getLong(contCursor.getColumnIndex("_ID"));
                Uri dataUri = ContactsContract.Data.CONTENT_URI;
                Cursor dataCursor = getContentResolver().query(dataUri, null, ContactsContract.Data.CONTACT_ID
                        + " = " + contact_Id, null, null);
                String displayName = "";
                String mobileNo = "";
                byte[] photoByte = null;
                String PhotoPath = " " + R.drawable.reddit_icon;   // default path of photo

                if (dataCursor.moveToFirst()) {
                    displayName = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    do {
                        mobileNo = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                        if (dataCursor.getString(dataCursor.
                                getColumnIndex("mimetype")).equals(ContactsContract.CommonDataKinds
                                .Photo.CONTENT_ITEM_TYPE)) {
                            photoByte = dataCursor.getBlob(dataCursor.
                                    getColumnIndex("data15"));

                            if (photoByte != null) {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(photoByte, 0, photoByte.length);
                                File cacheDirectory = getBaseContext().getCacheDir();

                                File tmp = new File(cacheDirectory.getPath() + "/_abc" + contact_Id + ".png");

                                try {
                                    FileOutputStream fileOutputStream = new FileOutputStream(tmp);
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                                    fileOutputStream.close();

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                PhotoPath = tmp.getPath();
                            }
                        }
                    }
                    while (dataCursor.moveToNext());
                    dataPojos_of_contact_list_main_array.add(new DataPojo(displayName, mobileNo, PhotoPath));     // here we added the item to array list
                }
            } while (contCursor.moveToNext());
        }
        return dataPojos_of_contact_list_main_array;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.searchbar).getActionView();

        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                if (recyclerViewAdapter.new_radio_button.isChecked() || recyclerViewAdapter.lastChecked_Radio_button.isChecked() ) {
                    recyclerViewAdapter.new_radio_button.setChecked(false);
                    recyclerViewAdapter.lastChecked_Radio_button.setChecked(false);

                } else {
                    //if not checked then nothing to do
                    toolbar.setTitle("Search contacts");
                }
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        String userInput = s.toLowerCase();
        ArrayList<DataPojo> newString = new ArrayList<>();

        if (userInput.equals("")) {
            newString = dataPojos_of_contact_list_main_array;
            recyclerViewAdapter.updateList(newString);
        } else {
            for (DataPojo item : dataPojos_of_contact_list_main_array) {
                if (item.getContact_name().toLowerCase().contains(userInput)) {
                    newString.add(item);
                }
            }
            recyclerViewAdapter.updateList(newString);
        }
        return true;
    }
}





