package com.example.yourcontacts;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    //variables declarations
    FloatingActionButton fab;
    SearchView searchView;
    CustomUsersAdapter adapter;
    ListView listView;
    ProgressBar progressBar;
    ArrayList<DataPojo> dataPojos_of_contact_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);    // setting progress bar untill data are being loaded
        //toolbar.setTitle("Read Contacts");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        listView = findViewById(R.id.list_view);
        listView.setTextFilterEnabled(true);
        populateUsersList();       // method for displaying data on screen
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(adapter.intent);
            }
        });
    }

       private void populateUsersList() {
        Thread doInBackground = new Thread(new Runnable() {
            @Override
            public void run() {
                // Constructing the data source
                dataPojos_of_contact_list = readContacts();
                // Creating the adapter to convert the array to views
                adapter = new CustomUsersAdapter(MainActivity.this, dataPojos_of_contact_list);

                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);    // making single selection on list view
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);        // once the data are loaded the progress bar is made invisible
                        listView.setAdapter(adapter);                    // Attaching the adapter to a ListView
                    }
                });

            }
        });
        doInBackground.start();

    }

    // method for reading phone contacts
    public ArrayList<DataPojo> readContacts() {
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
                    dataPojos_of_contact_list.add(new DataPojo(displayName, mobileNo, PhotoPath));     // here we added the item to array list
                }
            } while (contCursor.moveToNext());
        }
        return dataPojos_of_contact_list;
    }

    // option menu to inflate search view on toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_bar, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) menu.findItem(R.id.search_icon).getActionView();
        searchView.setQueryHint("Search Contacts");

        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        } else {
            searchView.setIconifiedByDefault(false);
        }

        searchView.setOnQueryTextListener(this);
        return true;

    }

    // methods for action on pressing back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                if (adapter.rb.isChecked() || adapter.lastcheck.isChecked()  )
                {
                    adapter.rb.setChecked(false);
                    adapter.lastcheck.setChecked(false);

                }
                else
                {
                    Toast.makeText(this, "Back button pressed", Toast.LENGTH_SHORT).show();
                }

            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // methods for search activity
    @Override
    public boolean onQueryTextSubmit(String query) {
      /*  if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }*/
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        String userInput = s.toLowerCase();

        ArrayList<DataPojo> newStrings = new ArrayList<>();

        if (userInput.equals(""))
        {
            newStrings= dataPojos_of_contact_list;
            adapter.updateList(newStrings);

        }
        else
        {
            for (DataPojo item : dataPojos_of_contact_list) {

                if (item.getContact_name().toLowerCase().contains(userInput))
                {
                    newStrings.add(item);
                }

            }
            adapter.updateList(newStrings);
        }


        return true;
    }


}