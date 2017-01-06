package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EmployeeShowRegActivity extends AppCompatActivity {

    protected DBHandler mydb;
    private TextView reglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_show_reg);
        reglist = (TextView) findViewById(R.id.tempRegView);
        mydb = new DBHandler(this, null, null, 1);
        showAllReg();
        mydb.close();
    }

    public void onClickedAllRegBack(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeShowRegActivity.this, EmployeeMenuActivity.class);
        startActivity(intent);
        EmployeeShowRegActivity.this.finish();
    }

    public void showAllReg(){
        mydb = new DBHandler(this, null, null, 1);
        reglist.setText(mydb.dbReg2string());
        mydb.close();
    }
}
