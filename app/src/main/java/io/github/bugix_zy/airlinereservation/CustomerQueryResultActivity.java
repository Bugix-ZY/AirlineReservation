package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerQueryResultActivity extends AppCompatActivity {

    protected DBHandler mydb;
    private TextView result;
    private EditText no;
    private String depa;
    private String arri;
    private String date;
    private  String account;
    private String flighno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_query_result);

        result = (TextView) findViewById(R.id.resultTextView);
        Intent intent = getIntent();
        depa = intent.getStringExtra("departure");
        arri = intent.getStringExtra("arrival");
        date = intent.getStringExtra("date");
        account = intent.getStringExtra("account");
        mydb = new DBHandler(this, null, null, 1);
        showResult(depa, arri, date);
        mydb.close();

    }

    public void onClickedBook(View view){
        // 数据库 curr Capcacity-1
        no = (EditText) findViewById(R.id.flightNoText);
        mydb = new DBHandler(this, null, null, 1);
        flighno = no.getText().toString();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("account", account);
        bundle.putString("date",date);
        bundle.putString("flightno", flighno);
        intent.putExtras(bundle);

        mydb.updateCapc(no.getText().toString(), date);
        mydb.close();
        // 更新结果
        showResult(depa, arri, date);
        intent.setClass(CustomerQueryResultActivity.this, CustomerBookingActivity.class);
        startActivity(intent);
        CustomerQueryResultActivity.this.finish();

    }
    public void onClickedBackToSearch(View view){
        Intent intent = new Intent();
        intent.setClass(CustomerQueryResultActivity.this, CustomerQueryActivity.class);
        startActivity(intent);
        CustomerQueryResultActivity.this.finish();
    }

    public void showResult(String dep, String arr, String date){
        String str = mydb.findSpec(dep,arr,date);
        result.setText(str);
        //mydb.close();
    }
}
