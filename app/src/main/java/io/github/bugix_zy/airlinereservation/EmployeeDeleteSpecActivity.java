package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeDeleteSpecActivity extends AppCompatActivity {

    private EditText flightnoinput;
    private EditText dateinput;
    protected DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_delete_spec);

        flightnoinput = (EditText) findViewById(R.id.deleteSpecNoEdit);
        dateinput = (EditText) findViewById(R.id.deleteSpecDateEdit);

    }

    public void onClickedDeleteSpecButton(View view){
        mydb = new DBHandler(this, null, null, 1);
        mydb.deleteSpec(flightnoinput.getText().toString(), dateinput.getText().toString());
        mydb.close();
        Toast.makeText(getApplicationContext(), "Delete Success!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.setClass(EmployeeDeleteSpecActivity.this, EmployeeMenuActivity.class);
        startActivity(intent);
        EmployeeDeleteSpecActivity.this.finish();
    }


    public void onClickedDeleteSpecBack(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeDeleteSpecActivity.this, EmployeeMenuActivity.class);
        startActivity(intent);
        EmployeeDeleteSpecActivity.this.finish();
    }
}
