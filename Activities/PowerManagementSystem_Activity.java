package com.nrxtechnologies.hoto.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.nrxtechnologies.hoto.R;

public class PowerManagementSystem_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
     String[] availability = {  "Yes", "No" };
     String [] type={"PIU/PMU","AMF Panel","Not Available"};
     String[] make={"DELTA","PACE","Intelux","ACME","SUN","UTOPIA","Mahindra","AMF Other Makes"};
     String [] position={"Indoor Type","Outdoor type","Mounted with DG","Outdoor With Cabinet"};
     String [] status={"Auto Mode","Manuel Mode","Not Working since long","Man Operated"};
     String [] condition={"Working","Not Working"};
     Spinner spinner_avilability,spinner_type,spinner_make,
        spinner_position,spinner_status,spinner_condition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_management_system_);
        spinner_avilability=(Spinner)findViewById(R.id.pms_availability_spinner);
        spinner_type=(Spinner)findViewById(R.id.pmstype_spinner);
        spinner_make=(Spinner)findViewById(R.id.pmsMake_spinner);
        spinner_position=(Spinner)findViewById(R.id.pmsposition_spinner);
        spinner_status=(Spinner)findViewById(R.id.pmsstatus_spinner);
        spinner_condition=(Spinner)findViewById(R.id.pmsworkingcondition_spinner);

        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,availability);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_avilability.setAdapter(aa1);

        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_type.setAdapter(aa2);

        ArrayAdapter aa3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,make);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_make.setAdapter(aa3);

        ArrayAdapter aa4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,position);
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_position.setAdapter(aa4);

        ArrayAdapter aa5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,status);
        aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_status.setAdapter(aa5);

        ArrayAdapter aa6 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,condition);
        aa6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_condition.setAdapter(aa6);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
