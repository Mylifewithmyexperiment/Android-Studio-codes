package com.nrxtechnologies.hoto.utilities;

import android.app.Application;
import android.os.StrictMode;

public class MyApplication extends Application
{
    @Override
    public void onCreate() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        super.onCreate();

    }
}
