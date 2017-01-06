package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CustomerManagementActivity extends AppCompatActivity {

    private String account;

    protected DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);

        Intent intent = getIntent();
        account = intent.getStringExtra("account");

        mydb = new DBHandler(this, null, null, 1);
        String[] tickets = mydb.getTicket(account);
        mydb.close();

        ArrayAdapter<String> bugixsAdapter = new ArrayAdapter<String>(this, R.layout.ticket_layout,
                R.id.bugixTextView, tickets);

        ListView ticketsList = (ListView) findViewById(R.id.ticketsListView);
        ticketsList.setAdapter(bugixsAdapter);

        ticketsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String ticket = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("account", account);
                        bundle.putString("entry",ticket);
                        intent.putExtras(bundle);
                        intent.setClass(CustomerManagementActivity.this, CustomerCancelActivity.class);
                        startActivity(intent);
                        CustomerManagementActivity.this.finish();
                        //Toast.makeText(CustomerManagementActivity.this, ticket, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
