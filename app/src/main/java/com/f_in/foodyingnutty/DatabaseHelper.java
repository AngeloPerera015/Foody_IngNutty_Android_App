package com.f_in.foodyingnutty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "SignUp.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "SignUp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table allusers(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists allusers");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("allusers", null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from allusers where username = ?", new String[]{username});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from allusers where username = ? and password = ?", new String[]{username, password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
}
