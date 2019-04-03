package com.example.redittclient;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity implements SearchView.OnQueryTextListener {


    SearchView searchView;
    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter();
    FragmentHome fragmentHome = new FragmentHome();
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.like:
                    Toast.makeText(MainScreen.this, "clicked reddit", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.comment:
                    Toast.makeText(MainScreen.this, "clicked dashboard", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.share:
                    Toast.makeText(MainScreen.this, "clicked message", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        setUpViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private void setUpViewPager(ViewPager mViewPager) {

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFragment(new FragmentHome(), "Home");
        mSectionsPagerAdapter.addFragment(new FragmentPopular(), "Popular");
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home_page, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setOnQueryTextListener(this);

        return true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        List<DataPojo> newStrings = new ArrayList<>();
        for (DataPojo item : fragmentHome.placing_data_in_home_fragment_recycler_view()) {
            if (item.get_main_heading().toLowerCase().contains(userInput)) {
                newStrings.add(item);
            }
        }
        recyclerViewAdapter.updateList(newStrings);
        return true;
    }

}


////////////////////////////////////////////// comments \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

/**
 * The {@link android.support.v4.view.PagerAdapter} that will provide
 * fragments for each of the sections. We use a
 * {@link FragmentPagerAdapter} derivative, which will keep every
 * loaded fragment in memory. If this becomes too memory intensive, it
 * may be best to switch to a
 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
 * <p>
 * The {@link ViewPager} that will host the section contents.
 *
 * @Override public boolean onOptionsItemSelected(MenuItem item) {
 * // Handle action bar item clicks here. The action bar will
 * // automatically handle clicks on the Home/Up button, so long
 * // as you specify a parent activity in AndroidManifest.xml.
 * int id = item.getItemId();
 * <p>
 * //noinspection SimplifiableIfStatement
 * if (id == R.id.action_settings) {
 * return true;
 * }
 * <p>
 * return super.onOptionsItemSelected(item);
 * }
 * A placeholder fragment containing a simple view.
 * <p>
 * The fragment argument representing the section number for this
 * fragment.
 * <p>
 * Returns a new instance of this fragment for the given section
 * number.
 */
// section page Adapter which will bridge the components from fragment resource page to java page


/**
 * The {@link ViewPager} that will host the section contents.
 */


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

*/
/**
 * @Override public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
int id = item.getItemId();

//noinspection SimplifiableIfStatement
if (id == R.id.action_settings) {
return true;
}

return super.onOptionsItemSelected(item);
}
 * A placeholder fragment containing a simple view.
 *//*
    public static class PlaceholderFragment extends Fragment {
        *//**
 * The fragment argument representing the section number for this
 * fragment.
 *//*
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        *//**
 * Returns a new instance of this fragment for the given section
 * number.
 *//*
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
            *//*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*//*


            return rootView;
        }
    }
*/
