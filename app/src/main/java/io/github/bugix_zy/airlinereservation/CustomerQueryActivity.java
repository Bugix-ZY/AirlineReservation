package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerQueryActivity extends AppCompatActivity {
    TextView queryText;
    
    EditText departureInput;
    EditText arrivalInput;
    EditText dateInput;
    private  String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_query);
        queryText = (TextView)findViewById(R.id.queryTextView);
        Intent intent = getIntent();
        account = intent.getStringExtra("account");


    }


    public void onClickedSearchButton(View view){
        EditText departureInput = (EditText) findViewById(R.id.departText);
        EditText arrivalInput = (EditText) findViewById(R.id.arrivalText);
        EditText dateInput = (EditText)  findViewById(R.id.dateText);

        String depa = departureInput.getText().toString();
        String arri = arrivalInput.getText().toString();
        String date = dateInput.getText().toString();

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("departure",depa);
        bundle.putString("arrival",arri);
        bundle.putString("date",date);
        bundle.putString("account", account);
        intent.putExtras(bundle);
        intent.setClass(CustomerQueryActivity.this, CustomerQueryResultActivity.class);
        startActivity(intent);
        CustomerQueryActivity.this.finish();
    }

    public void onClickedBackButton(View view){
        Intent intent = new Intent();
        intent.setClass(CustomerQueryActivity.this, CustomerMenuActivity.class);
        startActivity(intent);
        CustomerQueryActivity.this.finish();
    }

//    public void printSpecDatabase(){
//        String dbstr = mydb.dbSpec2string();
//        Log.i("bugixlogmessgae", dbstr);
//        Log.i("bugixlogmessgae", "printSpecDatabase");
//        queryText.setText(dbstr);
//    }


}
