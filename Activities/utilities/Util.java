package com.nrxtechnologies.hoto.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;

import com.nrxtechnologies.hoto.R;

public class Util {


    public static boolean checkInternetConnection(Context _activity) {
        ConnectivityManager conMgr = (ConnectivityManager) _activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }

    public static void showAlert(final Activity _activity,
                                 String alertMsg, final boolean goBack) {
        if (_activity == null || _activity.isFinishing())
            return;
        AlertDialog.Builder alert = new AlertDialog.Builder(_activity);
        alert.setTitle(_activity.getResources().getString(R.string.app_name));
        alert.setIcon(R.mipmap.ic_launcher);
        alert.setCancelable(false);
        alert.setMessage(alertMsg);
        alert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (goBack)
                            _activity.finish();
                    }
                });
        alert.show();

    }

    public static void NoInternetDialog(final Activity context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Internet ");
        builder.setMessage("Please Enable Your Internet ");
        builder.setCancelable(false);

        builder.setPositiveButton("Enable",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
                    }
                });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.finish();
            }
        });
        builder.create().show();
    }

}
