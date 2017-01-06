package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;




public class CustomerMenuActivity extends AppCompatActivity {

    protected DBHandler mydb;
    private TextView welcomeTextView;
    private  String name;
    private  String account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);

        welcomeTextView = (TextView) findViewById(R.id.welcometextView);
        mydb = new DBHandler(this, null, null, 1);

        Intent intent = getIntent();
        account = intent.getStringExtra("account");
        welcomeTextView.setText("Welcome back, " + mydb.getCustomerName(account));
        mydb.close();


    }

    public void onClickedBookingButton(View view){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("account", account);
        intent.putExtras(bundle);
        intent.setClass(CustomerMenuActivity.this, CustomerQueryActivity.class);
        startActivity(intent);
        CustomerMenuActivity.this.finish();
    }

    public void onClickedManagement(View view){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("account", account);
        intent.putExtras(bundle);
        intent.setClass(CustomerMenuActivity.this, CustomerManagementActivity.class);
        startActivity(intent);
        CustomerMenuActivity.this.finish();
    }

    public  void onClickedmenuBackButton(View view){
        Intent intent = new Intent();
        intent.setClass(CustomerMenuActivity.this, CustomerLoginActivity.class);
        startActivity(intent);
        CustomerMenuActivity.this.finish();
    }





}
