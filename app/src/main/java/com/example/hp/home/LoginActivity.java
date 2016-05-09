package com.example.hp.home;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
/**
 * Created by hp on 08-05-2016.
 */
public class LoginActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    @InjectView(R.id.eid)EditText eid;
    @InjectView(R.id.ename)EditText ename;
    @InjectView(R.id.eemail)EditText eemail;
    @InjectView(R.id.epassword)EditText epassword;
    @InjectView(R.id.fabadd)FloatingActionButton button;
    @InjectView(R.id.fabview)FloatingActionButton viewbutton;
    @InjectView(R.id.fabupdate)FloatingActionButton updatebutton;
    @InjectView(R.id.fabdelete)FloatingActionButton deletebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlfile);

        ButterKnife.inject(this);



        myDb= new DatabaseHelper(this);

        addData();
        showAllData();
        updateData();
        deleteData();
    }

    public void addData()
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(eid.getText().toString(),ename.getText().toString(), eemail.getText().toString(), epassword.getText().toString());
                if (isInserted)
                    Toast.makeText(LoginActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(LoginActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void showAllData()
    {
        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDb.getAllData();
                if (cursor.getCount() == 0) {
                    showMsg("Error", "No Data");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("Id :" + cursor.getString(0) + "\nName :" + cursor.getString(1) + "\nEmail :" + cursor.getString(2) + "\n\n");
                }
                //show all data
                showMsg("Data", buffer.toString());
            }
        });
    }
    public void showMsg(String title,String msg)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
    public void updateData()
    {
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean updated = myDb.updateData(eid.getText().toString(), ename.getText().toString(), eemail.getText().toString(), epassword.getText().toString());
                if (updated) {
                    Toast.makeText(LoginActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(LoginActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteData()
    {
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleted = myDb.deleteData(eid.getText().toString());
                if(deleted == 0)
                    Toast.makeText(LoginActivity.this,"No such row",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
