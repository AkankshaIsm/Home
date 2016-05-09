package com.example.hp.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.Callable;

/**
 * Created by hp on 08-05-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Student.db";
    public static final String TABLE_NAME ="student_table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="NAME";
    public static final String COL_3 ="EMAIL";
    public static final String COL_4 ="PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1); //database create when this constructor is called

    }

    @Override //table created when this is called
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("create table " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PASSWORD TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
   db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
       onCreate(db);
    }

    public boolean insertData(String id,String name,String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4, password);
        long result= db.insert(TABLE_NAME,null,contentValues);   //if data not inserted returns -1
        if(result == -1)
            return false;

        return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public boolean updateData(String id,String name,String email,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4, password);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});
        return true;

    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer res = db.delete(TABLE_NAME, "ID = ?", new String[]{id}); //returns the no of affected rows
        return res;
    }
}
