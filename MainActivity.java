package com.edwisor.session4samples;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * This Activity shows example of how permissions an be used to do some critical tasks like sending SMS and changing the
 * Wallpaper of the device.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button_set_wallpaper);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                setFunnyCatWallpaper();//Changing wallpaper
                sendFunnySms();//Sending sms
            }
        });


    }

    public void setFunnyCatWallpaper(){
        WallpaperManager manager = WallpaperManager.getInstance(this);
        try {
            manager.setResource(R.raw.taj_mahal);
        } catch (IOException e) {
            //exception can be thrown if, for some reason, changing wallpaper was not successful.
            e.printStackTrace();
        }
    }

    public void sendFunnySms(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("9044167566", null, "This is message text", null, null);
        smsManager.sendTextMessage("7277237897", null, "This is message text", null, null);
    }


}
