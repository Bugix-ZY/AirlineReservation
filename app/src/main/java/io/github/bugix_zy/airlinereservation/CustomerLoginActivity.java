package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerLoginActivity extends AppCompatActivity {
    protected DBHandler mydb;
    private EditText account;
    private EditText pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);


        mydb = new DBHandler(this, null, null, 1);
        //        // initialization
        Airline airline = Airline.getInstance();
        airline.addRegFlight("F09782", "Taipei", "Shanghai", "16:00", "17:55", 8036);
        airline.addRegFlight("A46891", "Taipei", "Osaka","09:50", "13:35", 9953);
        airline.addRegFlight("A13355", "Taipei", "Osaka","12:00", "15:55", 9953);
        airline.addRegFlight("A10239", "Taipei", "Osaka","15:40", "19:25", 9953);
        airline.addRegFlight("A40702", "Taipei", "Osaka","19:50", "23:35", 9038);
        mydb.addReg(airline.getRegFlights().get(0));
        mydb.addReg(airline.getRegFlights().get(1));
        mydb.addReg(airline.getRegFlights().get(2));
        mydb.addReg(airline.getRegFlights().get(3));
        mydb.addReg(airline.getRegFlights().get(4));

        airline.getRegFlights().get(0).addSpecFlight("2017-01-10", 108, 25);
        airline.getRegFlights().get(0).addSpecFlight("2017-01-11", 108, 25);
        airline.getRegFlights().get(0).addSpecFlight("2017-01-12", 108, 25);

        airline.getRegFlights().get(1).addSpecFlight("2017-01-10", 108, 27);
        airline.getRegFlights().get(1).addSpecFlight("2017-01-11", 108, 27);
        airline.getRegFlights().get(1).addSpecFlight("2017-01-12", 108, 27);

        airline.getRegFlights().get(2).addSpecFlight("2017-01-10", 108, 29);
        airline.getRegFlights().get(2).addSpecFlight("2017-01-11", 108, 29);
        airline.getRegFlights().get(2).addSpecFlight("2017-01-12", 108, 29);

        airline.getRegFlights().get(3).addSpecFlight("2017-01-10", 108, 15);
        airline.getRegFlights().get(3).addSpecFlight("2017-01-11", 108, 15);
        airline.getRegFlights().get(3).addSpecFlight("2017-01-12", 108, 15);

        mydb.addSpec(airline.getRegFlights().get(0).getAllSpecFlights().get(0));
        mydb.addSpec(airline.getRegFlights().get(0).getAllSpecFlights().get(1));
        mydb.addSpec(airline.getRegFlights().get(0).getAllSpecFlights().get(2));

        mydb.addSpec(airline.getRegFlights().get(1).getAllSpecFlights().get(0));
        mydb.addSpec(airline.getRegFlights().get(1).getAllSpecFlights().get(1));
        mydb.addSpec(airline.getRegFlights().get(1).getAllSpecFlights().get(2));

        mydb.addSpec(airline.getRegFlights().get(2).getAllSpecFlights().get(0));
        mydb.addSpec(airline.getRegFlights().get(2).getAllSpecFlights().get(1));
        mydb.addSpec(airline.getRegFlights().get(2).getAllSpecFlights().get(2));

        mydb.addSpec(airline.getRegFlights().get(3).getAllSpecFlights().get(0));
        mydb.addSpec(airline.getRegFlights().get(3).getAllSpecFlights().get(1));
        mydb.addSpec(airline.getRegFlights().get(3).getAllSpecFlights().get(2));


        mydb.addCustomer(new Person("David","g50558347","096661234"),"123456");

        mydb.close();
    }

    public void onClickedLoginButton(View view){

        account = (EditText) findViewById(R.id.accountEdit) ;
        pswd = (EditText) findViewById(R.id.pswEdit);

        String acco = account.getText().toString();
        String psw = pswd.getText().toString();

        if (mydb.isMember(acco, psw)) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("account", acco);
            intent.putExtras(bundle);
            intent.setClass(CustomerLoginActivity.this, CustomerMenuActivity.class);
            startActivity(intent);
            CustomerLoginActivity.this.finish();
        }

        else
            Toast.makeText(getApplicationContext(), "Wrong account or password!", Toast.LENGTH_LONG).show();
    }
    public void onClickedSignup(View view){
        Intent intent = new Intent();
        intent.setClass(CustomerLoginActivity.this, CustomerRegisterActivity.class);
        startActivity(intent);
        CustomerLoginActivity.this.finish();
    }

}
