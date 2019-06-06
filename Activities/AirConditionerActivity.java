package com.nrxtechnologies.hoto.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.nrxtechnologies.hoto.R;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.List;

public class AirConditionerActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    String[] c1={"0", "1", "2", "3", "4"};
    BetterSpinner spin1;
    List<EditText> editTextList;
    CardView contactlayout;
    Button busbookbtn;
    int noofSeats = 0;
    LinearLayout containerlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioner);

        spin1 = findViewById(R.id.c1spinner);
        spin1.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, c1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin1.setAdapter(aa);
        if (noofSeats > 0) {
            for (int i = 0; i < noofSeats; i++) {
                    LinearLayout linearLayout = (LinearLayout) containerlayout.getChildAt(i);

                Spinner spinner1 = (Spinner) linearLayout.getChildAt(0);
                EditText editText = (EditText) linearLayout.getChildAt(1);
                Spinner spinner2 = (Spinner) linearLayout.getChildAt(2);
                Spinner spinner3 = (Spinner) linearLayout.getChildAt(3);
                EditText editText1 = (EditText) linearLayout.getChildAt(4);

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position==0) {

            containerlayout.removeAllViews();
        } else {
            try {
                containerlayout.removeAllViews();
                noofSeats =position;

                LayoutInflater from = LayoutInflater.from(AirConditionerActivity.this);
                int j = 0;
                while (j < noofSeats) {
                    View inflate = from.inflate(R.layout.ac_layout, containerlayout, false);
                    containerlayout.addView(inflate);
                    j++;
                }

            } catch (NumberFormatException excetion) {
                excetion.printStackTrace();
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
