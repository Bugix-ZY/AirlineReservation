package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerBookingActivity extends AppCompatActivity {


    private String account;
    private String date;
    private String  flightno;
    protected DBHandler mydb;
    private EditText psgNameInput;
    private EditText psgIDInput;
    private EditText psgPhoneInput;
    private EditText emerNameInput;
    private EditText emerPhone;
    private TextView showTicket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booking);


        Intent intent = getIntent();
        account = intent.getStringExtra("account");
        flightno = intent.getStringExtra("flightno");
        date = intent.getStringExtra("date");

        psgNameInput = (EditText) findViewById(R.id.PsgNameEditText);
        psgIDInput = (EditText) findViewById(R.id.psgIDEditText);
        psgPhoneInput = (EditText) findViewById(R.id.psgPhoneEditText);
        emerNameInput = (EditText) findViewById(R.id.EmerNameEditText);
        emerPhone = (EditText) findViewById(R.id.emerPhoneInput);
        showTicket = (TextView) findViewById(R.id.TicketText);

        mydb = new DBHandler(this, null, null, 1);
        showAllTicket();
        mydb.close();


    }

    public void onClickedCancel(View view){
        //返回主页面
        Intent intent = new Intent();
        intent.setClass(CustomerBookingActivity.this, CustomerMenuActivity.class);
        startActivity(intent);
        CustomerBookingActivity.this.finish();

    }

    public void onClickedConfirm(View view){
        String psgName = psgNameInput.getText().toString();
        String psgID = psgIDInput.getText().toString();
        String psgPhone = psgPhoneInput.getText().toString();
        String emerName = emerNameInput.getText().toString();
        String emerPho = emerPhone.getText().toString();

        mydb = new DBHandler(this, null, null, 1);
        Person person = mydb.getCustomer(account);
        person.addRole(new CustomerRole());
        SpecificFlight specificFlight = mydb.getSpecFlight(flightno,date);
        CustomerRole woo =  (CustomerRole)person.getCustomerRole();
        woo.makeBooking(specificFlight,"random",psgName,psgPhone, psgID, emerName, emerPho);
        mydb.addTicket(woo.getTickets().get(0), account, flightno, specificFlight.getDate());
        showAllTicket();
        mydb.close();

        Toast.makeText(getApplicationContext(), "Booking success!", Toast.LENGTH_LONG).show();

        // 返回到主頁面
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("account", account);
        intent.putExtras(bundle);
        intent.setClass(CustomerBookingActivity.this, CustomerMenuActivity.class);
        startActivity(intent);
        CustomerBookingActivity.this.finish();


        // Booking
    }

    public void showAllTicket(){
        String str = mydb.dbTicketstring();
        showTicket.setText(str);
    }
}
