package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeAddRegActivity extends AppCompatActivity {
    private String sflightno;
    private String sdeparture;
    private String sarrival;
    private String sdepatime;
    private String sarrtime;
    private double sprice;

    private EditText flightno;
    private EditText departure;
    private EditText arrival;
    private EditText depatime;
    private EditText arrtime;
    private EditText price;

    protected DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_reg);

        flightno = (EditText) findViewById(R.id.RegFlightNo);
        departure = (EditText) findViewById(R.id.RegDeparture);
        arrival = (EditText) findViewById(R.id.RegArrival);
        depatime = (EditText) findViewById(R.id.depaTimeEdit);
        arrtime = (EditText) findViewById(R.id.arrTimeEdit);
        price = (EditText) findViewById(R.id.RegPriceEdit);
    }

    public void onClickedAddReg(View view){

        sflightno = flightno.getText().toString();
        sdeparture = departure.getText().toString();
        sarrival = arrival.getText().toString();
        sdepatime = depatime.getText().toString();
        sarrtime = arrtime.getText().toString();
        sprice = Double.parseDouble(price.getText().toString());


        // insert
        Airline airline = Airline.getInstance();
        airline.addRegFlight(sflightno, sdeparture, sarrival, sdepatime, sarrtime, sprice);

        // insert to db
        mydb = new DBHandler(this, null, null, 1);
        mydb.addReg(airline.getRegFlights().get(0));

        Toast.makeText(getApplicationContext(), "Add Success!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setClass(EmployeeAddRegActivity.this, EmployeeMenuActivity.class);
        startActivity(intent);
        EmployeeAddRegActivity.this.finish();

    }

    public void onClickedAddRegBack(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeAddRegActivity.this, EmployeeMenuActivity.class);
        startActivity(intent);
        EmployeeAddRegActivity.this.finish();
    }
}


