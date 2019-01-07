package com.edwisor.scrollviewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ScrollViewActivity extends AppCompatActivity {

    private Button mMoreVersionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        mMoreVersionsButton = (Button) findViewById(R.id.button_more_version);

        mMoreVersionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScrollViewActivity.this, ListViewActivity.class));
            }
        });
    }
}
