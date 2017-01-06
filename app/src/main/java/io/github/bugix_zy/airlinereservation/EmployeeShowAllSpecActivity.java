package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EmployeeShowAllSpecActivity extends AppCompatActivity {


    protected DBHandler mydb;
    private TextView speclist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_show_all_spec);

        speclist = (TextView) findViewById(R.id.allSpecText);
        mydb = new DBHandler(this, null, null, 1);
        showAllReg();
        mydb.close();
    }

    public void onClickedShowSpecBack(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeShowAllSpecActivity.this, EmployeeMenuActivity.class);
        startActivity(intent);
        EmployeeShowAllSpecActivity.this.finish();
    }


    public void showAllReg(){
        mydb = new DBHandler(this, null, null, 1);
        speclist.setText(mydb.dbSpec2string());
        mydb.close();
    }
}
