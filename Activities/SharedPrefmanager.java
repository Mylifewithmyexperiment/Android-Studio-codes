package com.nrxtechnologies.hoto.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.nrxtechnologies.hoto.LoginActivity;

/**
 * Created by Helios on 30/5/2019.
 */

//here for this class we are using a singleton pattern
public class SharedPrefmanager {

    //the constants
    private static final String SHARED_PREF_NAME = "hoto";
    private static final String KEY_EMPLOYEEID = "keyemployeeid";
    private static final String KEY_TOKEN = "keyid";
    private static SharedPrefmanager mInstance;
    private static Context mCtx;

    private SharedPrefmanager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefmanager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefmanager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(UserDetails user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMPLOYEEID, user.getEmployee_Id());
        editor.putString(KEY_TOKEN, user.getToken());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPLOYEEID, null) != null;
    }

    //this method will give the logged in user
    public UserDetails getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences
                (SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserDetails(
                sharedPreferences.getString(KEY_EMPLOYEEID, null),
                sharedPreferences.getString(KEY_TOKEN,null));
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
