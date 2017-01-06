package io.github.bugix_zy.airlinereservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EmployeeMenuActivity extends AppCompatActivity {

    protected DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_menu);


    }


    public void onClickedMenuAddSpec(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeMenuActivity.this, EmployeeAddSpecActivity.class);
        startActivity(intent);
        EmployeeMenuActivity.this.finish();
    }


    public void onClickedMenuAddReg(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeMenuActivity.this, EmployeeAddRegActivity.class);
        startActivity(intent);
        EmployeeMenuActivity.this.finish();
    }

    public void onClickedShowAllSpec(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeMenuActivity.this, EmployeeShowAllSpecActivity.class);
        startActivity(intent);
        EmployeeMenuActivity.this.finish();
    }

    public void onClickedShowAllReg(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeMenuActivity.this, EmployeeShowRegActivity.class);
        startActivity(intent);
        EmployeeMenuActivity.this.finish();
    }

    public void onClickedDeleteReg(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeMenuActivity.this, EmployeeDeleteRegActivity.class);
        startActivity(intent);
        EmployeeMenuActivity.this.finish();
    }

    public void onClickedDeleteSpec(View view){
        Intent intent = new Intent();
        intent.setClass(EmployeeMenuActivity.this, EmployeeDeleteSpecActivity.class);
        startActivity(intent);
        EmployeeMenuActivity.this.finish();
    }


}
