package com.example.hp.home;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.support.design.widget.Snackbar.*;

/**
 * Created by hp on 08-05-2016.
 */
public class LoginActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText ename;
    EditText eemail;
    EditText epassword;
    EditText eid;
    FloatingActionButton button;
    FloatingActionButton viewbutton;
    FloatingActionButton updatebutton;
    FloatingActionButton deletebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlfile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myDb= new DatabaseHelper(this);
        eid = (EditText)findViewById(R.id.eid);
        ename=(EditText)findViewById(R.id.ename);
        eemail=(EditText)findViewById(R.id.eemail);
        epassword=(EditText)findViewById(R.id.epassword);
        button=(FloatingActionButton)findViewById(R.id.fabadd);
        viewbutton=(FloatingActionButton)findViewById(R.id.fabview);
        updatebutton = (FloatingActionButton)findViewById(R.id.fabupdate);
        deletebutton = (FloatingActionButton)findViewById(R.id.fabdelete);
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
