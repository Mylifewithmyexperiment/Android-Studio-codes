package com.nrxtechnologies.hoto.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.nrxtechnologies.hoto.LoginActivity;


import java.util.HashMap;

public class SessionManager
{
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "HOTO App Preferences";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String EMPLOYEE_ID = "employee_id";
    public static final String TOKEN = "token";

// Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createLoginSession(String employee_id,String token){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(EMPLOYEE_ID, employee_id);
        editor.putString(TOKEN, token);
        // commit changes
        editor.commit();
    }
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap();
        user.put(EMPLOYEE_ID, this.pref.getString(EMPLOYEE_ID, null));
        user.put(TOKEN, this.pref.getString(TOKEN, null));

        return user;
    }
    public String getToken(){

        return this.pref.getString(TOKEN, null);
    }
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }
    }
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
