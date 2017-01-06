package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerRegisterActivity extends AppCompatActivity {
    private DBHandler mydb;
    private EditText name;
    private EditText phone;
    private EditText idno;
    private EditText pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);


    }

    public void onClickedRegisterButton(View view){
        // 寫入database
        name = (EditText) findViewById(R.id.nameEditText);
        phone = (EditText) findViewById(R.id.phoneEditText);
        idno = (EditText) findViewById(R.id.idcardEditText);
        pswd = (EditText) findViewById(R.id.pswdEditText);
        mydb = new DBHandler(this, null, null, 1);

        Person person = new Person(name.getText().toString(), idno.getText().toString(), phone.getText().toString());
        mydb.addCustomer(person, pswd.getText().toString());
        mydb.close();

        Toast.makeText(getApplicationContext(), "Registration Success!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setClass(CustomerRegisterActivity.this, CustomerLoginActivity.class);
        startActivity(intent);
        CustomerRegisterActivity.this.finish();
    }

    public void onClickedBackToLogin(View view){
        Intent intent = new Intent();
        intent.setClass(CustomerRegisterActivity.this, CustomerLoginActivity.class);
        startActivity(intent);
        CustomerRegisterActivity.this.finish();
    }
}
