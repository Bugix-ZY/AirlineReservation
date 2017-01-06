package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeDeleteRegActivity extends AppCompatActivity {


    private EditText flightnoinput;
    protected DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_delete_reg);

        flightnoinput = (EditText) findViewById(R.id.deleteRegNoEdit);
    }


    public void onClickedDeleteRegButton(View view) {
        mydb = new DBHandler(this, null, null, 1);
        mydb.deleteReg(flightnoinput.getText().toString());
        mydb.close();
        Toast.makeText(getApplicationContext(), "Delete Success!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.setClass(EmployeeDeleteRegActivity.this, EmployeeMenuActivity.class);
        startActivity(intent);
        EmployeeDeleteRegActivity.this.finish();
    }

    public void onClickedDeleteRegCancel(View view) {
        Intent intent = new Intent();
        intent.setClass(EmployeeDeleteRegActivity.this, EmployeeMenuActivity.class);
        startActivity(intent);
        EmployeeDeleteRegActivity.this.finish();
    }
}
