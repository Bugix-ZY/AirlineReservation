package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CustomerCancelActivity extends AppCompatActivity {
    private String rawstr;
    private String flightno;
    private String date;
    private String account;
    private TextView detail;
    protected DBHandler mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cancel);

        Intent intent = getIntent();
        rawstr = intent.getStringExtra("entry");
        account = intent.getStringExtra("account");


        String[] s = rawstr.split("   ");
        flightno = s[0];
        date = s[1];
        Log.i("ohmymymy","date=" + date);

        mydb = new DBHandler(this, null, null, 1);

        detail = (TextView) findViewById(R.id.ticketDetail);
        detail.setText(mydb.getTicketDetail(flightno, date, account));

    }

    public void onClickedTicketCancel(View view){
        mydb.deleteTicket(flightno, date, account);
        // 返回到主頁面
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("account", account);
        intent.putExtras(bundle);
        intent.setClass(CustomerCancelActivity.this, CustomerManagementActivity.class);
        startActivity(intent);
        CustomerCancelActivity.this.finish();

    }

    public void onClickedDetailBack(View view){
        // 返回到主頁面
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("account", account);
        intent.putExtras(bundle);
        intent.setClass(CustomerCancelActivity.this, CustomerMenuActivity.class);
        startActivity(intent);
        CustomerCancelActivity.this.finish();

    }
}
