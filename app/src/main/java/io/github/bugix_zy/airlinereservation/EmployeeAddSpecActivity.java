package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EmployeeAddSpecActivity extends AppCompatActivity {

    private String sflightno;
    private String sdate;
    private int smaxcap;
    private int sgate;

    private EditText flightno;
    private EditText date;
    private EditText maxcapacity;
    private EditText gate;


    protected DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_spec);

        flightno = (EditText) findViewById(R.id.addSpecNoEdit);
        date = (EditText) findViewById(R.id.addSpecDateEdit);
        maxcapacity = (EditText) findViewById(R.id.addSpecMaxEdit);
        gate = (EditText) findViewById(R.id.addSpecGateEdit);
    }

    public void onClickedAddSpec(View view){
        sflightno = flightno.getText().toString();
        sdate = date.getText().toString();
        smaxcap = Integer.parseInt(maxcapacity.getText().toString());
        sgate = Integer.parseInt(gate.getText().toString());

        mydb = new DBHandler(this, null, null, 1);
        RegularFlight reg = mydb.getRegFlight(sflightno);
        reg.addSpecFlight( sdate, smaxcap, sgate);
        mydb.addSpec(reg.getAllSpecFlights().get(0));


    }


    public void onClickedAddSpecBack(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeAddSpecActivity.this, EmployeeMenuActivity.class);
        startActivity(intent);
        EmployeeAddSpecActivity.this.finish();
    }
}
