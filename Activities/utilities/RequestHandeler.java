package com.nrxtechnologies.hoto.utilities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by KRISHNA on 12/6/2017.
 */

public class RequestHandeler {
    private static RequestHandeler mInstance;
    private RequestQueue mRequestQueue;

    private static Context mCtx;

    private RequestHandeler(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestHandeler getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestHandeler(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}