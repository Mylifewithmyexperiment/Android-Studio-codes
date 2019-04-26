package com.example.yourcontacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {

    TextView next_contact_name, next_contact_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String contact_name = bundle.getString("contact_name");
        String contact_no = bundle.getString("contact_no");

        next_contact_name = findViewById(R.id.next_screen_contact_name);
        next_contact_no = findViewById(R.id.next_screen_contact_number);

        next_contact_name.setText(contact_name);
        next_contact_no.setText(contact_no);

    }
}
