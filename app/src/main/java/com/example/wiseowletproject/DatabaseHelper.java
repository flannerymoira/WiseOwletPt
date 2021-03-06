package com.example.wiseowletproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "wiseOwlet1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //insert new student into the database
    public boolean insert(String first_name, String surname, String email, String password, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("first_name",first_name);
        contentValues.put("surname", surname);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("phone",phone);
        long ins = db.insert("student", null, contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }
    //check if email is correct
    public Boolean checkEmail(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select email from student where email=?", new String[]{email});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    //check if Password is correct
    public Boolean checkPassword(String Email, String password) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select email, password from student where email=? and password=?", new String[]{Email, password});
        if (cursor.getCount()> 0)
            return true;
        else
            return false;
    }

}

