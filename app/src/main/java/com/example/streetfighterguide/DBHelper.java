package com.example.streetfighterguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "dbUser";

    public DBHelper(Context context) {
        super(context, "dbUser", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbSql) {
        dbSql.execSQL("CREATE TABLE User(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbSql, int i, int i1) {
        dbSql.execSQL("DROP TABLE IF EXISTS User");
    }

    public Boolean insertDb(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("User", null, values);

        if(result == -1) return false;
        else return true;
    }

    public Boolean viewName (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  User WHERE username=?", new String[] {username});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean viewAll (String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  User WHERE username=? AND password=?", new String[] {username, password });
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
